package app.game;

import app.dashboard.GameResult;
import app.user.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static app.Application.userDao;

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

        String updateSql = "UPDATE questions SET " +
                " description = :description, question= :question, " +
                " answer1 = :answer1, catId1 = :catId1," +
                " answer2 = :answer2, catId2 = :catId2," +
                " answer3 = :answer3, catId3 = :catId3, " +
                " answer4 = :answer4, catId4 = :catId4, " +
                " answer5 = :answer5, catId5 = :catId5, " +
                " isActive = :isActive  WHERE id = :id";

        System.out.println("getAnswer1:  ________: " + question.getAnswer1());
        try (Connection con = sql2o.open()) {

            con.createQuery(updateSql)

                    .addParameter("id", question.getId())
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

                    .addParameter("isActive", question.isActive())

                    .executeUpdate();
            return true;

        }catch (Exception e){
            return false;
        }

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

        User user = userDao.getUserByUsername(username);

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

        User user = userDao.getUserByUsername(username);

        String sqlGame = "SELECT * FROM games WHERE userId =" + user.getId() +" AND openGameId = " + user.getOpenGameId() + " ;";

        try (Connection conn = sql2o.open()) {

            gameList = conn.createQuery(sqlGame).executeAndFetch(Game.class);
            questionList = getAllQuestions();

            if (gameList.size() == 0){
                //start the game with the first question
                return getQuestionById(1);
            }else if (gameList.size() >= questionList.size()){
                //end of game
                return null;
            }else{
                //get next question
                return questionList.get(gameList.size());
            }
        }
    }

    @Override
    public List<Game> getAllQuestionsOfCurrentGame(int userId, int openGameId) {
        List<Game> currentGameList;

        String sqlGame = "SELECT * FROM games WHERE userId =" + userId +" AND openGameId = " + openGameId + " ;";

        try (Connection conn = sql2o.open()) {

            currentGameList = conn.createQuery(sqlGame).executeAndFetch(Game.class);

        }
        return currentGameList;
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
                "INSERT INTO games(questionId, userId, endresult, openGameId) " +
                        "VALUES (:questionId, :userId, :endresult, :openGameId);";

        try (Connection con = sql2o.open()) {

            con.setRollbackOnException(false);

            con.createQuery(sql)
                    .addParameter("questionId", game.getQuestionId())
                    .addParameter("userId", game.getUserId())
                    .addParameter("endresult", game.getResult())
                    .addParameter("openGameId", game.getOpengameId())
                    .executeUpdate();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean saveGameResult(GameResult gameResult) {

        //ToDo: openGameId zu gameId abändern!

        String sql =
                "INSERT INTO gameresult(userId, endresult, gameId) " +
                        "VALUES (:userId, :endresult, :gameId);";

        try (Connection con = sql2o.open()) {

            con.setRollbackOnException(false);

            con.createQuery(sql)
                    .addParameter("userId", gameResult.getUserId())
                    .addParameter("endresult", gameResult.getEndresult())
                    .addParameter("gameId", gameResult.getGameId())
                    .executeUpdate();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
