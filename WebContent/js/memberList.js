//수정버튼 클릭했을때
$(document).on('click','#modifyMember',function(){
	var grade_Name = $(this).closest("tr").find("#grade_Name").val();
	var user_id = $(this).closest("tr").children().first().text();
	console.log(grade_Name);
	console.log(user_id);
	
	$.ajax({
		url : "http://localhost:8090/Nov_Project/memberList/updateMemberList.do", //요청할 주소
		type : "post",  //전송요청방식 설정! get 또는 post 둘중 하나를 작성
		async : true,  //true는 비동기방식 , false는 동기방식 으로 서버페이지 요청!
		data : {grade_Name : grade_Name, user_id : user_id}, //서버 페이지로 요청할 변수명 : 값
		dataType : "text", //서버페이지로 부터 응답 받을 데이터 종류 설정!
						   //종류는 json 또는 xml 또는 text중 하나 설정!
		
		//전송요청과 응답통신에 성공했을때
		//success 속성에 적힌 function(data,textStatus){}이 자동으로 호출된다.
		// data매개변수로는 서버페이지가 전달한 응답 데이터가 넘어옵니다.
		success : function(data,textStatus){
			
			
		}
		
	});
});

//삭제버튼 클릭했을때
$(document).on('click','#deleteMember',function(){
	
	var user_id = $(this).closest("tr").children().first().text();
	
	console.log(user_id);
	
	if (confirm("회원을 삭제하시겠습니까?")) {
		
		$.ajax({
			url : "http://localhost:8090/Nov_Project/memberList/delMemberList.do", //요청할 주소
			type : "post",  //전송요청방식 설정! get 또는 post 둘중 하나를 작성
			async : true,  //true는 비동기방식 , false는 동기방식 으로 서버페이지 요청!
			data : {user_id : user_id}, //서버 페이지로 요청할 변수명 : 값
			dataType : "text", //서버페이지로 부터 응답 받을 데이터 종류 설정!
							   //종류는 json 또는 xml 또는 text중 하나 설정!
			
			//전송요청과 응답통신에 성공했을때
			//success 속성에 적힌 function(data,textStatus){}이 자동으로 호출된다.
			// data매개변수로는 서버페이지가 전달한 응답 데이터가 넘어옵니다.
			success : function(data,textStatus){
				
				alert("삭제했습니다.");
				location.reload();
				
			}
			
		});
	}
});

