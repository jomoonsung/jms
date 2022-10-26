/*!
* Start Bootstrap - Small Business v5.0.5 (https://startbootstrap.com/template/small-business)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-small-business/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project


 /*로그인 체크 */
 
 function login_check(){
	var user_id = document.getElementById("userid").value;
	var user_password = document.getElementById("userpassword").value;
	
	if(user_id == ""){
		alert("아이디를 입력해주세요.");
		document.getElementById("userid").focus();
		return false;
	}else if(user_password == ""){
		alert("비밀번호를 입력해주세요.")
		document.getElementById("userpasswrod").focus();
		return false;
	}else{
		return true;
	}
	
}

/* 회원가입 유효성 체크 */

function joinFrom_check(){
	var name = document.getElementById("username").value;
	var id = document.getElementById("userid").value;
	var nickname = document.getElementById("nickname").value;
	var userpwd = document.getElementById("userpassword").value;
	var pwd_check = document.getElementById("pwdConfirmd").value;
	var email = document.getElementById("email").value;
	
	if(name == ""){
		alert("이름을 입력해주세요.");
		document.getElementById("username").focus();
		return false;
	}else if(id == ""){
		alert("아이디를 입력해주세요.")
		document.getElementById("userid").focus();
		return false;		
	}else if(nickname == ""){
		alert("닉네임을 입력해주세요.")
		document.getElementById("nickname").focus();
		return false;		
	}else if(userpwd == ""){
		alert("비밀번호를 입력해주세요")
		document.getElementById("userpassword").focus();
		return false;		
	}else if(pwd_check == ""){
		alert("비밀번호를 다시한번 입력해주세요")
		document.getElementById("pwdConfirmd").focus();
		return false;		
	}else if(email == ""){
		alert("이메이을 입력해주세요.")
		document.getElementById("email").focus();
		return false;		
	}
}

function update(){
	alert("추후 공개...");
}
