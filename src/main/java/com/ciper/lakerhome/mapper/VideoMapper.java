package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.Video;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {
    @Delete({
        "delete from Video",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    void deleteByPrimaryKey(@Param("id") Integer id);

    @Insert({
        "insert into Video (title, ",
        "place, stars_id)",
        "values (#{title,jdbcType=VARCHAR}, ",
        "#{place,jdbcType=VARCHAR}, #{starsId,jdbcType=INTEGER})"
    })
    void insert(@Param("title") String title, @Param("place") String place, @Param("starsId") Integer starsId);

    @Select({
        "select",
        "Id, title, place, stars_id",
        "from Video",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    Video selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, title, place, stars_id",
        "from Video"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    List<Video> selectAll();

    @Update({
        "update Video",
        "set title = #{title,jdbcType=VARCHAR},",
          "place = #{place,jdbcType=VARCHAR},",
          "stars_id = #{starsId,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Video record);

    List<Video> selectByStarsName(@Param("stars_name") String stars_name);
}