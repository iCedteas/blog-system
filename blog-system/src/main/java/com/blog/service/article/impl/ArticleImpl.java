package com.blog.service.article.impl;

import com.blog.domain.entity.article.Article;
import com.blog.mapper.article.ArticleMapper;
import com.blog.service.article.IArticle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章
 *
 * @author ssSally111
 * @date 2023/3/12
 */
@Service
public class ArticleImpl implements IArticle
{
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 查询文章列表
     *
     * @return {@code List<Article> }
     */
    @Override
    public List<Article> queryArticleList() {
        return articleMapper.selectArticleList();
    }
}
