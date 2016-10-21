package app.game;

/**
 * Created by Dennis on 06.10.2016.
 */
public class Game {
    int id;
    int questionId;
    int userId;
    int result;
    int opengameId;

    public Game(int id, int questionId, int userId, int result, int opengameId) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.result = result;
        this.opengameId = opengameId;
    }

    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getResult() {
        return result;
    }

    public int getOpengameId() {
        return opengameId;
    }
}
