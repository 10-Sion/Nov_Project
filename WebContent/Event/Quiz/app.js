// app.js

var jsonFilePath = '../../Assets/JSON/quizData.json';

// 배열을 섞는 함수
function shuffle(array) {
  let currentIndex = array.length, randomIndex;

  // 배열을 모두 섞을 때까지 반복
  while (currentIndex !== 0) {
    // 남은 요소 중 하나를 선택
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;

    // 선택한 요소와 현재 요소를 교체
    [array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]];
  }

  return array;
}

$(document).ready(function() {
  fetch(jsonFilePath)
    .then(response => response.json())
    .then(data => {
      // 가져온 데이터 배열을 섞음
      data.questions = shuffle(data.questions);
      
      // 처음 10개의 질문만 사용
      const selectedQuestions = data.questions.slice(0, 10);

      $('#quiz').quiz({
        questions: selectedQuestions
      });
    });

  $('#quiz-start-btn').on('click', function(event) {
    event.preventDefault();
    $('#quiz-start-screen').hide();
	$('#quiz-header').hide();
    $('#questions').show();
  });
});