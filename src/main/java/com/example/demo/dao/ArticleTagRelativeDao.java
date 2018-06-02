package com.example.demo.dao;

import com.example.demo.model.Article;
import com.example.demo.model.ArticleTagRelative;

import java.util.List;

/**
 * Created by wdg on 2018/6/2.
 */
public interface ArticleTagRelativeDao {
    List<ArticleTagRelative> queryTagsByArticle(Article article);

    void addArticleTagRelative(ArticleTagRelative articleTagRelative);

    void deleteByArticle(Article article);
}
