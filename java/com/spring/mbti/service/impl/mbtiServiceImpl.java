package com.spring.mbti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mbti.dao.MbtiDao;
import com.spring.mbti.service.mbtiService;
import com.spring.mbti.vo.MbtiVo;

@Service
public class mbtiServiceImpl implements mbtiService {
	
	@Autowired
	MbtiDao mbtiDao;

	@Override
	public List<MbtiVo> selectList() throws Exception {
		// TODO Auto-generated method stub
		return mbtiDao.selectList();
	}

}
