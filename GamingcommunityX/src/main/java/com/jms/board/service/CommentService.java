package com.jms.board.service;

import com.jms.domain.Comment;
import com.jms.domain.User_1;
import com.jms.security.SecurityUser;

public interface CommentService {


	void delete(Long commentnum);

	void commentSave(Long boardnum, Comment comment, String username);



	



}
