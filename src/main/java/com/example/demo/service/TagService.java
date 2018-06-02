package com.example.demo.service;

import com.example.demo.model.Tag;

import java.util.List;

/**
 * Created by wdg on 2018/5/27.
 */
public interface TagService {
   List<Tag> searchTags(String keyword);
}
