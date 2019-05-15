package org.springblade.information.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.Faq;
import org.springblade.common.form.FaqForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.FaqService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanbin
 * 常见问题 控制层
 */
@RestController
@RequestMapping("/api/faq")
@Api(tags = "常见问题操作控制器（faq）", description = "帅泽泽")
public class FaqController {

    @Resource
    FaqService faqService;

    @PostMapping("/detail")
    @ApiOperation(value = "问题详情", notes = "" )
    public R detail(@RequestBody FaqForm faqForm){
        Long id = faqForm.getId();
        if(id == null){
            return R.error("id参数缺失");
        }
        return R.ok().put("result",faqService.getById(id));
    }

    @PostMapping("list")
    @ApiOperation(value = "问题列表", notes = "" )
    public R list(@RequestBody FaqForm faqForm){

        IPage<Faq> faqIPage = new Page<>(faqForm.getPage(),faqForm.getSize());
        QueryWrapper<Faq> wrapper = Wrappers.<Faq>query();
        wrapper.eq("is_open",1);
        IPage page = faqService.page(faqIPage,wrapper);

        return R.ok().put("result",page);
    }




}
