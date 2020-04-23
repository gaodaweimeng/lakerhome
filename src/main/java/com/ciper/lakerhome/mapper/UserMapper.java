package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Delete({
        "delete from User",
        "where email = #{email,jdbcType=VARCHAR}"
    })
    void deleteByPrimaryKey(String email);

    @Insert({
        "insert into User (email, password, ",
        "tel)",
        "values (#{email,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{tel,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "email, password, tel",
        "from User",
        "where email = #{email,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectByPrimaryKey(@Param("email")String email);

    @Select({
        "select",
        "email, password, tel",
        "from User"
    })
    @Results({
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update User",
        "set password = #{password,jdbcType=VARCHAR},",
          "tel = #{tel,jdbcType=VARCHAR}",
        "where email = #{email,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);

    int UpdateUser(String email);
    User SelectByUserId(@Param("email") String email);

    //登录查询
    User Login(@Param("email")String email, @Param("password")String password);
    void Register(@Param("email") String email, @Param("password")String password, @Param("tel")String tel);

}