package com.blog.mapper.article;

import com.blog.domain.entity.article.Article;

/**
 * 文章映射器
 *
 * @author ssSally111
 * @description 针对表【blog_article(文章表)】的数据库操作Mapper
 * @Entity com.blog.system.domain.entity.article.Article
 * @date 2023/03/12
 */
public interface ArticleMapper
{
    /**
     * 按主键删除
     *
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     *
     * @param article 文章
     * @return int
     */
    int insert(Article article);

    /**
     * 插入选择性
     *
     * @param article 文章
     * @return int
     */
    int insertSelective(Article article);

    /**
     * 查询通过主键
     *
     * @param id id
     * @return {@link Article}
     */
    Article selectByPrimaryKey(Long id);

    /**
     * 更新通过主键选择性
     *
     * @param article 文章
     * @return int
     */
    int updateByPrimaryKeySelective(Article article);

    /**
     * 更新通过主键
     *
     * @param article 文章
     * @return int
     */
    int updateByPrimaryKey(Article article);

}
