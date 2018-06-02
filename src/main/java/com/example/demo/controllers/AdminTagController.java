package com.example.demo.controllers;

import com.example.demo.model.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdg on 2018/5/27.
 */
@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    TagService tagService;

    @RequestMapping("/list")
    @ResponseBody
    public Map searchTags(@RequestParam(value = "term",required = false) String term){
        Map results = new HashMap();
        List list = new ArrayList();
        
        List<Tag> tagList =  tagService.searchTags(term);

        for (Tag tag : tagList) {
            Map ret = new HashMap();
            ret.put("id", tag.getId());
            ret.put("text",tag.getName());
            list.add(ret);
        }
        results.put("results",list);
        return  results;
    }
}
