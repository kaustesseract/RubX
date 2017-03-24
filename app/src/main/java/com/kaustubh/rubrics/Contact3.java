package com.kaustubh.rubrics;

/**
 * Created by KAUSTUBH on 30-01-2017.
 */
public class Contact3 {

    int id;
    int low;
    String rubrics;
    int high;
    int grade;

    public void setRubric(String rubrics)
    {
        this.rubrics = rubrics;
    }

    public String getRubric()

    {
        return this.rubrics;
    }


    public void setLow(int low) {this.low = low;}

    public int getLow() {return this.low;}

    public void setHigh(int high) {this.high = high;}

    public int getHigh() {return this.high;}

    public void setGrade(int grade) {this.grade = grade;}

    public int getGrade() {return this.grade;}
}
