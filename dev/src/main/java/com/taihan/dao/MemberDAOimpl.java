package com.taihan.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.taihan.vo.MemberVO;

@Repository
public class MemberDAOimpl implements MemberDAO {
	
	@Inject 
	SqlSession sql;

	//회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert("memberMapper.register", vo);
	}
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return sql.selectOne("memberMapper.login", vo);
	}
	
	//서비스에서 보낸 파라미터를 memberUpdate(MemberVO vo)에 담는다.
	//멤버정보수정
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		sql.update("memberMapper.memberUpdate", vo);
	}
	
	//회원탈퇴(삭제)
	@Override
	public void memberDelete(MemberVO vo) throws Exception{
		sql.delete("memberMapper.memberDelete", vo);
	}
	//패스워드 쳌
	@Override
	public int pwChk(MemberVO vo) throws Exception{
		int result = sql.selectOne("memberMapper.pwChk", vo);
		return result;
	}
	
	//아이디 쳌
	@Override
	public int idChk(MemberVO vo) throws Exception{
		int result = sql.selectOne("memberMapper.idChk", vo);
		return result;
	}
}
