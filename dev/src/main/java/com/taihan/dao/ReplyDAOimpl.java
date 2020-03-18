package com.taihan.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.taihan.vo.ReplyVO;

@Repository
public class ReplyDAOimpl implements ReplyDAO {
	
	@Inject 
	SqlSession sql;
	
	//댓글 조회
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception{
		return sql.selectList("replyMapper.readReply", bno);
	}
	//댓글 작성
	@Override
	public void writeReply(ReplyVO vo) throws Exception{
		sql.insert("replyMapper.writeReply", vo);
	}
	
	@Override
	public void updateReply(ReplyVO vo) throws Exception{
		sql.update("replyMapper.updateReply", vo);
	}
	@Override
	public void deleteReply(ReplyVO vo) throws Exception{
		sql.delete("replyMapper.deleteReply", vo);
	}
	@Override
	public ReplyVO selectReply(int rno) throws Exception{
		return sql.selectOne("replyMapper.selectReply", rno);
	}
	
}
