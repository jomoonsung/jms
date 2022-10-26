package com.jms.domain;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "Grade")
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gradeid;
	
	private Role role;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid", nullable=false, updatable=false)
	private User_1 user;
}
