package com.example.olimtube.component;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class CategoryInfo {
    Map<Integer, String> map = new HashMap<Integer, String>();

    public CategoryInfo(){
        map.put(1, "https://d1jfiqncg5yujk.cloudfront.net/category/1.png");
        map.put(2, "https://d1jfiqncg5yujk.cloudfront.net/category/2.png");
        map.put(3, "https://d1jfiqncg5yujk.cloudfront.net/category/3.png");
        map.put(4, "https://d1jfiqncg5yujk.cloudfront.net/category/4.png");
        map.put(5, "https://d1jfiqncg5yujk.cloudfront.netcategory/5.png");
        map.put(6, "https://d1jfiqncg5yujk.cloudfront.net/category/6.png");
        map.put(7, "https://d1jfiqncg5yujk.cloudfront.net/category/7.png");
        map.put(8, "https://d1jfiqncg5yujk.cloudfront.net/category/8.png");
        map.put(9, "https://d1jfiqncg5yujk.cloudfront.net/category/9.png");
        map.put(10, "https://d1jfiqncg5yujk.cloudfront.net/category/10.png");
    }
}
