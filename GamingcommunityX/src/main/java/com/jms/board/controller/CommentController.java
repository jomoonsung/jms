package com.jms.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jms.board.service.CommentService;
import com.jms.domain.Comment;
import com.jms.domain.User_1;
import com.jms.security.SecurityUser;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	//댓글 등록
	@RequestMapping("/board/{boardnum}/comment")
	public String save(@PathVariable Long boardnum,
					 @RequestBody Comment comment,
					  User_1 user,Model model) {
		commentService.commentSave(boardnum,comment,user.getUsername());
		model.addAttribute("user", user);
		return "/board/boardDetail";
	}
	//댓글 삭제
	@RequestMapping("/comment/{commentnum}/comments")
	public String delete(@PathVariable Long commentnum,
			@AuthenticationPrincipal SecurityUser princ) {
		commentService.delete(commentnum);
		  return "/board/boardDetail";
	}
}
