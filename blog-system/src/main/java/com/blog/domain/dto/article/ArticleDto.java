package com.blog.domain.dto.article;

import com.blog.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章表
 *
 * @author ssSally111
 * @TableName blog_article
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends BaseEntity
{
    /**
     * 主键
     */
    private Long id;

    /**
     * 标签
     */
    private String tag;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 是否置顶:0否,1是
     */
    private Boolean isTop;

    /**
     * 文章评论数量
     */
    private Integer commentCount;

    /**
     * 文章浏览数量
     */
    private Integer browseCount;

    /**
     * 创建人userId
     */
    private Long createById;

    private static final long serialVersionUID = 1L;
}