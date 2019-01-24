package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class SearchController {
    /**
     *
     * @return redirect to search page
     */
    @RequestMapping("/search")
    public String searchRedir(){
        return "search";
    }
    /**
     *
     * @return redirect to error page
     */
    @RequestMapping("/404")
    public String errorRedir(){
        return "404error";
    }
}