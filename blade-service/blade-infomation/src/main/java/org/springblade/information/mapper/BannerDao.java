package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springblade.common.entity.Banner;

/**
 * (Banner)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-06 18:32:12
 */
 @Mapper
public interface BannerDao extends BaseMapper<Banner> {

 /**
  * 插入返回携带实体id
  * @param banner
  * @return
  */
 @Insert("insert into tb_banner(`logo`,`path`,is_open,`sort`) values(#{logo},#{path},#{isOpen},#{sort})")
 @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertReturnWithId(Banner banner);

}