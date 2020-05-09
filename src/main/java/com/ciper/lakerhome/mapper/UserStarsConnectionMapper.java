package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.UserStarsConnection;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserStarsConnectionMapper {
    @Delete({
        "delete from User_Stars_Connection",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into User_Stars_Connection (Id, stars_id, ",
        "user_id)",
        "values (#{id,jdbcType=INTEGER}, #{starsId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=VARCHAR})"
    })
    int insert(UserStarsConnection record);

    @Select({
        "select",
        "Id, stars_id, user_id",
        "from User_Stars_Connection",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR)
    })
    UserStarsConnection selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, stars_id, user_id",
        "from User_Stars_Connection"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR)
    })
    List<UserStarsConnection> selectAll();

    @Update({
        "update User_Stars_Connection",
        "set stars_id = #{starsId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserStarsConnection record);
}