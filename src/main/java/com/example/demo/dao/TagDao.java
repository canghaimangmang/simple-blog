package com.example.demo.dao;

import com.example.demo.model.Tag;

import java.util.List;

/**
 * Created by wdg on 2018/5/27.
 */
public interface TagDao {
    List<Tag> searchTags(String keyword);

    Tag getTag(Long id);
}
