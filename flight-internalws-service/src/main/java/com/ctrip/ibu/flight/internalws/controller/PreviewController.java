package com.ctrip.ibu.flight.internalws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 语言接口
 * @author : kyxie
 * createTime : 2018/6/1 15:58
 */
@Controller
@RequestMapping("/preview")
public class PreviewController {

    @RequestMapping(value = {"/emailpreview"},method = RequestMethod.GET)
    public ModelAndView emailPreview(@RequestParam String emailid){

        ModelAndView mv = new ModelAndView("emailpreview");

        mv.addObject("emailId",emailid);

        return mv;

    }

}
