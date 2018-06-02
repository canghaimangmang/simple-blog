package com.example.demo.daoimpl;

import com.example.demo.dao.ArticleTagRelativeDao;
import com.example.demo.model.Article;
import com.example.demo.model.ArticleTagRelative;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by wdg on 2018/6/2.
 */
@Repository
public class ArticleTagRelativeDaoImpl implements ArticleTagRelativeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ArticleTagRelative> queryTagsByArticle(Article article) {
        String hql = "from ArticleTagRelative where article=?";
       Query query = entityManager.createQuery(hql);
       query.setParameter(0,article);
        List<ArticleTagRelative> list = query.getResultList();
        return list;
    }

    @Override
    public void  addArticleTagRelative(ArticleTagRelative articleTagRelative) {

       entityManager.persist(articleTagRelative);

    }

    @Override
    public void deleteByArticle(Article article) {

        List<ArticleTagRelative> list =  queryTagsByArticle(article);

        for (ArticleTagRelative relative:list) {
            entityManager.remove(relative);
        }
    }
}
