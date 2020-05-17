package com.ciper.lakerhome.mapper;

import com.ciper.lakerhome.entity.NoteComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteCommentMapper {
    List<NoteComment> selectTopByNoteId(@Param("note_id") Integer note_id);

    List<NoteComment> selectSecById(@Param("note_id") Integer note_id, @Param("pid") Integer pid);

    NoteComment selectByKey(@Param("id") Integer id);

    void insert(@Param("content") String content, @Param("user_id") String user_id, @Param("reply_user_id") String reply_user_id, @Param("pid") Integer pid, @Param("note_id") Integer note_id);
}
