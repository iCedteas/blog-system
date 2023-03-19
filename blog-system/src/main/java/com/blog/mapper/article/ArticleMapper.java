package com.blog.mapper.article;

import com.blog.domain.entity.article.Article;

import java.util.List;

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
     * 删除通过id
     *
     * @param id id
     * @return int
     */
    int deleteById(Long id);

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
     * 查询通过id
     *
     * @param id id
     * @return {@code Article } 结果
     */
    Article selectById(Long id);

    /**
     * 更新通过主键选择性
     *
     * @param article 文章
     * @return int
     */
    int updateByIdSelective(Article article);

    /**
     * 更新通过主键
     *
     * @param article 文章
     * @return int
     */
    int updateById(Article article);

    /**
     * 查询文章列表
     *
     * @return {@code List<Article> }
     */
    List<Article> selectArticleList();

}
