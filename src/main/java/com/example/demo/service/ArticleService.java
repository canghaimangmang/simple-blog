package com.example.demo.service;

import java.util.List;

import com.example.demo.command.AddArticleCommand;
import com.example.demo.command.ListArticleCommand;
import com.example.demo.command.ListHomeArticleCommand;
import com.example.demo.command.UpdateArticleCommand;
import com.example.demo.model.Article;
import com.example.demo.model.Tag;
import com.example.demo.model.UserInfo;
import com.example.demo.vo.PageBean;

public interface ArticleService {
	public List<Article> queryArticle(Integer userId);


    Article add(AddArticleCommand cmd);

    PageBean list(ListArticleCommand cmd);

    Article findById(Long id);


    List<Tag> queryTagsById(Long id);

    Article updateArticle(UserInfo login, UpdateArticleCommand cmd);

    void deleteById(Long id);

    PageBean queryArticleList(ListHomeArticleCommand cmd);
}
