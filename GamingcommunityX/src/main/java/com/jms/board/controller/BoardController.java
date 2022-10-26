
package com.jms.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jms.board.service.BoardService;
import com.jms.common.MessageDto;
import com.jms.domain.Board;
import com.jms.domain.Files;
import com.jms.security.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
	
	//팝니다 게시판
	@RequestMapping("/buyboardList")
	public String getBoardListBy(Model model, Board board,
			@PageableDefault(size = 10, sort = "boardnum", direction = Sort.Direction.DESC) Pageable pageable) {
		board.setBoardType("팝니다");
		Page<Board> pageList = boardService.getBoardListBy(pageable, board);
		List<Board> boardList = pageList.getContent();

		int startPage = Math.max(1, pageList.getPageable().getPageNumber() - 9);
		int endPage = Math.min(pageList.getPageable().getPageNumber() + 9, pageList.getTotalPages());

		model.addAttribute("board", board);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageList", pageList);

		return "board/buyboardList";
	}
	
	//삽니다 게시판
	@GetMapping("/sellboardList")
	public String sellBoardListView(Model model,
			@PageableDefault(size = 10, sort = "created_date", direction = Sort.Direction.DESC) Pageable pageable,
			Board board) {
		board.setBoardType("삽니다");
		Page<Board> pageList = boardService.getBoardListBy(pageable, board);
		List<Board> boardList = pageList.getContent();

		int startPage = Math.max(1, pageList.getPageable().getPageNumber() - 9);
		int endPage = Math.min(pageList.getPageable().getPageNumber() + 9, pageList.getTotalPages());

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);
		model.addAttribute("board", board);
		model.addAttribute("boardList", boardList);
		return "board/sellboardList";
	}
	
	//공지사항
	@GetMapping("/noticeboardList")
	public String notiesBoardListView(Model model,
			@PageableDefault(size = 10, sort = "created_date", direction = Sort.Direction.DESC) Pageable pageable,
			Board board) {
		board.setBoardType("공지사항");
		Page<Board> pageList = boardService.getBoardListBy(pageable, board);
		List<Board> boardList = pageList.getContent();

		int startPage = Math.max(1, pageList.getPageable().getPageNumber() - 9);
		int endPage = Math.min(pageList.getPageable().getPageNumber() + 9, pageList.getTotalPages());

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);

		model.addAttribute("board", board);
		model.addAttribute("boardList", boardList);
		return "board/noticeboardList";
	}
	
	//자유게시판
	@GetMapping("/newsboardList")
	public String newsBoardListView(Model model,
			@PageableDefault(size = 10, sort = "created_date", direction = Sort.Direction.DESC) Pageable pageable,
			Board board) {
		board.setBoardType("자유게시판");
		Page<Board> pageList = boardService.getBoardListBy(pageable, board);
		List<Board> boardList = pageList.getContent();

		int startPage = Math.max(1, pageList.getPageable().getPageNumber() - 9);
		int endPage = Math.min(pageList.getPageable().getPageNumber() + 9, pageList.getTotalPages());

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);
		model.addAttribute("board", board);
		model.addAttribute("boardList", boardList);
		return "board/newsboardList";
	}
	
	//게시글
	@GetMapping("/boardDetail")
	public String getBoard(Board board, Model model,@AuthenticationPrincipal SecurityUser princ) {
		model.addAttribute("board", boardService.getBoard(board));
		model.addAttribute("user", princ.getMember());
		return "board/boardDetail";
	}
	
	//검색
	@RequestMapping("/search")
	public String searchBoard(String keyword, String content,String boardType, Model model,
			@PageableDefault(size = 10) Pageable pageable, Board board) {

		Page<Board> pageList = boardService.search(keyword, content, pageable,boardType);
		List<Board> boardList = pageList.getContent();

		int startPage = Math.max(1, pageList.getPageable().getPageNumber() - 9);
		int endPage = Math.min(pageList.getPageable().getPageNumber() + 9, pageList.getTotalPages());

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageable", pageable);
		model.addAttribute("boardList", boardList);
		model.addAttribute("keyword", keyword);
		
		if(board.getBoardType().equals("팝니다")) {
			return "board/buyboardList";
		}else if(board.getBoardType().equals("삽니다")) {
			return "board/sellboardList";
		}else if(board.getBoardType().equals("자유게시판")) {
			return "board/newsboardList";
		}else {
			return "board/noticeboardList";
		}
	}
	//게시판 글쓰기 화면
	@GetMapping("/insertBoard")
	public String insertBoardView(Model model) {
		model.addAttribute("board", new Board());
		return "board/insertBoard";
	}
	
	//게시판 글쓰기
	@PostMapping("/insertboard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser princ,
			@RequestParam(value = "file", required = false) MultipartFile file,Model model,Files files) throws Exception {
		
		board.setUser(princ.getMember());
		boardService.insertBoard(board, file,files);
		if (board.getBoardType().equals("팝니다")) {
	        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/buyboardList", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		} else if (board.getBoardType().equals("삽니다")) {
	        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/sellboardList", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		} else if (board.getBoardType().equals("자유게시판")) {
	        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/newsboardList", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		} else {
	        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/noticeboardList", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		}
	}
	
	//게시판 수정화면 
	@GetMapping("/updateboard")
	public void updateBoardView(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
	}
	
	//게시판 수정
	@PostMapping("/update")
	public String updateBoard(Board board,Model model) {

		boardService.updateBoard(board);
		if (board.getBoardType().equals("팝니다")) {
			  MessageDto message = new MessageDto("게시글 수정되었습니다.", "/board/buyboardList", RequestMethod.GET, null);
			  return showMessageAndRedirect(message, model);
		} else if (board.getBoardType().equals("삽니다")) {
			  MessageDto message = new MessageDto("게시글 수정되었습니다", "/board/sellboardList", RequestMethod.GET, null);
			  return showMessageAndRedirect(message, model);
		} else if (board.getBoardType().equals("자유게시판")) {
			  MessageDto message = new MessageDto("게시글 수정되었습니다", "/board/newsboardList", RequestMethod.GET, null);
			  return showMessageAndRedirect(message, model);
		} else {
			  MessageDto message = new MessageDto("게시글 수정되었습니다", "/board/noticeboardList", RequestMethod.GET, null);
			  return showMessageAndRedirect(message, model);
		}

	}
	
	//게시판 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board,Model model) {
		
		boardService.deleteBoard(board);
			  MessageDto message = new MessageDto("게시글 삭제되었습니다.", "/board/buyboardList", RequestMethod.GET, null);
			  return showMessageAndRedirect(message, model);
	}
}
