package com.jms.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardListBy(Pageable pageable);
	
	@Modifying
	@Query("update Board p set p.count = p.count +1 where p.boardnum = :boardnum")
	Long getBycount(Long boardnum);
	
	@Query(value="select * from board where board_type=?1", nativeQuery=true)
	@Transactional(readOnly = true)
	public Page<Board> findAllByBoardType(Pageable pageable,String boardType);
	
	@Query(value="select * from board where (title like %?1% or content like %?2%) and board_type=?3", nativeQuery=true)
	Page<Board> findByTitleContainingOrContentContainingAndBoardType(String keyword, String content, Pageable pageable,
			String boardType);
}
