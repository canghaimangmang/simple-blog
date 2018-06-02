package com.example.demo.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by wdg on 2018/5/29.
 */
public class CriteriaUtils {


    public static <V> CriteriaMerger<V,V> create(CriteriaBuilder cb, Class<V> entityClass){
        CriteriaQuery<V> query= cb.createQuery(entityClass);

        Root<V> from=query.from(entityClass);

        query.select(from);
        CriteriaMerger<V,V> c=new CriteriaMerger<>();
        c.path=from;
        c.query=query;
        return c;
    }
    public static <V> CriteriaMerger<V,Long> createCount(CriteriaBuilder cb, Class<V> entityClass){
        CriteriaQuery<Long> query= cb.createQuery(Long.class);
        Root<V> from=query.from(entityClass);
        query.select(cb.count(from));
        CriteriaMerger<V,Long> x=new CriteriaMerger<>();
        x.setPath(from);
        x.setQuery(query);
        return x;
    }
}
