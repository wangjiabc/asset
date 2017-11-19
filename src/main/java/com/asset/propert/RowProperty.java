package com.asset.propert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLLong;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class RowProperty<T> {
	Object object;	
	Class<?> className;
	Class<?> objectPropertyName;
	
	public RowProperty(Object object,Class<?> objectPropertyName) {
		// TODO Auto-generated constructor stub
        this.object=object;
        this.objectPropertyName=objectPropertyName;
        className=object.getClass();
	}
	
	public T get() {
		String name = className.getName(); 
   	    Class<?> cl=null;
        try {
			cl = Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Object objectProperty=null;
		try {
			objectProperty = objectPropertyName.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
        

			 for(Field field : cl.getDeclaredFields())                  //获取声明的属性
		        {
		        	String columnName = null;           
		            Annotation[] anns = field.getDeclaredAnnotations();//获取注解，一个属性可以有多个注解，所以是数组类型
		            if(anns.length < 1)
		            {
		                continue;
		            }else
		            if(anns[0] instanceof SQLInteger)                //判断注解类型
		            {
		                SQLInteger sInt = (SQLInteger)anns[0];
		                columnName = (sInt.name().length()<1)?field.getName():sInt.name();//获取列名称与获取表名一样
		                setIntMethods(object,objectProperty, cl, field, columnName);

		            }else
		            if(anns[0] instanceof SQLString)
		            {
		                SQLString sStr = (SQLString)anns[0];
		                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
		                setStringMethods(object,objectProperty, cl, field, columnName);
		            }else
		            if(anns[0] instanceof SQLFloat)
		            {
		                SQLFloat sStr = (SQLFloat) anns[0];
		                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
		                setFloatMethods(object,objectProperty, cl, field, columnName);
		            }else
		            if(anns[0] instanceof SQLDateTime)
		            {
		                SQLDateTime sStr = (SQLDateTime) anns[0];
		                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
		                setDateTimeMethods(object,objectProperty,cl, field, columnName);
		            }else
			        if(anns[0] instanceof SQLDouble)
			        {
			             SQLDouble sStr =  (SQLDouble) anns[0];
			             columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
			             setDoubleMethods(object,objectProperty,cl, field, columnName);
			         }else
					 if(anns[0] instanceof SQLLong)
					 {
					      SQLLong sStr =  (SQLLong) anns[0];
					      columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
					      setLongMethods(object,objectProperty,cl, field, columnName);
					 }else
			         if(anns[0] instanceof SQLDateTime)
			         {
			               SQLDateTime sStr = (SQLDateTime) anns[0];
			               columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
			               setDateTimeMethods(object,objectProperty,cl, field, columnName);
			         }
		         }
		   
		
		   return (T) objectProperty;
	    }
	
	public static void setStringMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
      //  System.out.println("classname="+className);
      //  System.out.println("getMethodName="+getMethodName);
      // System.out.println("setMethodName="+setMethodName);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
      	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,String.class);
       	
       	 String aa=(String) getMethod.invoke(object,null);
      	// System.out.println("aa="+aa);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setIntMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
     //  System.out.println("getMethodName="+getMethodName);
     //  System.out.println("className="+className);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
    	 /* 
    	  * setMethod与getMethod 不一样的在于不能使用classname
    	  * 否则抛出 java.lang.IllegalArgumentException
    	  * object is not an instance of declaring class
    	  */
       	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,Integer.class);
     //   System.out.println("setmethod="+setMethod);
       	 Integer aa=(Integer) getMethod.invoke(object,null);
     //  	 System.out.println("aa="+aa+" "+objectProperty);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setFloatMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
     //   System.out.println("setMethodName="+setMethodName);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
    	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,Float.class);
       //	 System.out.println("setmethod="+setMethod);
       	 float aa=(float) getMethod.invoke(object,null);
       //	 System.out.println("aa="+aa);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setDateTimeMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
       // System.out.println("setMethodName="+setMethodName);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
    	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,Date.class);
     //  	 System.out.println("setmethod="+setMethod);
       	 Date aa= (Date) getMethod.invoke(object,null);
      // 	 System.out.println("aa="+aa);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void setDoubleMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
     //  System.out.println("getMethodName="+getMethodName);
     //  System.out.println("className="+className);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
    	 /* 
    	  * setMethod与getMethod 不一样的在于不能使用classname
    	  * 否则抛出 java.lang.IllegalArgumentException
    	  * object is not an instance of declaring class
    	  */
       	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,Double.class);
      //  System.out.println("setmethod="+setMethod);
       	 Double aa= (Double) getMethod.invoke(object,null);
       //	 System.out.println("aa="+aa+" "+objectProperty);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void setLongMethods(Object object,Object objectProperty,Class className,Field field,String columnName){
        String filedName = field.getName();  
        //获取相应字段的getXXX()方法  
        String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
        String setMethodName = "set" + filedName.substring(0, 1).toUpperCase()  
                + filedName.substring(1); 
      // System.out.println("getMethodName="+getMethodName);
      // System.out.println("className="+className);
       try {
    	 Method getMethod =className.getDeclaredMethod(getMethodName);
    	 /* 
    	  * setMethod与getMethod 不一样的在于不能使用classname
    	  * 否则抛出 java.lang.IllegalArgumentException
    	  * object is not an instance of declaring class
    	  */
       	 Method setMethod =(objectProperty.getClass()).getDeclaredMethod(setMethodName,Long.class);
        System.out.println("setmethod="+setMethod);
       	 Long aa= (Long) getMethod.invoke(object,null);
     //  	 System.out.println("aa="+aa+" "+objectProperty);
		 setMethod.invoke(objectProperty,aa);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
