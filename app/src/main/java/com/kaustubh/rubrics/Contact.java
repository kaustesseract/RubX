package com.kaustubh.rubrics;

/**
 * Created by KAUSTUBH on 12-10-2016.
 */
public class Contact {

    int id;
    String name;
    String password;
    String email;
    int roll;
    String classname;
    String studentc;


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()

    {
        return this.name;
    }

    public void setPassword(String password)

    {
        this.password = password;
    }

    public String getPassword()

    {
        return this.password;
    }

    public void setEmail(String email)

    {
        this.email = email;
    }

    public String getEmail()

    {
        return this.email;
    }

    public void setStudentc(String studentc)
    {
        this.studentc = studentc;
    }

    public String getStudentc()

    {
        return this.studentc;
    }

    public void setClassname(String classname)
    {
        this.classname = classname;
    }

    public String getClassname()

    {
        return this.classname;
    }

    public void setRoll(int roll) {this.roll = roll;}

    public int getRoll()

    {
        return this.roll;
    }


}
