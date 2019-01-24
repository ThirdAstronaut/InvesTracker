package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {


    /**
     *
     * @return redirect to index page
     */
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        return "index";
    }
}