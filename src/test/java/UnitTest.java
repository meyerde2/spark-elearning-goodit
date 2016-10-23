import app.dashboard.DashboardDao;
import app.dashboard.DashboardDaoImpl;
import app.dashboard.PlayedQuestion;
import app.game.Game;
import app.game.GameDao;
import app.game.GameDaoImpl;
import app.user.UserDao;
import app.user.UserDaoImpl;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Dennis on 06.10.2016.
 */
public class UnitTest {

    @Test
    public void testGameId() {

        int i = 1 +1;

        //System.out.println("i: " + i);
        String DB_URL = "jdbc:mysql://localhost:3306/goodIT";
        String USER = "root";
        String PASS = "";
        Sql2o sql2o = new Sql2o(DB_URL, USER, PASS);

        UserDao userDao = new UserDaoImpl(sql2o);
        userDao.getAllUsers();

        GameDao gameDao = new GameDaoImpl(sql2o);
        userDao.getLatestOpengameId("admin");

        DashboardDao dashboardDao = new DashboardDaoImpl(sql2o);

        dashboardDao.getAllGameResults();

        List<PlayedQuestion> list = dashboardDao.getPlayedQuestionsCount();

        System.out.println("hello world");

        for (PlayedQuestion entry : list) {
            System.out.println(entry.getQuestionId());
            System.out.println(entry.getPlayScore());
        }

        System.out.println("numberOfAllQuestions:  " + dashboardDao.getNumberOfAllQuestions());
    }


    @Test
    public void testGameResult() {

        String DB_URL = "jdbc:mysql://localhost:3306/goodIT";
        String USER = "root";
        String PASS = "";
        Sql2o sql2o = new Sql2o(DB_URL, USER, PASS);

        GameDao gameDao = new GameDaoImpl(sql2o);
        DashboardDao dashboardDao = new DashboardDaoImpl(sql2o);

        List<Game> currentGameList = gameDao.getAllQuestionsOfCurrentGame(1, 23);


        double d = 0;
        for (Game game : currentGameList) {
            d += game.getResult();
        }

        d = d / currentGameList.size();

        int gameResultValue = ((int) Math.round(d));

        System.out.println("filter:  " + dashboardDao.getAllGameResults().stream().filter(g -> (g.getResult() == 1)).count());
    }

}
