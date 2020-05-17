package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.News;
import com.ciper.lakerhome.entity.NewsComment;
import com.ciper.lakerhome.mapper.NewsCommentMapper;
import com.ciper.lakerhome.mapper.NewsMapper;
import com.ciper.lakerhome.mapper.StarsMapper;
import com.ciper.lakerhome.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NewsController {
    final NewsService newsService;
    final NewsMapper newsMapper;
    final StarsMapper starsMapper;
    final NewsCommentMapper newsCommentMapper;

    @Autowired
    public NewsController(NewsService newsService, NewsMapper newsMapper, StarsMapper starsMapper, NewsCommentMapper newsCommentMapper){
        this.newsMapper = newsMapper;
        this.newsService = newsService;
        this.starsMapper = starsMapper;
        this.newsCommentMapper = newsCommentMapper;
    }


    @GetMapping("news.html")
    public String News(){
        return "news";
    }

    //查询所有新闻
    @GetMapping("show_all_news")
    public String show_all_news(Model model){
        List<News> news_list = newsMapper.selectAll();
        model.addAttribute(news_list);
        return "news";
    }

    //查询球星新闻
    @PostMapping("show_star_news")
    public String show_star_news(HttpServletRequest request, Model model){
        String stars_name = request.getParameter("stars_name");
        List<News> news_list = newsMapper.selectByStarsName(stars_name);
        model.addAttribute(news_list);
        return "news";
    }

    //用户登录后——球星新闻
    @GetMapping("show_user_news")
    public String show_user_news(Model model, HttpSession session){
        String email = session.getAttribute("user_email").toString();
        List<News> news_list = newsMapper.selectByUserId(email);
        model.addAttribute(news_list);
        return "user_news";
    }

    //管理员球星新闻管理
    @GetMapping("handle_news")
    public String handle_news(Model model){
        List<News> handle_news_list = newsMapper.selectAll();
        model.addAttribute(handle_news_list);
        return "handle_news";
    }

    //新闻管理——查询新闻
    @PostMapping("handle_star_news")
    public String handle_star_news(HttpServletRequest request, Model model){
        String stars_name = request.getParameter("stars_name");
        List<News> handle_news_list = newsMapper.selectByStarsName(stars_name);
        model.addAttribute(handle_news_list);
        return "handle_news";
    }

    //新闻管理——添加新闻
    @GetMapping("handle_add_news.html")
    public String handle_add_news_index(){
        return "handle_add_news";
    }

    @PostMapping("handle_add_news")
    public String handle_add_news(HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String  stars_name = request.getParameter("star_name");
        Integer stars_id = starsMapper.selectByStarsName(stars_name).getId();
        newsMapper.insert(title, content, stars_id);
        return "handle_news";
    }

    //新闻管理——删除新闻
    @GetMapping("handle_delete_news/{id}")
    public String handle_delete_news(@PathVariable("id") Integer id){
        newsMapper.deleteByPrimaryKey(id);
        return "redirect:/handle_news";
    }

    //新闻评论——查看一级评论
    @GetMapping("show_comment/{id}")
    public String show_news_comment(@PathVariable("id") Integer id, Model model){
        List<NewsComment> topCommentList = newsCommentMapper.selectTopByNewsId(id);
        model.addAttribute("top", topCommentList);

        return "show_comment";
    }

    //新闻评论——查看二级评论
    @GetMapping("show_sec_comment/{id}")
    public String show_sec_comment(@PathVariable("id") Integer id, Model model){
        //id 为一级评论的主键
        //如何获取一级评论的所有二级评论 ->>两个参数(新闻ID，父ID，id)
        Integer news_id = newsCommentMapper.selectByKey(id).getNews_id();
        List<NewsComment> secCommentList = newsCommentMapper.selectSecById(news_id, id);
        model.addAttribute("sec", secCommentList);

        return "show_sec_comment";
    }

    //新闻评论——添加一级评论 ->>一级评论
    @PostMapping("news_reply_comment/{id}")
    public String news_reply_comment(HttpServletRequest request, @PathVariable("id") Integer id){
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();

        newsCommentMapper.insert(content, user_id, null, null , id);

        return "redirect:/show_comment/{id}";
    }

    //新闻评论——回复一级评论 ->>二级评论
    @PostMapping("top_reply_comment/{id}")
    public String top_reply_news_comment(HttpServletRequest request, @PathVariable("id") Integer id){
        NewsComment newsComment = newsCommentMapper.selectByKey(id);
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();
        String reply_user_id = newsComment.getUser_id();
        Integer news_id = newsComment.getNews_id();

        newsCommentMapper.insert(content, user_id, reply_user_id, id , news_id);

        return "redirect:/show_sec_comment/{id}";
    }

    //新闻评论——回复二级评论 ->>二级评论
    @PostMapping("sec_reply_comment/{id}")
    public String sec_reply_news_comment(HttpServletRequest request, @PathVariable("id") Integer id){
        NewsComment newsComment = newsCommentMapper.selectByKey(id);
        HttpSession session = request.getSession();

        String content = request.getParameter("content");
        String user_id = session.getAttribute("user_email").toString();
        String reply_user_id = newsComment.getUser_id();
        Integer pid = newsComment.getPid();
        Integer news_id = newsComment.getNews_id();

        newsCommentMapper.insert(content, user_id, reply_user_id, pid , news_id);

        return "redirect:/show_sec_comment/{id}";
    }

}
