package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springblade.bgadmin.modules.sys.entity.BannerEntity;

/**
 * 广告图表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface BannerDao extends BaseMapper<BannerEntity> {

    /**
     * 插入返回携带实体id
     * @param banner
     * @return
     */
    @Insert("insert into tb_banner(`logo`,`path`,is_open,`sort`) values(#{logo},#{path},#{isOpen},#{sort})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertReturnWithId(BannerEntity banner);

}
