package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.Shoppingcart;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingcartMapper {
    @Delete({
        "delete from ShoppingCart",
        "where product_id = #{ProductId,jdbcType=INTEGER}"
    })
    void deleteByProductId(@Param("ProductId") Integer ProductId);

    @Insert({
        "insert into ShoppingCart (user_id, ",
        "product_id)",
        "values (#{userId,jdbcType=VARCHAR}, ",
        "#{productId,jdbcType=INTEGER})"
    })
    void insert(@Param("userId") String userId, @Param("productId") Integer productId);

    @Select({
        "select",
        "Id, user_id, product_id",
        "from ShoppingCart",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER)
    })
    Shoppingcart selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, user_id, product_id",
        "from ShoppingCart"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER)
    })
    List<Shoppingcart> selectAll();

    @Update({
        "update ShoppingCart",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "product_id = #{productId,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Shoppingcart record);


    @Select({
            "select",
            "Id, user_id, product_id",
            "from ShoppingCart",
            "where product_id = #{productId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER)
    })
    Shoppingcart selectByProductId(@Param("productId") Integer productId);
}