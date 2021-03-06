package com.taihan.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.taihan.dao.MemberDAO;
import com.taihan.vo.MemberVO;

@Service
public class MemberServiceimpl implements MemberService {
	
	@Inject
	MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception{
		dao.register(vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return dao.login(vo);
	}
	
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		dao.memberUpdate(vo);
	}
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception{
		dao.memberDelete(vo);
	}
	
	@Override
	public int pwChk(MemberVO vo) throws Exception{
		int result = dao.pwChk(vo);
		return result;
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception{
		int result = dao.idChk(vo);
		return result;
	}
}
