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

		List<MbtiVo> questionList = mbtiService.selectList();
		int eScore = 0, iScore = 0;
		int nScore = 0, sScore = 0;
		int fScore = 0, tScore = 0;
		int jScore = 0, pScore = 0;

		for (int i = 0; i < questionList.size(); i++) {
			MbtiVo question = questionList.get(i);
			int answer = mbtiAnswer.getAnswers().get(i);
			int score = Math.abs(answer-4);
			String type = question.getMbtiType();
			char mbti = answer < 4 ? type.charAt(0) : type.charAt(1);

			switch (mbti) {
			case 'E':
				eScore += score;
				break;
			case 'I':
				iScore += score;
				break;
			case 'N':
				nScore += score;
				break;
			case 'S':
				sScore += score;
				break;
			case 'F':
				fScore += score;
				break;
			case 'T':
				tScore += score;
				break;
			case 'P':
				pScore += score;
				break;
			case 'J':
				jScore += score;
				break;
			}
		}

		String mbtiResult = "";

		mbtiResult += eScore >= iScore ? "E" : "I";
		mbtiResult += nScore >= sScore ? "N" : "S";
		mbtiResult += fScore >= tScore ? "F" : "T";
		mbtiResult += jScore >= pScore ? "J" : "P";

		model.addAttribute("mbtiResult", mbtiResult);

        return "mbti/mbtiResult";
	}
}