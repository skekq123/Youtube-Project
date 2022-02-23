package com.example.olimtube.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class S3toColudFront {

    @Value("${cloud.aws.cloudFront.domain}")
    public String domain;

    public String changeUrl(String url){

        int index = url.indexOf(".com/") + 4;
        String res = "http://" + domain + url.substring(index, url.length());
        return res;
    }
}
