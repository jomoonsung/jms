package com.jms;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jms.domain.Board;
import com.jms.domain.User_1;
import com.jms.persistence.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class GamingcommunityXApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BoardRepository boardRepo;
	
	 @Test
	 public void testInsert() { 
		 User_1 user = new User_1();
		 for (int i=1; i<=20; i++) { 
			  Board board = new Board();
			  user.setUserid(14L);
			  user.setUsername("user");
			  board.setTitle("자게1"+i);
			  board.setContent("자유게시판"+i);
			  board.setCount(0L);
			  board.setCreated_date(new Date());
			  boardRepo.save(board); 
		  }
		 
	 }
}
