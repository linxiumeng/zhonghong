package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.ArticleEntity;
import org.springblade.bgadmin.modules.sys.service.ArticleService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 帮助配置表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/article")
@Api(tags = "帮助配置表", description = " * @author jinzeze")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:article:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:article:info")
    public R info(@PathVariable("id") Integer id) {
            ArticleEntity article = articleService.getById(id);

        return R.ok().put("article", article);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:article:save")
    public R save(@RequestBody ArticleEntity article) {
            articleService.save(article);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:article:update")
    public R update(@RequestBody ArticleEntity article) {
        //ValidatorUtils.validateEntity(article);
            articleService.updateById(article);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:article:delete")
    public R delete(@RequestBody Integer[] ids) {
            articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
