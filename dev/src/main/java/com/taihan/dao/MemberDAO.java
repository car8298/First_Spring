package com.taihan.dao;

import com.taihan.vo.MemberVO;

public interface MemberDAO {
	
	//회원가입
	public void register(MemberVO vo) throws Exception;
	
	//로그인
	public MemberVO login(MemberVO vo) throws Exception;
	
	//회원정보 수정
	public void memberUpdate(MemberVO vo) throws Exception;
	
	//회원탈퇴(삭제)
	public void memberDelete(MemberVO vo) throws Exception;
	
	//패스워드 쳌
	public int pwChk(MemberVO vo) throws Exception;
	
	//아이디쳌
	public int idChk(MemberVO vo) throws Exception;
	
}
