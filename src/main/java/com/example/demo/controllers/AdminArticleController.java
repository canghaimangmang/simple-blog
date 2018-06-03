package com.example.demo.controllers;

import com.example.demo.command.AddArticleCommand;
import com.example.demo.command.ListArticleCommand;
import com.example.demo.command.UpdateArticleCommand;
import com.example.demo.model.Article;
import com.example.demo.model.Tag;
import com.example.demo.model.UserInfo;
import com.example.demo.service.ArticleService;
import com.example.demo.util.AdminUtils;
import com.example.demo.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdg on 2018/5/26.
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/index")
    public String index(){
        return "/admin/article/index";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String add(){
        return "/admin/article/add";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageBean list(ListArticleCommand cmd){
       PageBean page= articleService.list(cmd);
        return page;
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Map add(HttpSession session, AddArticleCommand cmd){
        Map map = new HashMap();

        UserInfo user  = (UserInfo) session.getAttribute("user");
        cmd.setUserId(user.getId());

        Article article = articleService.add(cmd);

        if(article == null || article.getId() ==null){
            map.put("status",101);
            map.put("message","文章保存失败！");

        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model){
        Article article = articleService.findById(id);
        List<Tag> tagList = articleService.queryTagsById(id);
        model.addAttribute("article",article);
        model.addAttribute("tagList",tagList);
        return "/admin/article/edit";
    }

    @RequestMapping("/editSave")
    @ResponseBody
    public Map editSave(UpdateArticleCommand cmd){

        Article article1 = articleService.updateArticle(AdminUtils.getUserInfo(),cmd);

        return new HashMap();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(Long id){
        articleService.deleteById(id);
        return new HashMap();
    }
}


