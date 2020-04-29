package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.NewsComments;
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
public interface NewsCommentsMapper {
    @Delete({
        "delete from News_Comments",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into News_Comments (Id, user_id, ",
        "news_id, reply_user_id, ",
        "comment_msg, create_time)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{newsId,jdbcType=INTEGER}, #{replyUserId,jdbcType=VARCHAR}, ",
        "#{commentMsg,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(NewsComments record);

    @Select({
        "select",
        "Id, user_id, news_id, reply_user_id, comment_msg, create_time",
        "from News_Comments",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER),
        @Result(column="reply_user_id", property="replyUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment_msg", property="commentMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    NewsComments selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, user_id, news_id, reply_user_id, comment_msg, create_time",
        "from News_Comments"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER),
        @Result(column="reply_user_id", property="replyUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment_msg", property="commentMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<NewsComments> selectAll();

    @Update({
        "update News_Comments",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "news_id = #{newsId,jdbcType=INTEGER},",
          "reply_user_id = #{replyUserId,jdbcType=VARCHAR},",
          "comment_msg = #{commentMsg,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(NewsComments record);
}