package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.entity.NoticeEntity;
import org.springblade.bgadmin.modules.sys.form.NoticeForm;
import org.springblade.bgadmin.modules.sys.service.NoticeService;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "通知表", description = " * @author jinzeze")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @PostMapping("/list")
   // @RequiresPermissions("sys:notice:list")
    @ApiOperation(value = "列表", notes = "")
    public R list(@RequestBody NoticeForm noticeForm) {
      //  PageUtils page = noticeService.queryPage(params);

        IPage<NoticeEntity> page = new Page<>(noticeForm.getPage(),noticeForm.getSize());

        QueryWrapper<NoticeEntity> wrapper = new QueryWrapper();

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


        page = noticeService.page(page,wrapper.orderBy(true,false,"is_open","create_date"));

        return R.ok().put("result", page);
    }


    /**
     * 信息
     */
    @PostMapping("detail")
    @ApiOperation(value = "列表", notes = "")
  //  @RequiresPermissions("sys:notice:info")
    public R info(@RequestBody NoticeEntity noticeEntity) {
        if(noticeEntity.getId() != null) {
            NoticeEntity notice = noticeService.getById(noticeEntity.getId());

            return R.ok().put("notice", notice);
        }
        return R.error();
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "列表", notes = "")
  //  @RequiresPermissions("sys:notice:save")
    public R save(@RequestBody NoticeEntity notice) {
            noticeService.save(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "列表", notes = "")
 //   @RequiresPermissions("sys:notice:update")
    public R update(@RequestBody NoticeEntity notice) {
       // ValidatorUtils.validateEntity(notice);
        noticeService.updateById(notice);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "列表", notes = "")
  //  @RequiresPermissions("sys:notice:delete")
    public R delete(@RequestBody Integer[] ids) {
            noticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
