package com.example.demo.command;

import org.springframework.data.domain.PageRequest;

/**
 * Created by wdg on 2018/5/29.
 */
public class BaseCommand  {
    int length=10;
    int start=0;
    int draw;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLength() {
        return length;
    }

//    public void setLength(int length) {
//        this.length = length;
//    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;

    }
    // pageNum*length+x=start
    //when pageNum ==1   -->start 0
    //  1*10+x=0  --> x=-10
    //pageNum*length-length=start
    //pageNum=(start+length)/length;
    public  int pageNum(){
        return (start+length)/length;
    }
}
