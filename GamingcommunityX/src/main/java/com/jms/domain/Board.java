package com.jms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long boardnum;
	
	private String title;
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date created_date = new Date();
	
	private Long  count = 0L;
	
	private String boardType;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid", nullable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User_1 user;
	
	@OrderBy("commentnum asc")
	@JsonIgnoreProperties({"board"})
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Comment> commentList = new ArrayList<Comment>();
	
	
	public void setUser(User_1 user) {
		this.user = user;
		user.getBoardList().add(this);
		
	}
}
