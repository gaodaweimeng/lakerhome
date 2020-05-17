package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.UserStarsConnection;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarsConnectionMapper {
    @Delete({
        "delete from User_Stars_Connection",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    void deleteByPrimaryKey(@Param("id") Integer id);

    @Insert({
        "insert into User_Stars_Connection (stars_id, ",
        "user_id)",
        "values (#{starsId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=VARCHAR})"
    })
    void insert(@Param("starsId") Integer starsId, @Param("userId") String userId);


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
    void updateByPrimaryKey(@Param("starsId") Integer starsId,@Param("userId") String userId, @Param("id") Integer id);

    @Select({
            "select",
            "Id, stars_id, user_id",
            "from User_Stars_Connection",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="stars_id", property="starsId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR)
    })
    List<UserStarsConnection> selectByUserId(@Param("userId") String userId);
}