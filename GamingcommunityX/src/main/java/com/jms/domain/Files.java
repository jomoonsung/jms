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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILE_UPLOAD")
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileno;
	
	private String filename;
	
	private String filepath;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date regdate = new Date();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="boardnum")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	public void save(Board board) {
		this.board = board;
	}
	

}
