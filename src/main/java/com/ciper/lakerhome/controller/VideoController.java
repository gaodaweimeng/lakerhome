package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.Video;
import com.ciper.lakerhome.mapper.StarsMapper;
import com.ciper.lakerhome.mapper.VideoMapper;
import com.ciper.lakerhome.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VideoController {
    final VideoMapper videoMapper;
    final VideoService videoService;
    final StarsMapper starsMapper;

    @Autowired
    public VideoController(VideoMapper videoMapper, VideoService videoService, StarsMapper starsMapper){
        this.videoMapper = videoMapper;
        this.videoService = videoService;
        this.starsMapper = starsMapper;
    }

    //视频展示
    @GetMapping("show_all_video")
    public String show_all_video(Model model){
        List<Video> video_list = videoMapper.selectAll();
        model.addAttribute(video_list);
        return "video";
    }

    //视频展示——球星查询
    @PostMapping("show_star_video")
    public String show_star_video(HttpServletRequest request, Model model){
        String stars_name = request.getParameter("stars_name");
        List<Video> video_list = videoMapper.selectByStarsName(stars_name);
        model.addAttribute(video_list);
        return "video";
    }

    //管理员球星视频管理
    @GetMapping("handle_video")
    public String handle_video(Model model){
        List<Video> video_list = videoMapper.selectAll();
        model.addAttribute(video_list);
        return "handle_video";
    }

    //视频管理——球星查询展示
    @PostMapping("handle_star_video")
    public String handle_star_video(HttpServletRequest request, Model model){
        String stars_name = request.getParameter("stars_name");
        List<Video> video_list = videoMapper.selectByStarsName(stars_name);
        model.addAttribute(video_list);
        return "handle_video";
    }

    //视频管理——删除视频
    @GetMapping("handle_delete_video/{id}")
    public String handle_delete_video(@PathVariable("id") Integer id){
        videoMapper.deleteByPrimaryKey(id);
        return "handle_video";
    }

    //视频管理——添加视频
    @GetMapping("handle_add_video.html")
    public String handle_add_video_index(){
        return "handle_add_video";
    }

    @PostMapping("handle_add_video")
    public String handle_add_video(HttpServletRequest request){
        String title = request.getParameter("title");
        String place = request.getParameter("place");
        String  stars_name = request.getParameter("stars_name");
        Integer stars_id = starsMapper.selectByStarsName(stars_name).getId();
        videoMapper.insert(title, place, stars_id);
        return "handle_video";
    }

}
