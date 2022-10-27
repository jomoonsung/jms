package com.jms.controller;




import javax.transaction.Transactional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jms.common.MessageDto;
import com.jms.domain.Mail;
import com.jms.domain.Role;
import com.jms.domain.User_1;
import com.jms.persistence.UserRepository;
import com.jms.security.SecurityUser;
import com.jms.user.service.UserService;
import com.jms.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	public BCryptPasswordEncoder encodePWD;
	
	//사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
	
	//회원가입 화면
	@GetMapping("/userjoin")
	public String userjoinView(Model model) {
		model.addAttribute("user", new User_1());
		return "user/userjoin";
	}
	
	//회원가입
	@PostMapping("/userjoin")
	public String userjoin(@Valid @ModelAttribute("user") User_1 user , BindingResult br,Model model) {
			
			//아이디 중복 처리
			if(br.hasErrors()) {
				userService.checkUsernameDuplication(user);
				return "/user/userjoin";
//				userService.checkUserEmailDuplication(user);
//				return "/user/userjoin";
				
			}else {
				
				user.setRole(Role.ROLE_ADMIN);
				String rawPassword = user.getUserpassword();
				String encPassword = encodePWD.encode(rawPassword);
				user.setUserpassword(encPassword);
				user.setTwopassword(encPassword);
				userService.userJoin(user);
				MessageDto message = new MessageDto("회원가입 완료되었습니다.", "/system/loginform", RequestMethod.GET, null);
				return showMessageAndRedirect(message, model);
			}
		
		}
	
	//회원정보
	@GetMapping("/userinfo")
	public void userInfo(@AuthenticationPrincipal SecurityUser princ ,Model model) {
		model.addAttribute("user", princ.getMember());
		System.out.println("userDetails---->" + princ.getMember());
	}
//	@GetMapping("/userinfo")
//	@ResponseBody
//	public User_1 userInfo() {
//		User_1 user = new User_1();
//		user.getUserid();
//		return user;
//	}
	
	//회원정보 수정
	@PostMapping("/updateuser")
	public String updateUser(@AuthenticationPrincipal SecurityUser princ, User_1 user,Model model) {
		
		userService.updateUser(princ,user);
		MessageDto message = new MessageDto("회원정보 수정완료.", "/system/loginform", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}
	
	//회언탈퇴
	@GetMapping("/deleteuser")
	public String deleteUser(@AuthenticationPrincipal SecurityUser princ,Model model) {
		userService.deleteUser(princ);
		SecurityContextHolder.clearContext();
		MessageDto message = new MessageDto("회원탈퇴 완료.", "/", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}
	//아아디,비밀번호 찾기화면
	@GetMapping("/idpasswordSearch")
	public void  idpasswordSearchView() {
		
	}
	//아이디찾기
	@PostMapping("/Findid")
	public String  finduserId(String email, Model model) {
		User_1 user = userService.getUserByEmail(email);
		
		MessageDto message = new MessageDto("해당 아이디는 "+user.getUsername()+"입니다.", "/system/loginform", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}
	
	//비밀번호 찾기
	@Transactional
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestParam("email") String email,Model model) {
		String tmpPassword = userService.getTempPassword();
		userService.updatePassword(tmpPassword, email);
		Mail mail = userService.createMailAndChangePassword(email);
		userService.mailSend(mail);
		
		MessageDto message = new MessageDto("해당 이메일로 전송완료.", "/system/loginform", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
		
	}
}