package com.spring.mbti.service;

import java.util.List;

import com.spring.mbti.vo.MbtiVo;

public interface mbtiService {

	public List<MbtiVo> selectList() throws Exception;
}
