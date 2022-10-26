package com.jms.board.service;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.Board;
import com.jms.domain.Comment;
import com.jms.domain.User_1;
import com.jms.persistence.BoardRepository;
import com.jms.persistence.CommentRepository;
import com.jms.persistence.UserRepository;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	//댓글 등록
	@Override
	@Transactional
	public void commentSave(Long boardnum, Comment comment, String username) {
		Optional<User_1> user  = userRepo.findByUsername(username);
		Board board = boardRepo.findById(boardnum).orElseThrow(()->
		new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardnum));
		
		comment.setBoard(board);
		
		commentRepo.save(comment);
	}

	//댓글 삭제
	@Override
	public void delete(Long commentnum) {
		commentRepo.deleteByCommentnum(commentnum);
	}





}
