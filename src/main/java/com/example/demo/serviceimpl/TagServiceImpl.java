package com.example.demo.serviceimpl;

import com.example.demo.dao.TagDao;
import com.example.demo.model.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wdg on 2018/5/27.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> searchTags(String keyword) {
        return tagDao.searchTags(keyword);
    }
}
