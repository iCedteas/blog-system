package com.blog.service.article;

import com.blog.domain.entity.article.Article;

import java.util.List;

/**
 * 文章service
 *
 * @author ssSally111
 * @date 2023/3/12
 */
public interface IArticle
{
    /**
     * 查询文章列表
     *
     * @return {@link List}<{@link Article}>
     */
    List<Article> queryArticleList();
}
