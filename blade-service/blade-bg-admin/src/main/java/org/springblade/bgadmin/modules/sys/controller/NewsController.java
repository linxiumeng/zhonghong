package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "新闻表", description = " * @author jinzeze")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:news:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = newsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:news:info")
    public R info(@PathVariable("id") Integer id) {
            NewsEntity news = newsService.getById(id);

        return R.ok().put("news", news);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:news:save")
    public R save(@RequestBody NewsEntity news) {
            newsService.save(news);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("sys:news:update")
    @ApiOperation(value = "修改", notes = "")
    public R update(@RequestBody NewsEntity news) {
        //ValidatorUtils.validateEntity(news);
            newsService.updateById(news);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:news:delete")
    public R delete(@RequestBody Integer[] ids) {
            newsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
