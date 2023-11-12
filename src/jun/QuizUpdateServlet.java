package jun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "QuizUpdateServlet", urlPatterns = "/quizUpdate")
public class QuizUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<Quiz> quizList;

    @Override
    public void init() throws ServletException {
        super.init();

        // 매일 00:00에 퀴즈 업데이트 및 섞기
        Timer timer = new Timer();
        timer.schedule(new QuizUpdateTask(), getDelay(), 24 * 60 * 60 * 1000); // 매일 00:00에 실행

        // 초기에는 퀴즈 데이터를 로드
        quizList = fetchQuizDataFromJson();
    }

    private long getDelay() {
        // 현재 시간에서 내일 00:00 까지의 지연 시간을 계산
        long currentTime = System.currentTimeMillis();
        long midnight = (currentTime / (24 * 60 * 60 * 1000) + 1) * (24 * 60 * 60 * 1000); // 다음날 00:00
        return midnight - currentTime;
    }

    private class QuizUpdateTask extends TimerTask {
        @Override
        public void run() {
            // 퀴즈 업데이트 및 섞기 로직 호출
            updateAndShuffleQuizData();
        }
    }

    private void updateAndShuffleQuizData() {
        // 퀴즈 데이터 가져오기
        quizList = fetchQuizDataFromJson();

        // 퀴즈 데이터를 섞음
        shuffle(quizList);

        // 섞인 퀴즈 데이터를 저장하는 로직
        saveShuffledQuizData(quizList);
    }

    private List<Quiz> fetchQuizDataFromJson() {
        List<Quiz> quizList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("WebContent/Assets/JSON/quizData.json"))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // JSON 파일 내용을 읽어와 List<Quiz>로 변환
            quizList = parseJsonToQuizList(jsonContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quizList;
    }

    private List<Quiz> parseJsonToQuizList(String jsonString) {
        List<Quiz> parsedQuizList = new ArrayList<>();

        // JSON 파싱 로직 추가
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonQuiz = jsonArray.getJSONObject(i);

                String q = jsonQuiz.getString("q");
                List<String> options = jsonArrayToList(jsonQuiz.getJSONArray("options"));
                int correctIndex = jsonQuiz.getInt("correctIndex");
                String correctResponse = jsonQuiz.getString("correctResponse");
                String incorrectResponse = jsonQuiz.getString("incorrectResponse");

                Quiz quiz = new Quiz(q, options, correctIndex, correctResponse, incorrectResponse);
                parsedQuizList.add(quiz);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedQuizList;
    }

    private List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }


    private void shuffle(List<Quiz> array) {
        // 퀴즈 데이터를 섞는 로직
        Collections.shuffle(array);
    }

    private void saveShuffledQuizData(List<Quiz> shuffledQuizList) {
        // 섞인 퀴즈 데이터를 저장하는 로직
        // 예: 데이터베이스에 업데이트 또는 파일에 저장
        // 이 부분을 구현해 주세요.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자에게 퀴즈 제공
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 여기에서 quizList를 활용하여 사용자에게 퀴즈를 제공하는 코드를 작성하세요.
        // 아래는 가상의 코드이며 실제 로직에 맞게 수정이 필요합니다.
        resp.getWriter().write(generateQuizJsonResponse(quizList.get(0)));
    }

    private String generateQuizJsonResponse(Quiz quiz) {
        // 여기에서 Quiz 객체를 JSON 형식으로 변환하는 코드를 작성하세요.
        // 아래는 가상의 코드이며 실제 로직에 맞게 수정이 필요합니다.
        return "{\"q\":\"" + quiz.getQuestion() + "\", \"options\":" + quiz.getOptions() +
                ", \"correctIndex\":" + quiz.getCorrectIndex() + ", \"correctResponse\":\"" + quiz.getCorrectResponse() +
                "\", \"incorrectResponse\":\"" + quiz.getIncorrectResponse() + "\"}";
    }
}
