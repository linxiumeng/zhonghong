package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import org.springblade.common.entity.News;

import java.util.Date;
import java.util.List;

/**
 * @author hanbin
 * 新闻数据库操作类
 */
@Mapper
public interface NewsDao extends BaseMapper<News> {

    /**
     * 阅读数自增保证了原子性
     * @param id
     * @return
     */
    @Update("update `tb_news` set views = views + 1 where id = ${id}")
    boolean increaseViews(@Param("id") Long id);

    @Select("select max(create_date) as max_date from tb_news where type = #{newsType}")
    Date selectMaxDateByType(@Param("newsType") int type);

    @Select("<script>select id from tb_news<where>${ew.sqlSegment}</where></script> ")
    List<String> selectIdListByPage(IPage page, @Param("ew") Wrapper wrapper);
}
