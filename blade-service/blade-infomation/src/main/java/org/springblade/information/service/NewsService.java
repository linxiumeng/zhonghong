package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.News;

import java.util.Date;

/**
 * @author hanbin
 * 新闻service
 */
public interface NewsService extends IService<News> {

    /**
     * 增加阅读数
     * @param id
     * @return
     */
    boolean increaseViews(Long id);


    /**
     * 获取新闻最大的时间
     * @param type
     * @return
     */
    Date selectMaxDateByType(int type);

    /**
     * 从缓存中捞取数据
     * @param id
     * @return
     */
    News selectByIdFromCache(Long id);

    /**
     * 缓存列表 具体步骤（id列表 - > mget操作） todo 未解决increView问题 改造成hash
     * @param page
     * @return
     */
    Page<News> selectPageFromCache(Page page);

    /**
     * 不用缓存查询分页
     * @param page
     * @return
     */
    Page<News> selectPageNoCache(Page page);


}
