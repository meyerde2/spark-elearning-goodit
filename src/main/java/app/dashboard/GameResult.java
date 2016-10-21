package app.dashboard;

/**
 * Created by Dennis on 16.10.2016.
 */
public class GameResult {

    int id;

    int userId;
    int result;
    int opengameId;

    public GameResult(int id, int userId, int opengameId, int result) {
        this.id = id;
        this.userId = userId;
        this.opengameId = opengameId;
        this.result = result;
    }

    public int getId() {
        return id;
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
