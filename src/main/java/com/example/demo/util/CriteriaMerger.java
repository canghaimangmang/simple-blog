package com.example.demo.util;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public  class CriteriaMerger<T,V>{
    CriteriaQuery<V> query;
    Root<T> path;

    public CriteriaQuery<V> getQuery() {
        return query;
    }

    public void setQuery(CriteriaQuery<V> query) {
        this.query = query;
    }

    public Root<T> getPath() {
        return path;
    }

    public void setPath(Root<T> path) {
        this.path = path;
    }
}