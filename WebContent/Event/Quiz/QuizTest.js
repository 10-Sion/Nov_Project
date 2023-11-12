(function($) {
  $.fn.quiz = function(options) {
    var settings = $.extend({}, options);

    // 수정된 부분: correctCount 초기화
    settings.correctCount = options.correctCount || 0;

    return this.each(function() {
      var $quiz = $(this);
      var $questions = $quiz.find('#questions');
      var currentQuestion = 0;

      function showQuestion() {
        var question = settings.questions[currentQuestion];
        var $questionElement = $('<div class="question">' + question.q + '</div>');
        var $answersList = $('<ul class="answers"></ul>');

        question.options.forEach(function(option, index) {
          var $answer = $('<li><a href="#" data-index="' + index + '">' + option + '</a></li>');
          $answersList.append($answer);
        });

        $questions.empty().append($questionElement).append($answersList);
      }

      function showResults() {
        // 결과 표시 로직 구현
        var totalCorrect = settings.correctCount;
        var totalQuestions = settings.questions.length;

        var resultMessage = '오늘의 건강 퀴즈 결과  ' + totalQuestions+' 개의 문제 중 '+ totalCorrect + ' 개를 맞추셨네요~ ';
        alert(resultMessage);
      }

      // 수정된 handleAnswerClick 함수
      function handleAnswerClick(event) {
        event.preventDefault();
        var selectedAnswerIndex = $(this).data('index');

        // currentQuestion 값이 questions 배열의 인덱스 범위를 벗어나지 않도록 조정
        currentQuestion = Math.min(currentQuestion, settings.questions.length - 1);

        var correctIndex = settings.questions[currentQuestion].correctIndex;

        var message;
        if (selectedAnswerIndex == correctIndex) {
          // 정답인 경우의 처리
          message = settings.questions[currentQuestion].correctResponse;
          settings.correctCount++; // 맞춘 개수 증가
        } else {
          // 오답인 경우의 처리
          message = settings.questions[currentQuestion].incorrectResponse;
        }

        // 모달 창에 메시지 표시
        $('#quiz-modal-message').text(message);

        // 모달 창을 화면에 표시
        $('#quiz-modal').show();

        // 다음 질문 또는 결과 표시
        currentQuestion++;
        if (currentQuestion < settings.questions.length) {
          showQuestion();
        } else {
          showResults();
        }
      }

      function init() {
        $quiz.on('click', '.answers a', handleAnswerClick);
        $quiz.on('click', '#quiz-start-btn', function(event) {
          event.preventDefault();
          $('#quiz-start-screen').hide();
          showQuestion(); // 시작 버튼 클릭 시 첫 번째 문제 표시
        });
      }

      init();
    });
  };
})(jQuery);

// 모달 닫기 이벤트 처리
$('.close').on('click', function() {
  $('#quiz-modal').hide();
});

// 모달 외부 클릭 시 닫기
$(window).on('click', function(event) {
  if (event.target === $('#quiz-modal')[0]) {
    $('#quiz-modal').hide();
  }
});
