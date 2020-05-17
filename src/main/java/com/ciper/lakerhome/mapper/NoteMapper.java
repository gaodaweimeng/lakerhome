package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.Note;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteMapper {
    @Select({
            "select",
            "Id, title, content, user_id",
            "from Note"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="user_id", jdbcType=JdbcType.INTEGER)
    })
    List<Note> selectAll();

    @Select({
            "select",
            "Id, title, content, user_id",
            "from Note",
            "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="user_id", jdbcType=JdbcType.INTEGER)
    })
    Note selectByKey(@Param("id") Integer id);

    @Insert({
            "insert into Note (title, ",
            "content, user_id)",
            "values (#{title,jdbcType=VARCHAR}, ",
            "#{content,jdbcType=VARCHAR}, #{user_id,jdbcType=VARCHAR})"
    })
    void insert(@Param("title") String title, @Param("content") String content, @Param("user_id")String user_id);
}
