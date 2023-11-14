//댓글창 오픈
$(document).on('click','#modifyComment',function(){
	
	var comment_id = $(this).closest("tr").find("#comment_id").val();
	var user_name = $(this).closest("tr").find("#user_name").val();
	var comment_date = $(this).closest("tr").find("#comment_date").val();
	var comment_text = $(this).closest("tr").find("#comment_text").val();
//	alert(comment_id);
		window.open('modifyCommentsForm.me?comment_id='+comment_id + '&user_name='+ user_name + '&comment_date='+ comment_date + '&comment_text=' + comment_text ,'_blank','width=500,height=400')			
});	

//댓글창  창 안보여주기 and 수정창 수정못하게 하기
if ($("#post_user_id").val() != $("#user_id").val()) {
	$("#reflected").attr('hidden',true);
	$("#del").attr('hidden',true);
	$("#post_title").attr('readonly',true);
	$("#post_content").attr('readonly',true);
}

if ($("#user_id").val() == null || $("#user_id").val() == "") {
	$("#writeBtn").attr('hidden',true);
	
}


   
    // "답글" 링크를 클릭했을 때
$(document).on('click','#replyComment', function () {
      // 폼의 'hidden' 속성을 토글
      $(this).closest("div").find("#replyCommentForm").attr("hidden", function (_, attr) {
        return !attr; // 'hidden' 속성을 토글
      
    });
  });

