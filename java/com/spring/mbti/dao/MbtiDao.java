package com.spring.mbti.dao;

import java.util.List;

import com.spring.mbti.vo.MbtiVo;

public interface MbtiDao {

	public List<MbtiVo> selectList() throws Exception;

}