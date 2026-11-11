package com.spring.mbti.controller;

import java.util.ArrayList;
import java.util.Map;
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

	// mbti 결과 계산 및 표시
	@RequestMapping(value = "/mbti/mbtiList.do", method = RequestMethod.POST)
	public String mbtiResult(Locale locale, Model model, MbtiAnswer mbtiAnswer) throws Exception {

		List<MbtiVo> questionList = mbtiService.selectList();
		// 각 타입별로 점수 계산
		Map<Character,Integer> scoreMap = new HashMap<>();

		for (int i = 0; i < questionList.size(); i++) {
			MbtiVo question = questionList.get(i);
			int answer = mbtiAnswer.getAnswers().get(i);

			char mbti = 0;
			int point = 0;
			// 타입을 하나씩 분리
			char firstType = question.getMbtiType().charAt(0);
			char secondType = question.getMbtiType().charAt(1);

			switch (answer) {
			case 1:
				mbti = firstType;
				point = 3;
				break;
			case 2:
				mbti = firstType;
				point = 2;
				break;
			case 3:
				mbti = firstType;
				point = 1;
				break;
			case 4:
			    break;
			case 5:
				mbti = secondType;
				point = 1;
				break;
			case 6:
				mbti = secondType;
				point = 2;
				break;
			case 7:
				mbti = secondType;
				point = 3;
				break;
			}

			if (mbti != 0) {
				int score = 0;
				// map 안에 해당 타입의 키가있으면
				if(scoreMap.containsKey(mbti)) {
					// value를 가져오고
					score = scoreMap.get(mbti);
					// 기존 value와 추가된 점수를 합하여 다시 저장
					scoreMap.put(mbti, score + point);
				} else {
					// map 안에 해당 타입의 키가 없으면 그냥 저장
					scoreMap.put(mbti, point);
				}
			}
		}

		String[] types = {"EI", "NS", "FT", "JP"};
		String mbtiResult = "";

		// 유형 쌍 배열을 돌면서 각 쌍의 점수 비교
		for (String type : types) {
			char first = type.charAt(0);
			char second = type.charAt(1);
			// 맵에 점수가 없을경우 초기값 지정
			int firstScore = scoreMap.getOrDefault(first, 0);
			int secondScore = scoreMap.getOrDefault(second, 0);
			
			if (firstScore > secondScore) {
				mbtiResult += first;
			} else if (firstScore < secondScore) {
				mbtiResult += second;
			} else { // 동점일경우 사전순으로 앞에있는게 나옴
				mbtiResult += first < second? first : second;
			}
		}
		
		model.addAttribute("mbtiResult", mbtiResult);

        return "mbti/mbtiResult";
	}
}