package com.kaustubh.rubrics;

/**
 * Created by KAUSTUBH on 30-01-2017.
 */
public class Contact4 {

    int id;
    int weight;
    String row;
    String column;
    int lweight;
    int hweight;

    public void setRow(String row)
    {
        this.row = row;
    }

    public String getRow()

    {
        return this.row;
    }

    public void setColumn(String column)
    {
        this.column = column;
    }

    public String getColumn()

    {
        return this.column;
    }


    public void setWeight(int weight) {this.weight = weight;}

    public int getWeight()

    {
        return this.weight;
    }

    public void setLweight(int lweight){ this.lweight = lweight;}
    public int getLweight(){ return this.lweight; }

    public void setHweight(int hweight){ this.hweight = hweight;}
    public int getHweight(){ return this.hweight; }
}
