package app.game;

import app.dashboard.GameResult;

import java.util.List;

public interface GameDao {

    boolean createNewQuestion(Question game);
    boolean updateQuestion(Question question);
    List<Question> getAllQuestions();
    List<Question> getAllActiveQuestions();

    Question getCurrentQuestion(String username);
    Question getNextQuestion(String username);
    List<Game> getAllQuestionsOfCurrentGame(int userId, int openGameId);
    Question getQuestionById(int id);
    boolean saveGame(Game game);
    boolean saveGameResult(GameResult gameResult);


}
