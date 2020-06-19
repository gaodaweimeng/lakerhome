package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.Note;
import com.ciper.lakerhome.entity.NoteComment;
import com.ciper.lakerhome.mapper.NoteCommentMapper;
import com.ciper.lakerhome.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoteController {
    final NoteMapper noteMapper;

    final NoteCommentMapper noteCommentMapper;

    @Autowired
    public NoteController(NoteMapper noteMapper, NoteCommentMapper noteCommentMapper){
        this.noteMapper = noteMapper;
        this.noteCommentMapper = noteCommentMapper;
    }

    //用户查看所有的帖子
    @RequestMapping("show_all_note")
    public String show_all_note(Model model){
        List<Note> note_list = noteMapper.selectAll();
        model.addAttribute(note_list);
        return "show_all_note";
    }

    //帖子标题模糊查询
    @RequestMapping("find_note")
    public String find_note(Model model, HttpServletRequest request){
        String title = request.getParameter("title");
        List<Note> note_list = noteMapper.selectByTitleWord(title);
        model.addAttribute(note_list);
        return "show_all_note";
    }

    //根据帖子类型查看
    @RequestMapping("show_type_note")
    public String show_type_note(Model model,HttpServletRequest request) {
        String type = request.getParameter("type");
        List<Note> note_list = noteMapper.selectByType(type);
        model.addAttribute(note_list);
        return "show_all_note";
    }

    //查看帖子——详情查看
    @RequestMapping("show_select_note/{id}")
    public String show_select_note(Model model, @PathVariable("id") Integer id){
        Note note = noteMapper.selectByKey(id);
        model.addAttribute("note",note);

        //查询一级评论
        List<NoteComment> comment_list = noteCommentMapper.selectTopByNoteId(id);
        model.addAttribute("top_comment", comment_list);

        return "show_select_note";
    }

    //帖子评论——查看二级评论
    @RequestMapping("note_show_sec_comment/{sec_id}")
    public String note_show_sec_comment(@PathVariable("sec_id") Integer id, Model model, HttpSession session){
        //id 为一级评论的主键
        //如何获取一级评论的所有二级评论 ->>两个参数(帖子ID，父ID)
        Integer note_id = noteCommentMapper.selectByKey(id).getNote_id();
        List<NoteComment> secCommentList = noteCommentMapper.selectSecById(note_id, id);
        model.addAttribute("sec", secCommentList);

        return "show_note_sec_comment";
    }

    //帖子评论——添加一级评论 ->>一级评论
    @RequestMapping("note_reply_comment/{id}")
    public String note_reply_comment(HttpServletRequest request, @PathVariable("id") Integer id){
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();

        noteCommentMapper.insert(content, user_id, null, null , id);

        return "redirect:/show_select_note/{id}";
    }

    //新闻评论——回复一级评论 ->>二级评论
    @RequestMapping("note_top_reply_comment/{id}")
    public String note_top_reply_comment(HttpServletRequest request, @PathVariable("id") Integer id){
        NoteComment noteComment = noteCommentMapper.selectByKey(id);
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();
        String reply_user_id = noteComment.getUser_id();
        Integer note_id = noteComment.getNote_id();

        noteCommentMapper.insert(content, user_id, reply_user_id, id , note_id);

        return "redirect:/note_show_sec_comment/{id}";
    }

    //帖子评论——回复二级评论 ->>二级评论
    @RequestMapping("sec_reply_note_comment/{id}")
    public String sec_reply_note_comment(HttpServletRequest request, @PathVariable("id") Integer id, Model model){
        NoteComment noteComment = noteCommentMapper.selectByKey(id);
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();
        String reply_user_id = noteComment.getUser_id();
        Integer pid = noteComment.getPid();
        Integer note_id = noteComment.getNote_id();

        noteCommentMapper.insert(content, user_id, reply_user_id, pid , note_id);

        List<NoteComment> secCommentList = noteCommentMapper.selectSecById(note_id, pid);
        model.addAttribute("sec", secCommentList);

        return "show_note_sec_comment";
    }

    //用户发帖
    @GetMapping("user_add_note.html")
    public String note_return(){
        return "user_add_note";
    }

    @PostMapping("user_add_note")
    public String add_note(HttpServletRequest request){
        HttpSession session = request.getSession();

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();
        String type = request.getParameter("type");

        noteMapper.insert(title, content, user_id, type);
        return "redirect:/show_all_note";
    }
}
