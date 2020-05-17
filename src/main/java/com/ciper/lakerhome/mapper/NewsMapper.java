package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.News;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMapper {
    @Delete({
        "delete from News",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    void deleteByPrimaryKey(@Param("id") Integer id);

    @Insert({
        "insert into News (title, ",
        "content, stars_id)",
        "values (#{title,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{starsId,jdbcType=INTEGER})"
    })
    void insert(@Param("title")String title, @Param("content")String content, @Param("starsId")Integer starsId);

    @Select({
        "select",
        "Id, title, content, stars_id",
        "from News",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    News selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, title, content, stars_id",
        "from News"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    List<News> selectAll();

    @Update({
        "update News",
        "set title = #{title,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "stars_id = #{starsId,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(News record);

    List<News> selectByStarsName(@Param("stars_name") String stars_name);

    List<News> selectByUserId(@Param("email") String email);
}