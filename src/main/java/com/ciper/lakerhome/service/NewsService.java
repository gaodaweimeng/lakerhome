package com.ciper.lakerhome.service;

import com.ciper.lakerhome.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    public NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsMapper newsMapper){
        this.newsMapper = newsMapper;
    }


}
