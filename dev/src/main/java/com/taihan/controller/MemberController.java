package com.taihan.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taihan.service.MemberService;
import com.taihan.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	// 회원가입 Get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	// 회원가입 Post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		logger.info("post register");
		int result = service.idChk(vo);
		try {
			if(result == 1) {
				return "member/register";
			} else if(result == 0) {
				String inputPw = vo.getUserPw();
				String pwd = pwdEncoder.encode(inputPw);
				vo.setUserPw(pwd);
				
				service.register(vo);
			}
			// 이 단에서 입력한 아이디가 존재한다면 회원가입 페이지로 회귀
			// 존재하지 않는다면 register
		} catch(Exception e) {
			throw new RuntimeException();
		}
		
		return "redirect:/";
	}
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		logger.info("post login");
		
		session.getAttribute("member");
		MemberVO login = service.login(vo);
		boolean pwdMatch;
		
		if(login != null) {
			if(login.getUserPw().length() < 20) {
				pwdMatch = vo.getUserPw().equals(login.getUserPw());
			} else {
			pwdMatch = pwdEncoder.matches(vo.getUserPw(), login.getUserPw());
			}
		} else {
			pwdMatch = false;
		}
		
		if(login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}
		
		return "redirect:/";
	}
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	//정보수정 GET
	@RequestMapping(value = "/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "member/memberUpdateView";
	}
	//정보수정 POST
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		service.memberUpdate(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원탈퇴(삭제) GET => POST페이지로 넘겨주는 부분
	@RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}
	
	//회원탈퇴(삭제) POST => 실질적 삭제를 담당하는부분
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		/* 비밀번호 검증 이전버젼
		 * MemberVO member = (MemberVO)session.getAttribute("member");
		 * 
		 * String sessionPw = member.getUserPw();
		 * 
		 * String voPw = vo.getUserPw();
		 * 
		 * if(!(sessionPw.contentEquals(voPw))) { rttr.addFlashAttribute("msg", false);
		 * return "redirect:/member/memberDeleteView"; }
		 */
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
	
	//패스워드쳌
	@ResponseBody
	@RequestMapping(value="/pwChk", method = RequestMethod.POST)
	public boolean pwChk(MemberVO vo) throws Exception{
		MemberVO login = service.login(vo);
		
		boolean pwdChk; 
		if(login.getUserPw().length() < 20) {
			pwdChk = vo.getUserPw().equals(login.getUserPw());
		} else {
			pwdChk = pwdEncoder.matches(vo.getUserPw(), login.getUserPw());
		}
		return pwdChk;
	}
	
	//아이디 쳌
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception{
		int result = service.idChk(vo);
		return result;
	}
}	
