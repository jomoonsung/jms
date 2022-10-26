package com.jms.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.Board;
import com.jms.domain.Mail;
import com.jms.domain.User_1;
import com.jms.persistence.UserRepository;
import com.jms.security.SecurityUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	public BCryptPasswordEncoder encodePWD;

	// 회원가입
	@Override
	public void userJoin(User_1 user) {
		userRepo.save(user);
	}

	// 아이디 중복 여부 확인
	@Transactional(readOnly = true)
	public void checkUsernameDuplication(User_1 user) {
		boolean usernameDuplication = userRepo.existsByUsername(user.getUsername());
		if (usernameDuplication) {
			throw new IllegalStateException("이미 존재하는 아이디입니다.");
		}
	}
	
	@Transactional(readOnly = true)
	public void checkUserEmailDuplication(User_1 user) {
		boolean userEmailDuplication = userRepo.existsByEmail(user.getEmail());
		if(userEmailDuplication) {
			throw new IllegalStateException("이미 존재하는 이메일입니다.");
		}
	}

	// 회원정보 -> 비밀번호 변경
	@Override
	public void updatePassWord(User_1 user) {
		userRepo.save(user);
	}

	// 회원탈퇴
	@Override
	public void deleteUser(SecurityUser princ) {
		userRepo.deleteByusername(princ.getUsername());
	}

	@Override
	public void updateUser(SecurityUser princ, User_1 user) {
		User_1 findUser = userRepo.findByUsername(princ.getUsername()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기실패");
		});
		String userPassword = user.getUserpassword();
		String encPassword = encodePWD.encode(userPassword);
		findUser.setUserpassword(encPassword);
		findUser.setEmail(user.getEmail());

		userRepo.save(findUser);
	}
	
	//아이디 찾기
	@Override
	public User_1 getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

	// 임시빌밀번호 생성
	@Override
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String str = "";

		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}

	@Override
	public Mail createMailAndChangePassword(String email) {
		String str = getTempPassword();
		Mail mail = new Mail();
		mail.setAddress(email);
		mail.setTitle("jms게임커뮤니티 임시비밀번호 안내 이메일 입니다.");
		mail.setMessage("안녕하세요. jms게임커뮤니티 임시비밀번호 안내 관려 이메일입니다." + "회원님의 임시" + " " + str + " " + "입니다."
				+ "로그인 후에 비밀번호를 변경을 해주세요.");
		updatePassword(str, email);
		return mail;
	}

	// 임시 비밀번호 업데이트
	@Override
	@Transactional
	public void updatePassword(String tmpPassword, String email) {
		String encryptPassword = encodePWD.encode(tmpPassword);
		Long userid = userRepo.findByemail(email).getUserid();
		userRepo.saveuseridAnduserpassword(userid, encryptPassword);
	}

	@Override
	public void mailSend(Mail mail) {
		System.out.println("전송 완료!");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		message.setFrom("jomoonsung3321@gmail.com");
		message.setReplyTo("to");
		System.out.println("message" + message);
		mailSender.send(message);
	}
	@Override
	public User_1 getUser(User_1 user) {
		try {
			user = userRepo.findById(user.getUserid()).orElseThrow();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
