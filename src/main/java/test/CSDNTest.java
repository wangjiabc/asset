package test;

import java.util.regex.*;
public class CSDNTest
{
  public static void main(String[] ss)
  {
    String s="abc.jpg";
    //String regex=".+?//.(.+)";这种写法也是可以的，但我认为没有后面的精确
    String regex=".+?//.([a-zA-z]+)";
    Pattern pt=Pattern.compile(regex);
    Matcher mt=pt.matcher(s);
    System.out.println(s.substring(s.lastIndexOf('.')+1));
    if(mt.find())
    {
      System.out.println(mt.group(1));
    }else{
    	System.out.println("aaaaaa");
    }
  }
}