package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.Stars;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface StarsMapper {
    @Delete({
        "delete from Stars",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into Stars (Id, name)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Stars record);

    @Select({
        "select",
        "Id, name",
        "from Stars",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Stars selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, name",
        "from Stars"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Stars> selectAll();

    @Update({
        "update Stars",
        "set name = #{name,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Stars record);


    @Select({
            "select * from Stars where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Stars selectByStarsName(@Param("name") String name);
}