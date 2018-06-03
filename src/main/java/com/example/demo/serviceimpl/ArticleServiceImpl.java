package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.command.AddArticleCommand;
import com.example.demo.command.ListArticleCommand;
import com.example.demo.command.ListHomeArticleCommand;
import com.example.demo.command.UpdateArticleCommand;
import com.example.demo.dao.ArticleTagRelativeDao;
import com.example.demo.dao.TagDao;
import com.example.demo.model.ArticleTagRelative;
import com.example.demo.model.Tag;
import com.example.demo.model.UserInfo;
import com.example.demo.vo.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;

import javax.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	public ArticleDao articleDao;

	@Autowired
	private ArticleTagRelativeDao articleTagRelativeDao;

	@Autowired
	private  TagDao tagDao;
	
	@Override
	public List<Article> queryArticle(Integer userId) {
		
		return articleDao.queryArticle(userId);
	}

	@Transactional
	@Override
	public Article add(AddArticleCommand cmd) {

		return articleDao.add(cmd);
	}


	@Override
	public PageBean list(ListArticleCommand cmd) {
		return articleDao.list(cmd);
	}

	@Override
	public Article findById(Long id) {
		return articleDao.findById(id);
	}

	@Override
	public List<Tag> queryTagsById(Long id) {
		Article article=articleDao.findById(id);
		List<ArticleTagRelative> list = articleTagRelativeDao.queryTagsByArticle(article);
		List<Tag> tagList = new ArrayList<Tag>();
		for (ArticleTagRelative re:list) {
			tagList.add(re.getTag());
		}
		return tagList;
	}

	@Override
	@Transactional
	public Article updateArticle(UserInfo login, UpdateArticleCommand cmd) {
		Article article = new Article();
		BeanUtils.copyProperties(cmd,article);
		article.setUserId(login.getId());
		article.setAuthor(login.getNickname());
		article.setUpdatedDate(new Date());
		articleTagRelativeDao.deleteByArticle(article);
		for (Long tagId:cmd.getTags()) {
			ArticleTagRelative articleTagRelative = new ArticleTagRelative();

			Tag tag = tagDao.getTag(tagId);

			articleTagRelative.setTag(tag);
			articleTagRelative.setArticle(article);

			articleTagRelativeDao.addArticleTagRelative(articleTagRelative);
		}






		return articleDao.updateArticle(article);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Article article =articleDao.findById(id);
		articleTagRelativeDao.deleteByArticle(article);

		articleDao.deleteArticle(article);
	}

	@Override
	public PageBean queryArticleList(ListHomeArticleCommand cmd) {

		return articleDao.queryArticleList(cmd);
	}

}
