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
    String phone;
    int date;
    int year;
    int month;
    String forget;
    String answer;



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

    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return this.phone;}

    public void setDate(int date){this.date = date;}
    public int getDate(){ return this.date;}

    public void setMonth(int month){this.month = month;}
    public int getMonth(){ return this.month;}

    public void setYear(int year){this.year = year;}
    public int getYear(){ return this.year;}

    public void setForget(String forget)

    {
        this.forget = forget;
    }

    public String getForget()

    {
        return this.forget;
    }

    public void setAnswer(String answer)

    {
        this.answer = answer;
    }

    public String getAnswer()

    {
        return this.answer;
    }


}
