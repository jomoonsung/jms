package com.jms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENT_1")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentnum;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false )
	
	private Date created_date  = new Date();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="boardnum")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User_1 user;
	
	
	public void save(Board board, User_1 user) {
		this.board = board;
		this.user = user;
	}
	
	
}
