package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.UserStarsConnect;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarsConnectMapper {
    @Delete({
        "delete from User_Stars_Connect",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into User_Stars_Connect (Id, user_id, ",
        "stars_id)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{starsId,jdbcType=INTEGER})"
    })
    int insert(UserStarsConnect record);

    @Select({
        "select",
        "Id, user_id, stars_id",
        "from User_Stars_Connect",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    UserStarsConnect selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, user_id, stars_id",
        "from User_Stars_Connect"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER)
    })
    List<UserStarsConnect> selectAll();

    @Update({
        "update User_Stars_Connect",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "stars_id = #{starsId,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserStarsConnect record);
}