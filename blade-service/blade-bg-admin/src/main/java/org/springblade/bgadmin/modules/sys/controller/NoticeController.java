package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.NoticeEntity;
import io.finepetro.modules.sys.form.NoticeForm;
import io.finepetro.modules.sys.service.NoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 通知表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("sys:notice:list")
    public R list(@RequestBody NoticeForm noticeForm) {
      //  PageUtils page = noticeService.queryPage(params);

        Page<NoticeEntity> page = new Page<>(noticeForm.getPage(),noticeForm.getSize());

        Wrapper wrapper = new EntityWrapper();

        if(StringUtils.isNotBlank(noticeForm.getKeywords())){
            wrapper.eq("title",noticeForm.getKeywords());
        }

        if(noticeForm.getStatus() != null){
            wrapper.eq("is_open",noticeForm.getStatus());
        }

        if(noticeForm.getStartDate() != null){
            wrapper.gt("create_date",noticeForm.getStartDate());
        }

        if(noticeForm.getEndDate() != null){
            wrapper.lt("create_date",noticeForm.getEndDate());
        }


        page = noticeService.selectPage(page,wrapper.orderBy("is_open",false).orderBy("create_date",false));

        return R.ok().put("result", page);
    }


    /**
     * 信息
     */
    @RequestMapping("detail")
  //  @RequiresPermissions("sys:notice:info")
    public R info(@RequestBody NoticeEntity noticeEntity) {
        if(noticeEntity.getId() != null) {
            NoticeEntity notice = noticeService.selectById(noticeEntity.getId());

            return R.ok().put("notice", notice);
        }
        return R.error();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("sys:notice:save")
    public R save(@RequestBody NoticeEntity notice) {
            noticeService.insert(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
 //   @RequiresPermissions("sys:notice:update")
    public R update(@RequestBody NoticeEntity notice) {
        ValidatorUtils.validateEntity(notice);
        noticeService.updateById(notice);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("sys:notice:delete")
    public R delete(@RequestBody Integer[] ids) {
            noticeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
