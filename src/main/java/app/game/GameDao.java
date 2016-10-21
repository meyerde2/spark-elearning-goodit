package app.game;

import java.util.List;

/**
 * Created by Dennis on 29.09.2016.
 */
public interface GameDao {

    boolean createNewQuestion(Question game);
    boolean updateQuestion(Question question);
    List<Question> getAllQuestions();
    Question getCurrentQuestion(String username);
    Question getNextQuestion(String username);
    Question getQuestionById(int id);
    boolean saveGame(Game game);

}
