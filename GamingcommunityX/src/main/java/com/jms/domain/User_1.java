package com.jms.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User_1 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;

	@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String username;
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
//    message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
	@Column(name="userpassword")
	private String userpassword;
	private String twopassword;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	private boolean enabled;
	private int point_amount;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Comment> commentList = new ArrayList<Comment>();
	
	public void updatePassword(String userpassword) {
		this.userpassword = userpassword;
	}
}
