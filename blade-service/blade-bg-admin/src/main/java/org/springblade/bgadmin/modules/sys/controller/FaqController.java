package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.modules.sys.entity.FaqEntity;
import io.finepetro.modules.sys.form.FaqForm;
import io.finepetro.modules.sys.service.FaqService;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/sys/faq")
public class FaqController {

    @Resource
    FaqService faqService;

    @PostMapping("/detail")
    public R detail(@RequestBody FaqForm faqForm){
        Long id = faqForm.getId();
        if(id == null){
            return R.error("id参数缺失");
        }
        return R.ok().put("result",faqService.selectById(id));
    }

    @PostMapping("list")
    public R list(@RequestBody FaqForm faqForm){

        return R.ok().put("result",faqService.listFaq(
                new Page(faqForm.getPage(),faqForm.getSize()),
                faqForm.getStartDate(),
                faqForm.getEndDate(),
                faqForm.getKeywords()));
    }

    @PostMapping("update")
    public R update(@RequestBody FaqForm faqForm){

        Long id = faqForm.getId();
        Integer status = faqForm.getIsOpen();

        if(id == null){
            return R.error("参数缺失");
        }

        FaqEntity faqEntity = new FaqEntity();
        BeanUtils.copyProperties(faqForm,faqEntity);

        if(status != null){
            faqEntity.setIsOpen(status);
        }

        return R.ok().put("result",faqService.updateById(faqEntity));
    }

    @PostMapping("delete")
    public R delete(@RequestBody FaqForm faqForm){
        Long id = faqForm.getId();
        if(id == null){
            return R.error("ID 缺失");
        }
        return R.ok().put("result",faqService.deleteById(id));
    }

    @PostMapping("save")
    public R save(@RequestBody FaqForm faqForm){

        FaqEntity faqEntity = new FaqEntity();
        BeanUtils.copyProperties(faqForm,faqEntity);

        return R.ok().put("result",faqService.insert(faqEntity));
    }



}
