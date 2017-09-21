package test;

import java.sql.Date;

import test.annotations.Constraints;
import test.annotations.DBTable;
import test.annotations.SQLDateTime;
import test.annotations.SQLFloat;
import test.annotations.SQLInteger;
import test.annotations.SQLString;
 
@DBTable(name="ggg")
public class User {
    @SQLInteger(name="id")
    public Integer id;
    @SQLString(value=30)
    public String name;
    @SQLString(name="passwd")
    public String password;
    @SQLString(name="sss")
    public String ssss;
    @SQLString(name="www")
    public String www;
    
    @SQLFloat(name="bbb")
    public Float bbb;
    @SQLDateTime(name="time")
    public Date time;
    
   /* 
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    */
}
