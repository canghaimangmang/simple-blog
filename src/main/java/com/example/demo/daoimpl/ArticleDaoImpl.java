package com.example.demo.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;

import com.example.demo.command.AddArticleCommand;
import com.example.demo.command.BaseCommand;
import com.example.demo.command.ListArticleCommand;
import com.example.demo.model.ArticleTagRelative;
import com.example.demo.model.Tag;
import com.example.demo.model.UserInfo;
import com.example.demo.util.CriteriaMerger;
import com.example.demo.util.CriteriaUtils;
import com.example.demo.vo.PageBean;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Article> queryArticle(Integer userId) {
		
		String hql = "from Article  where userId = " +userId;
		
		Query query = entityManager.createQuery(hql);
		
		List<Article> articleList = query.getResultList();
		
		return articleList;
	}

	@Override
	public Article add(AddArticleCommand cmd) {
		Article article = new Article();
		article.setTitle(cmd.getTitle());
		article.setSummary(cmd.getSummary());
		//article.setAuthor(cmd.g);
		article.setContent(cmd.getContent());
		article.setCreatedDate(cmd.getCreatedDate());
		UserInfo user  = entityManager.find(UserInfo.class,cmd.getUserId());
		article.setAuthor(user.getNickname());
		article.setUserId(user.getId());
		entityManager.persist(article);

		if(cmd.getTags() != null){
			for(Long tagId :cmd.getTags()){
				Tag tag = entityManager.find(Tag.class,tagId);
				ArticleTagRelative relative = new ArticleTagRelative();
				relative.setArticle(article);
				relative.setTag(tag);
				entityManager.persist(relative);
			}
		}


		return article;
	}

	public PageBean list2(ListArticleCommand cmd) {
		List<Object> params = new ArrayList<>();
		StringBuffer queryBuf = new StringBuffer("from Article a where 1=1 ");
		StringBuffer countQueryBuf = new StringBuffer("select count(a.id) from Article a where 1=1 ");
		StringBuffer where=new StringBuffer();
		if (cmd.getTitle() != null) {
			where.append(" and a.title like ? ");
			params.add("%"+cmd.getTitle()+"%");
		}
		if(cmd.getStartDate()!=null){
			where.append(" and a.createdDate >= ? ");
			params.add(cmd.getStartDate());
		}
		if(cmd.getEndDate()!=null){
			where.append(" and a.createdDate <= ? ");
			params.add(cmd.getEndDate());
		}
		Query countQuery = entityManager.createQuery( countQueryBuf.append(where).toString());
		Query query = entityManager.createQuery( queryBuf.append(where).toString());
		Stream.of(countQuery,query).forEach((Query q)->{
			int i=0;
			for(Object param:params){
				q.setParameter(i,param);
				i++;
			}
		});
		Long count=(Long)countQuery.getSingleResult();
		List<Article> ret=query.setFirstResult(cmd.getStart()).setMaxResults(cmd.getLength()).getResultList();
		PageBean<Article>  page=new PageBean<>(ret,count);
		return  page;
	}
	@Override
	public PageBean list(ListArticleCommand cmd) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		PageBean page= createBaseQuery((CriteriaMerger t)->{
			List<Predicate> list=new ArrayList<>();
			if(cmd.getTitle()!=null){
				list.add(cb.like(t.getPath().get("title"),"%"+cmd.getTitle()+"%"));
			}
			if(cmd.getStartDate()!=null){
				list.add(cb.greaterThanOrEqualTo(t.getPath().get("createdDate"),cmd.getStartDate()));
			}
			if(cmd.getEndDate()!=null){
				list.add(cb.lessThanOrEqualTo(t.getPath().get("createdDate"),cmd.getEndDate()));
			}
			if(!list.isEmpty()){
				t.getQuery().where(list.toArray(new Predicate[list.size()]));
			}
		},cb,Article.class,cmd);

		return page;
	}

	@Override
	public Article findById(Long id) {
		Article article = entityManager.find(Article.class,id);
		return article;
	}

	@Override
	public Article updateArticle(Article article) {
		//Article article1 = entityManager.find(Article.class,article.getId());
		//article1.setTitle(article.getTitle());
		//article1.setContent(article.getContent());
		Article article1 =entityManager.merge(article);
		return article1;
	}

	private <T> PageBean  createBaseQuery(Consumer<? super CriteriaMerger> criteriaActoin, CriteriaBuilder cb, Class<T> entityClass, BaseCommand cmd) {
		CriteriaMerger<T,Long> countMerger=CriteriaUtils.createCount(cb,entityClass);
		CriteriaMerger<T,T> merger=CriteriaUtils.create(cb,entityClass);
		Stream.of(countMerger,merger).forEach(criteriaActoin);
		Long count=entityManager.createQuery(countMerger.getQuery()).getSingleResult();
		Query query=entityManager.createQuery(merger.getQuery());
		query.setFirstResult(cmd.getStart()).setMaxResults(cmd.getLength());
		List<T> ret=query.getResultList();
		PageBean<T>  page=new PageBean<>(ret,count);
		page.setDraw(cmd.getDraw());
		page.setRecordsFiltered(count);
		return page;
	}

//	public PageBean list3(ListArticleCommand cmd) {
//
//		CriteriaMerger<Article,Long> countMerger=CriteriaUtils.createCount(cb,Article.class);
//		CriteriaMerger<Article,Article> merger=CriteriaUtils.create(cb,Article.class);
//		Stream.of(countMerger,merger).forEach((CriteriaMerger t)->{
//			List<Predicate> list=new ArrayList<>();
//			if(cmd.getTitle()!=null){
//				list.add(cb.like(t.getPath().get("title"),"%"+cmd.getTitle()+"%"));
//			}
//			if(cmd.getStartDate()!=null){
//				list.add(cb.greaterThanOrEqualTo(t.getPath().get("createdDate"),cmd.getStartDate()));
//			}
//			if(cmd.getEndDate()!=null){
//				list.add(cb.lessThanOrEqualTo(t.getPath().get("createdDate"),cmd.getEndDate()));
//			}
//			if(!list.isEmpty()){
//				t.getQuery().where(list.toArray(new Predicate[list.size()]));
//			}
//		});
//		Long count=entityManager.createQuery(countMerger.getQuery()).getSingleResult();
//		Query query=entityManager.createQuery(merger.getQuery());
//		query.setFirstResult(cmd.getStart()).setMaxResults(cmd.getLength());
//		List<Article> ret=query.getResultList();
//		PageBean<Article>  page=new PageBean<>(ret,count);
//		return page;
//	}
}
