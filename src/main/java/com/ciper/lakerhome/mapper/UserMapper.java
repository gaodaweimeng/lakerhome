package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from User",
        "where email = #{email,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String email);

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
    User selectByPrimaryKey(String email);

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
}