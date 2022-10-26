package com.jms.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.jms.domain.Board;
import com.jms.domain.Files;
import com.jms.domain.Search;

public interface BoardService {
	
	Page<Board> getBoardListBy(Pageable pageable,Board board);

	Board getBoard(Board board);

	void deleteBoard(Board board);

	void updateBoard(Board board);

//	Page<Board> search(String keyword,String content,Pageable pageable);


	Page<Board> search(String keyword, String content, Pageable pageable, String boardType);

	void insertBoard(Board board, MultipartFile file, Files files) throws Exception;


}
