package app.dashboard;

/**
 * Created by Dennis on 16.10.2016.
 */
public class PlayedQuestion {
    int questionId;
    int playScore;

    public PlayedQuestion(int questionId, int playScore) {
        this.questionId = questionId;
        this.playScore = playScore;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getPlayScore() {
        return playScore;
    }
}
