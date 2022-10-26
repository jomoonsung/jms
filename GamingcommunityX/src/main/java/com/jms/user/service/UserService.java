package com.jms.user.service;





import com.jms.domain.Board;
import com.jms.domain.Mail;
import com.jms.domain.User_1;
import com.jms.security.SecurityUser;

public interface UserService {
	 void userJoin(User_1 user);
	
	void checkUsernameDuplication(User_1 user);
	void checkUserEmailDuplication(User_1 user);

	User_1 getUserByEmail(String email);

	Mail createMailAndChangePassword(String email);

	void mailSend(Mail mail);
	
	void updatePassword(String str, String email);
	
	void updatePassWord(User_1 user);

	String getTempPassword();

	void updateUser(SecurityUser princ,User_1 user);

	void deleteUser(SecurityUser princ);
	
	User_1 getUser(User_1 user);
}
