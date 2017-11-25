package com.breast.oil.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/show")
    public String show(ModelMap map){
        return "show";
    }
}
