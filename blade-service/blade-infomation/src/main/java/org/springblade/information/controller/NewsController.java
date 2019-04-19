package org.springblade.information.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.News;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.PageForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hanbin
 * 新闻controller
 */
@Api(tags = "新闻表控制器", description = " * @author hanbin")
@RestController
@RequestMapping("api/news")
public class NewsController {


    @Resource
    NewsService newsService;

    /**
     * 获取文章详情控制器
     *
     * @param id 文章id
     * @return
     */
    @Login
    @PostMapping("detail")
    @ApiOperation(value="获取文章详情接口")
    public R getNewDetail(@RequestParam("id") Long id, @LoginUser UserEntity userEntity) {

        System.out.println("new detail's userEntity is "+userEntity);

        if (id == null) {
            return R.error("id 为 null");
        }
        News news = newsService.selectByIdFromCache(id);
        //增加浏览数量
        newsService.increaseViews(id);
        return R.ok().put("row", news);
    }

    /**
     * 获取文章列表
     *
     * @param pageForm 分页参数
     * @return
     */
    @PostMapping("list")
    @ApiOperation("分页获取文章列表")
    public R listNewsByPage(@RequestBody PageForm pageForm) {
        Page<News> page = new Page<>(pageForm.getPage(), pageForm.getSize());

        //缓存批量获取返回
      //  Page<News> newsPage = newsService.selectPageFromCache(page);
        Page<News> newsPage = newsService.selectPageNoCache(page);
        return R.ok().put("result", newsPage);
    }


}
