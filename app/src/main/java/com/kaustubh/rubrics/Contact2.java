package com.kaustubh.rubrics;

/**
 * Created by KAUSTUBH on 23-01-2017.
 */
public class Contact2 {
    int id;
    int tid;
    String cname;
    int totalmarks;


    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getCname()

    {
        return this.cname;
    }


    public void setTid(int tid) {this.tid = tid;}

    public int getTid()

    {
        return this.tid;
    }

    public void setTotalmarks(int totalmarks) { this.totalmarks = totalmarks; }

    public int getTotalmarks() { return this.totalmarks; }
}
