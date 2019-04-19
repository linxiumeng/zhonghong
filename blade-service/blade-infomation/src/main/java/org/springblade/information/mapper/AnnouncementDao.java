package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.Announcement;

/**
 * (Announcement)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-12 10:46:58
 */
 @Mapper
public interface AnnouncementDao extends BaseMapper<Announcement> {

}