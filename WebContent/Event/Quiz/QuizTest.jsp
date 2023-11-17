<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title> 알쏭달쏭 노잼퀴즈 </title>
  
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />	
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  
  <link rel="stylesheet" href="QuizTest.css">
</head>
<body>

	<div class="sideNav">
		<jsp:include page= "../../Main/mainNavigate.jsp"/>
	</div>

  <div id="quiz">
    <div id="quiz-header">
      <h1> 알쏭달쏭 건강 퀴즈 </h1>
      <p class="faded"> 나는 세상의 수많은 건강 상식들 앞에서 얼마나 무지할까? </p>
    </div>
    <div id="quiz-start-screen">
      <p><a href="#" id="quiz-start-btn" class="quiz-button">  시작 ! </a></p>
    </div>
    <div id="questions"></div>
  </div>
  
  <!-- 모달 창 -->
	<div id="quiz-modal" class="modal">
	  <div class="modal-content">
	    <span class="close">&times;</span>
	    <p id="quiz-modal-message"></p>
	  </div>
	</div>
  
  
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <script src="QuizTest.js"></script>
  <script src="app.js"></script>
</body>
</html>
