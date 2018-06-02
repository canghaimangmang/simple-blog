package com.example.demo.daoimpl;

import com.example.demo.dao.TagDao;
import com.example.demo.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by wdg on 2018/5/27.
 */
@Repository
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Tag> searchTags(String keyword) {
        if(keyword == null){
            keyword = "";
        }
        String hql = "from Tag where name like ?";
        Query query = entityManager.createQuery(hql);
        query.setParameter(0,"%"+keyword +"%");
        List<Tag> list =query.getResultList();
        return list;

    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = entityManager.find(Tag.class,id);
        return tag;
    }


}
