package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.NewsEntity;
import org.springblade.bgadmin.modules.sys.service.NewsService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 新闻表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:news:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = newsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:news:info")
    public R info(@PathVariable("id") Integer id) {
            NewsEntity news = newsService.getById(id);

        return R.ok().put("news", news);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:news:save")
    public R save(@RequestBody NewsEntity news) {
            newsService.save(news);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:news:update")
    public R update(@RequestBody NewsEntity news) {
        //ValidatorUtils.validateEntity(news);
            newsService.updateById(news);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:news:delete")
    public R delete(@RequestBody Integer[] ids) {
            newsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
