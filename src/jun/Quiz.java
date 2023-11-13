package jun;

import java.util.List;

public class Quiz {
    private String q; // 퀴즈 질문
    private List<String> options; // 퀴즈 옵션들
    private int correctIndex; // 정답 옵션의 인덱스
    private String correctResponse; // 정답일 때의 메시지
    private String incorrectResponse; // 오답일 때의 메시지


    public String getQuestion() {
        return q;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getCorrectResponse() {
        return correctResponse;
    }

    public String getIncorrectResponse() {
        return incorrectResponse;
    }
    
    public Quiz(String q, List<String> options, int correctIndex, String correctResponse, String incorrectResponse) {
        this.q = q;
        this.options = options;
        this.correctIndex = correctIndex;
        this.correctResponse = correctResponse;
        this.incorrectResponse = incorrectResponse;
    }
}

