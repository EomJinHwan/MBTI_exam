package com.spring.mbti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mbti.service.mbtiService;
import com.spring.mbti.vo.MbtiVo;
import com.spring.mbti.vo.MbtiAnswer;

@Controller
public class MbtiController {

    @Autowired
	mbtiService mbtiService;

	// mbti 질문들 표시
    @RequestMapping(value = "/mbti/mbtiList.do", method = RequestMethod.GET)
	public String mbtiQuestionList(Locale locale, Model model) throws Exception {

		List<MbtiVo> questionList = new ArrayList<MbtiVo>();

		questionList = mbtiService.selectList();

		model.addAttribute("questionList", questionList);

		return "mbti/mbtiList";
	}

	// mbti 결과 표시
	@RequestMapping(value = "/mbti/mbtiList.do", method = RequestMethod.POST)
	public String mbtiResult(Locale locale, Model model, MbtiAnswer mbtiAnswer) throws Exception {


        return "mbti/mbtiResult";
	}


}