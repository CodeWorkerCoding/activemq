package com.shenmajr.boot.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shenma on 2016/7/11.
 */
@Controller
public class HelleAction {
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public void  test(){
        Math.abs(23);
        System.out.println(11);
    }
}
