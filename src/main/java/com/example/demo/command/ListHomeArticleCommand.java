package com.example.demo.command;

/**
 * Created by wdg on 2018/6/2.
 */
public class ListHomeArticleCommand extends BaseCommand{
    Long tagId;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
