package com.lundin_pr.sqlitetesting.sql.entity;

/**
 * Created by Eirikur Lundin on 3/17/2017.
 *
 */

public class TestEntity {

    private int counter;
    private String content;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
