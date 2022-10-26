/**
 * 
 */

//id중복체크 , 비밀번호확인
var idCheck = false;
var pwCheck = false;
/*로그인 체크 */
function login_check() {
	var user_id = document.getElementById("username").value;
	var user_password = document.getElementById("userpassword").value;

	if (user_id == "") {
		alert("아이디를 입력해주세요.");
		document.getElementById("username").focus();
		return false;
	} else if (user_password == "") {
		alert("비밀번호를 입력해주세요.")
		document.getElementById("userpasswrod").focus();
		return false;
	} else {
		return true;
	}

}

/* 회원가입 유효성 체크 */

function joinFormSubmit() {
	var user_id = document.getElementById("username").value;
	var userpwd = document.getElementById("userpassword").value;
	var pwd_check = document.getElementById("passwordConfirmd").value;
	var email = document.getElementById("email").value;

	if (user_id == "") {
		alert("아이디를 입력해주세요.")
		document.getElementById("username").focus();
		return false;
	} else if (userpwd == "") {
		alert("비밀번호를 입력해주세요")
		document.getElementById("userpassword").focus();
		return false;
	} else if (pwd_check == "") {
		alert("비밀번호를 다시한번 입력해주세요")
		document.getElementById("passwordConfirmd").focus();
		return false;
	} else if (userpwd != pwd_check) {
		alert("비밀번호가 맞지 않습니다")
	} else if (email == "") {
		alert("이메일을 입력해주세요.")
		document.getElementById("email").focus();
		return false;
	} else {
		return true;
	}
}
	
	
	/**
 * 
 */

 

 
		
