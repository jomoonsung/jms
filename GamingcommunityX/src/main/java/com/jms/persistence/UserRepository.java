package com.jms.persistence;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.User_1;

public interface UserRepository extends JpaRepository<User_1, Long> {

	User_1 getUserByEmail(@Param("email") String email);

	public Optional<User_1> findByUsername(String username);

	boolean existsByUsername(String username);

	User_1 findByemail(@Param("email") String email);

	@Transactional
	@Modifying
	@Query("update User_1 u set u.userpassword =?2 WHERE u.userid=?1")
	void saveuseridAnduserpassword(Long userid, String userpassword);
	
	@Transactional
	void deleteByusername(String username);

	boolean existsByEmail(String email);






}
