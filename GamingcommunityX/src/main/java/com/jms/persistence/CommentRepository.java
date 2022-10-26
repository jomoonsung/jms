package com.jms.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	@Transactional
	void deleteByCommentnum(Long commentnum);

}
