package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.NewsComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentMapper {

    //查看一级评论
    List<NewsComment> selectTopByNewsId(@Param("news_id") Integer news_id);

    List<NewsComment> selectSecById(@Param("news_id") Integer news_id, @Param("pid") Integer pid);

    NewsComment selectByKey(@Param("id") Integer id);

    void deleteByNewsId(@Param("news_id") Integer news_id);

    void insert(@Param("content") String content, @Param("user_id") String user_id, @Param("reply_user_id") String reply_user_id, @Param("pid") Integer pid, @Param("news_id") Integer news_id);

}
