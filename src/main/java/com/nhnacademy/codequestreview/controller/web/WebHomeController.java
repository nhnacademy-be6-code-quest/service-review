package com.nhnacademy.codequestreview.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHomeController {

    @GetMapping("/index2")
    public String home() {
        return "index2";
    }

}
