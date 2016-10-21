package app.game;

import app.Application;
import app.user.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Dennis on 05.10.2016.
 */
public class GameDaoImpl implements GameDao{


    private Sql2o sql2o;
    private List<Question> game;
    private Question question;

    public GameDaoImpl(Sql2o sql2o){
        this.sql2o =sql2o;
    }

    @Override
    public boolean createNewQuestion(Question question) {


        String sql =
                "INSERT INTO questions(description, question, answer1, catId1, answer2, catId2, " +
                        "answer3, catId3, answer4, catId4, answer5, catId5, files, isActive) " +
                        "VALUES (:description, :question, :answer1, :catId1, :answer2, :catId2, " +
                        ":answer3, :catId3, :answer4, :catId4, :answer5, :catId5, :files, :isActive)";

        try (Connection con = sql2o.open()) {

            con.setRollbackOnException(false);

            con.createQuery(sql)
                    .addParameter("description", question.getDescription())
                    .addParameter("question", question.getQuestion())
                    .addParameter("answer1", question.getAnswer1())
                    .addParameter("catId1", question.getCatId1())
                    .addParameter("answer2", question.getAnswer2())
                    .addParameter("catId2", question.getCatId2())

                    .addParameter("answer3", question.getAnswer3())
                    .addParameter("catId3", question.getCatId3())
                    .addParameter("answer4", question.getAnswer4())
                    .addParameter("catId4", question.getCatId4())
                    .addParameter("answer5", question.getAnswer5())
                    .addParameter("catId5", question.getCatId5())

                    .addParameter("files", question.getFiles())
                    .addParameter("isActive", question.isActive())

                    .executeUpdate();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateQuestion(Question question) {

        //ToDo: put
        return false;
    }

    @Override
    public List<Question> getAllQuestions() {

        try (Connection conn = sql2o.open()) {
            game = conn.createQuery("SELECT * FROM questions")
                    .executeAndFetch(Question.class);
            return game;
        }

    }

    @Override
    public Question getCurrentQuestion(String username) {

        List<Game> gameList;
        List<Question> questionList;

        User user = Application.userDao.getUserByUsername(username);

        int userId = user.getId();

        System.out.println("userId:  " + userId);

        String sqlGame = "SELECT * FROM games where userId =" + userId +";";

        try (Connection conn = sql2o.open()) {

            gameList = conn.createQuery(sqlGame).executeAndFetch(Game.class);

            questionList = getAllQuestions();

            int i;

            if (gameList.size() == 0 ){
                i = 0;
            }else {
                i = gameList.size()-1;
            }

            if (gameList.size() >= questionList.size()){
                //Planspiel nun durchgespielt.
                return null;
            }else{
                return questionList.get(i);
            }

        }

    }

    @Override
    public Question getNextQuestion(String username) {

        List<Game> gameList;
        List<Question> questionList;

        User user = Application.userDao.getUserByUsername(username);

        int userId = user.getId();

        System.out.println("userId:  " + userId);

        String sqlGame = "SELECT * FROM games where userId =" + userId +" AND openGameId = " + user.getOpenGameId() + " ;";

        try (Connection conn = sql2o.open()) {

            gameList = conn.createQuery(sqlGame).executeAndFetch(Game.class);

            questionList = getAllQuestions();

            int i;

            if (gameList.size() == 0 ){
                i = 0;
            }else {
                i = gameList.size();
            }

            if (gameList.size() >= questionList.size()){
                //Planspiel nun durchgespielt.
                System.out.println("Planspiel durchgespielt");
                return null;
            }else{
                System.out.println("nextQuestion-Frage:  " + questionList.get(i).getQuestion());
                return questionList.get(i);
            }

        }

    }

    @Override
    public Question getQuestionById(int id) {

        try (Connection conn = sql2o.open()) {
            List<Question> qList = conn.createQuery("SELECT * FROM questions WHERE id =" + id + ";")
                    .executeAndFetch(Question.class);
            return qList.get(0);
        }
    }

    @Override
    public boolean saveGame(Game game) {

        String sql =
                "INSERT INTO games(questionId, userId, result, openGameId) " +
                        "VALUES (:questionId, :userId, :result, :openGameId);";

        try (Connection con = sql2o.open()) {

            con.setRollbackOnException(false);

            con.createQuery(sql)
                    .addParameter("questionId", game.getQuestionId())
                    .addParameter("userId", game.getUserId())
                    .addParameter("result", game.getResult())
                    .addParameter("openGameId", game.getOpengameId())
                    .executeUpdate();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }

    }
}
