package com.FDSC.mapper;

import com.FDSC.controller.dto.SlideShowDto;
import com.FDSC.entity.Announcement;
import com.FDSC.mapper.dto.AnnounceShowTempDto;
import com.FDSC.mapper.dto.StoryTempDto;
import com.FDSC.utils.SqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AnnounceMapper extends BaseMapper<Announcement> {

    @Select("Select a.id as announce_id, a.cover_url from slideshow as s, announcement as a " +
            "where s.announcement_id=a.id and a.is_activity=1")
    public List<SlideShowDto> getSlideShow();

    @SelectProvider(type = SqlProvider.class, method = "announce")
    public List<Announcement> getAllAnnounce(@Param("page") Integer page);

    @Select("Select COUNT(1) from announcement WHERE is_activity = 0")
    public Integer getAnnounceNum();

    @Select("Select id, title, create_time \n" +
            "from announcement \n" +
            "WHERE is_activity = 0 \n"+
            "ORDER BY update_time DESC\n" +
            "LIMIT 2")
    public List<AnnounceShowTempDto> getTwoAnnounce();

    @Select("Select * FROM announcement WHERE id = #{id}")
    public Announcement getAnnounce(Integer id);

}
