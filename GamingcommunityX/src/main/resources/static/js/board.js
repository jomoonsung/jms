/**
 * 
 */
 
 function saveBoard() {
	 var title = document.getElementById("title").vlaue;
	 var content = document.getElementById("content").value;

	if(title == ""){
		alert("제목을 입력해주세요.")
		document.getElementById("title").focus();
		return false;
	}else if(content == ""){
		alert("내용을 입력해주세요.")
		document.getElementById("content").focus();
		return false;
	}else{
		return true;
	}	
}


