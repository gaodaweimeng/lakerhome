package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.News;
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
import java.util.List;

@Controller
public class NewsController {
    final NewsService newsService;
    final NewsMapper newsMapper;
    final StarsMapper starsMapper;

    @Autowired
    public NewsController(NewsService newsService, NewsMapper newsMapper, StarsMapper starsMapper){
        this.newsMapper = newsMapper;
        this.newsService = newsService;
        this.starsMapper = starsMapper;
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
        return "handle_news";
    }

}
