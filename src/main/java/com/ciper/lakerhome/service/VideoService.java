package com.ciper.lakerhome.service;

import com.ciper.lakerhome.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    final VideoMapper videoMapper;

    @Autowired
    public VideoService(VideoMapper videoMapper){
        this.videoMapper = videoMapper;
    }
}
