package com.studyEnglish.controller;

import com.studyEnglish.entity.Notice;
import com.studyEnglish.entity.Sentence;
import com.studyEnglish.service.Impl.NoticeServiceImpl;
import com.studyEnglish.service.Impl.SentenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {

    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private SentenceServiceImpl sentenceService;
        //进入用户主页
        @RequestMapping("/main.html")
        public String toMainPage(Model model, HttpSession session){

            //查询最新公告
            Notice notice = noticeService.queryNewNoticeById();
            model.addAttribute("notice",notice);
            //随机查询每日一句
            Sentence sentence = sentenceService.queryRandomSentence();
            model.addAttribute("sentence",sentence);


            return "main";
        }
        //跳转管理员主页
        @RequestMapping("/admin/main")
        public String toAdminPage(Model model){


            return "admin/main";
        }


}
