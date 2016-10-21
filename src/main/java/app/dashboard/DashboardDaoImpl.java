package app.dashboard;

import app.Application;
import app.game.Game;
import app.user.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 05.10.2016.
 */
public class DashboardDaoImpl implements DashboardDao {

    private Sql2o sql2o;


    public DashboardDaoImpl(Sql2o sql2o){
        this.sql2o =sql2o;
    }


    @Override
    public Game getCurrentGameProgress(String username) {

        List<Game> gameList;

        User user = Application.userDao.getUserByUsername(username);

        String sqlGame = "SELECT * FROM games WHERE userId =" + user.getId() +"AND openGameId= " + user.getOpenGameId() + ";";


        try (Connection conn = sql2o.open()) {

            gameList = conn.createQuery(sqlGame).executeAndFetch(Game.class);

        }

        if (gameList.size() >=1){

            return gameList.get(gameList.size() -1) ;

        }else{

            return null;

        }
    }

    @Override
    public List<PlayedQuestion> getPlayedQuestionsCount() {

        List<PlayedQuestion> playedQuestionsList = new ArrayList<>();
        int count;

        try (Connection conn = sql2o.open()) {


            String sqlGetNumberOfQuestions = "SELECT COUNT(id) FROM questions";
            int numberOfQuestions = Integer.parseInt(conn.createQuery(sqlGetNumberOfQuestions).executeScalar().toString());

            System.out.println("numberOfQuestions:  " + numberOfQuestions);

            for (int i = 1; i <= numberOfQuestions; i++){

                String sql = "SELECT COUNT(questionId) FROM games WHERE questionId ="+ i +";";

                count = Integer.parseInt(conn.createQuery(sql).executeScalar().toString());

                System.out.println("wie oft wurde welche Frage gespielt?   " + count);
                playedQuestionsList.add(new PlayedQuestion(i, count));
            }


        }

        return playedQuestionsList;
    }

    @Override
    public List<GameResult> getAllGameResults() {

        List<GameResult> gameResultList;

        try (Connection conn = sql2o.open()) {

            String sql = "SELECT * FROM gameresult";
            gameResultList = conn.createQuery(sql).executeAndFetch(GameResult.class);

        }

        return gameResultList;
    }

    @Override
    public int getNumberOfAllQuestions() {

        int numberOfQuestions;

        try (Connection conn = sql2o.open()) {

            String sqlGetNumberOfQuestions = "SELECT COUNT(id) FROM questions";
            numberOfQuestions = Integer.parseInt(conn.createQuery(sqlGetNumberOfQuestions).executeScalar().toString());

        }

        return numberOfQuestions;
    }
}
