package com.example.demo.dao;

import java.util.List;

import com.example.demo.command.AddArticleCommand;
import com.example.demo.command.ListArticleCommand;
import com.example.demo.command.ListHomeArticleCommand;
import com.example.demo.model.Article;
import com.example.demo.model.Tag;
import com.example.demo.vo.PageBean;

public interface ArticleDao {
	public List<Article> queryArticle(Integer userId);

    Article add(AddArticleCommand cmd);

    PageBean list(ListArticleCommand cmd);

    Article findById(Long id);


    Article updateArticle(Article article);


    void deleteArticle(Article article);

    PageBean queryArticleList(ListHomeArticleCommand cmd);
}
