package com.seleniumapp.weddriver.app.controller;

import com.seleniumapp.weddriver.app.takescreenshot.WebsiteCrawler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
public class UiappController {

    @GetMapping("/")
    public String helloworld(){
        return "Hello Hamzeh";
    }

    @GetMapping("/shot/")
    public int urls() throws MalformedURLException {
        //String url = "http://www.google.com";
        String url = "http://www.linkedin.com";
        WebsiteCrawler websiteCrawl = new WebsiteCrawler();
        //websiteCrawl.initialDelay();
        //websiteCrawl.setUp(url);
        //return websiteCrawl.showLogs();
        //return "Hello WebCrawler";
        return websiteCrawl.setUp(url);
    }


}
