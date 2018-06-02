package com.example.demo.vo;

import java.util.List;

/**
 * Created by wdg on 2018/5/29.
 */
public class PageBean<T> {

    List<T>  data;
    Long recordsTotal;
    Long recordsFiltered;
    int draw;


    public PageBean() {
    }

    public PageBean(List<T> data, Long recordsTotal) {
        this.data = data;
        this.recordsTotal = recordsTotal;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getDraw() {
        return draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
