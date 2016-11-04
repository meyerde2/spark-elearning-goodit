package app.dashboard;

import app.Application;
import app.login.LoginController;
import app.util.Path;
import app.util.ViewUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Application.dashboardDao;
import static app.Application.userDao;
import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.clientAcceptsHtml;
import static app.util.RequestUtil.clientAcceptsJson;

/**
 * Created by Dennis on 29.09.2016.
 */
public class DashboardController {

    public static Route serveDashboardPage = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {

            System.out.println("DashboardController serveDashboardPage");

            Map attributes = new HashMap<>();

            attributes.putAll(ViewUtil.getTemplateVariables(request));
            attributes.put("currentPage", "dashboard");

            //Dashboard-Diagram 1: Mein aktueller Spielfortschritt?
            attributes.put("numberOfAllQuestions", dashboardDao.getNumberOfAllQuestions());
            attributes.put("lastPlayedQuestion", dashboardDao.getCurrentGameProgress(request.session().attribute("currentUser")));

            //Dashboard-Diagram 2: Wie oft wurde welche Frage gespielt?

            attributes.put("playedQuestionsCountList", dashboardDao.getPlayedQuestionsCount());
            attributes.put("totalNumberOfAllPlayedGames", dashboardDao.getTotalNumberOfAllPlayedGames());


            //Dashboard-Diagram 3: Wie viele Planspiele wurden mit welchem Status abgeschlossen?


            List<GameResult> gameResultList = dashboardDao.getAllGameResults();
            attributes.put("resultCategory1", gameResultList.stream().filter(g -> (g.getEndresult() == 1)).count());
            attributes.put("resultCategory2", gameResultList.stream().filter(g -> (g.getEndresult() == 2)).count());
            attributes.put("resultCategory3", gameResultList.stream().filter(g -> (g.getEndresult() == 3)).count());
            attributes.put("numberOfTotalResults", gameResultList.size());

            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.DASHBOARD));
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };


}
