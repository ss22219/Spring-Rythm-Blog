package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {
    @RequestMapping("/{title}/{page}")
    public String search(@PathVariable String title,@PathVariable int page,ModelMap map){

        return "/category/cat";
    }


}
