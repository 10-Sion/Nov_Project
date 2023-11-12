<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quiz App</title>
  <link rel="stylesheet" href="QuizTest.css">
</head>
<body>
  <div id="quiz">
    <div id="quiz-header">
      <h1>Quiz</h1>
      <p class="faded">Bacon ipsum dolor amet beef sausage ham hock meatloaf burgdoggen picanha drumstick meatball ribeye corned beef fatback.</p>
    </div>
    <div id="quiz-start-screen">
      <p><a href="#" id="quiz-start-btn" class="quiz-button">Start</a></p>
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
