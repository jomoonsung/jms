/**
 * 
 */




$(document).ready(function() {
	$("#btn-comment-save").click(function() {

		let data = {
			content: $("#content").val(),
			boardnum: $("#boardnum").val()
		};

		console.log(data);
		console.log(boardnum);
		$.ajax({
			type: 'POST',
			url: '/board/' + data.boardnum + '/comment',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
		}).done(function() {
			alert("댓글작성이 완료되었습니다.");
			window.location.reload();
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});
});


$(document).ready(function() {
	$("#deleteComment").click(function() {
		
		let data = {
			commentnum: $("#commentnum").val(),
		};
		console.log(data);
		$.ajax({
			type: 'POST',
			url: '/comment/' + data.commentnum + '/comments',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
		}).done(function() {
			alert("댓글을 삭제완료");
			window.location.reload();
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});
});


