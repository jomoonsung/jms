package com.jms.board.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jms.domain.Board;
import com.jms.domain.Files;
import com.jms.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	
	//게시판 리스트
	@Override
	public Page<Board> getBoardListBy(Pageable pageable,Board board) {
		String boardType = board.getBoardType();
		return boardRepo.findAllByBoardType(pageable,boardType);
	}
	
	//조회수 카운트
	@Override
	@Transactional
	public Board getBoard(Board board) {
		try {
			board = boardRepo.findById(board.getBoardnum()).orElseThrow();
			board.setCount(board.getCount()+1);
			return board;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//게시글 등록
	@Override
	public void insertBoard(Board board,MultipartFile file ,Files files) throws Exception {
		
		//파일이 없을떄
		if(file.isEmpty()) {
			
		}else {
		String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\images";
		//랜덤 
		UUID uuid  = UUID.randomUUID();
			
		String fileName = uuid + "_" + file.getOriginalFilename();
			
		File saveFile = new File(projectPath, fileName);
			
			
		files.setFilename(fileName);
		files.setFilepath("/files/"+fileName);
			
		file.transferTo(saveFile);
		}
		
		boardRepo.save(board);
	}
	
	//게시글 수정
	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getBoardnum()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		
		boardRepo.save(findBoard);
	}
	
	//게시글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getBoardnum());
	}
	
	//게시글 검색
	@Override
	public Page<Board> search(String keyword,String content,Pageable pageable,String boardType) {
		return  boardRepo.findByTitleContainingOrContentContainingAndBoardType(keyword,content,pageable,boardType);
	}
}
