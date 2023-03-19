package com.blog.api.article;

import com.blog.common.core.controller.BaseController;
import com.blog.common.core.page.TableDataInfo;
import com.blog.service.article.IArticle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章api
 *
 * @author ssSally111
 * @date 2023/3/19
 */
@RestController
@RequestMapping("blog/article")
public class ApiArticleController extends BaseController
{
    @Resource
    private IArticle iArticle;

    @GetMapping("list")
    public TableDataInfo queryArticleList() {
        startPage();
        return getDataTable(iArticle.queryArticleList());
    }
}
