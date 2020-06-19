package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.Product;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    @Delete({
        "delete from Product",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    void deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into Product (name, ",
        "brand, color, price, ",
        "owner, ",
        "place)",
        "values (#{name,jdbcType=VARCHAR}, ",
        "#{brand,jdbcType=VARCHAR}, #{color,jdbcType=VARCHAR}, #{price,jdbcType=INTEGER}, ",
        "#{owner,jdbcType=VARCHAR},",
        "#{place,jdbcType=VARCHAR})"
    })
    void insert(@Param("name") String name, @Param("brand") String brand, @Param("color") String color, @Param("price") Integer price, @Param("owner") String owner, @Param("place") String place);

    @Select({
        "select",
        "Id, name, brand, color, price, deal_state, owner, place",
        "from Product",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="brand", property="brand", jdbcType=JdbcType.VARCHAR),
        @Result(column="color", property="color", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="deal_state", property="dealState", jdbcType=JdbcType.INTEGER),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR)
    })
    Product selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, name, brand, color, price, deal_state, owner, place",
        "from Product where deal_state = 0"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="brand", property="brand", jdbcType=JdbcType.VARCHAR),
        @Result(column="color", property="color", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="deal_state", property="dealState", jdbcType=JdbcType.INTEGER),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR)
    })
    List<Product> selectAll();

    @Select({
            "select",
            "Id, name, brand, color, price, deal_state, owner, place",
            "from Product"
    })
    @Results({
            @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="brand", property="brand", jdbcType=JdbcType.VARCHAR),
            @Result(column="color", property="color", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
            @Result(column="deal_state", property="dealState", jdbcType=JdbcType.INTEGER),
            @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
            @Result(column="place", property="place", jdbcType=JdbcType.VARCHAR)
    })
    List<Product> selectAllProduct();

    @Update({
        "update Product",
        "set name = #{name,jdbcType=VARCHAR},",
          "brand = #{brand,jdbcType=VARCHAR},",
          "color = #{color,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=INTEGER},",
          "deal_state = #{dealState,jdbcType=INTEGER},",
          "owner = #{owner,jdbcType=VARCHAR},",
          "place = #{place,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);

    @Update({
            "update Product",
            "set deal_state = #{dealState,jdbcType=INTEGER}",
            "where Id = #{id,jdbcType=INTEGER}"
    })
    void updateDealStateByProductId(@Param("id") Integer id, @Param("dealState") Integer dealState);

    List<Product> selectByName(@Param("name") String name);

    List<Product> selectByOwner(@Param("owner") String owner);

    List<Product> selectShoppingCart(@Param("user_id") String user_id);

    List<Product> cartCheckout(@Param("user_id") String user_id);

    List<Product> selectByDealState(@Param("owner") String owner, @Param("dealState") Integer dealState);

    void modifyProduct(@Param("name") String name, @Param("brand") String brand, @Param("color") String color, @Param("price") Integer price, @Param("place") String place, @Param("id") Integer id);

}