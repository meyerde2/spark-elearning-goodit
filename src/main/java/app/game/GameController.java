package app.game;

import app.Application;
import app.login.LoginController;
import app.user.User;
import app.util.Path;
import app.util.ViewUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static app.Application.gameDao;
import static app.Application.userDao;
import static app.user.UserController.getCurrentUserRole;
import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.*;

/**
 * Created by Dennis on 29.09.2016.
 */
public class GameController {

    public static Route serveGamePage = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map attributes = new HashMap<>();
            attributes.putAll(ViewUtil.getTemplateVariables(request));


            String currentUsername = request.session().attribute("currentUser");

            System.out.println("currentUsername serveGamePage:  " + currentUsername);
            Question question = gameDao.getNextQuestion(currentUsername);

            userDao.getAllUsers();

            //is the current game at the end?
            if (question == null || userDao.getUserByUsername(currentUsername).getOpenGameId() == 0){
                System.out.println("question: " +question + "  - getopengameid:  " + userDao.getUserByUsername(currentUsername).getOpenGameId());

                return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMEEND));

            }else{

                attributes.put("question", question);

                return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAME));

            }

        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };


    public static Route serveGamecontrolPage = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);

        if (getCurrentUserRole(request.session().attribute("currentUser")) == 1) {
            if (clientAcceptsHtml(request)) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.putAll(ViewUtil.getTemplateVariables(request));

                attributes.put("questions", gameDao.getAllQuestions());

                return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMECONTROL));
            }
            if (clientAcceptsJson(request)) {
                return dataToJson(userDao.getAllUserNames());
            }

        }
        return ViewUtil.forbidden.handle(request, response);
    };

    public static Route createNewQuestion = (Request request, Response response) -> {

        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.putAll(ViewUtil.getTemplateVariables(request));

            System.out.println("before object-parsing");

            System.out.println("situation:" + request.queryParams("situation"));
            System.out.println("question:" + request.queryParams("question"));
            System.out.println("answer1:" + request.queryParams("answer1"));
            System.out.println("category1:" + request.queryParams("category1"));
            System.out.println("active:" + request.queryParams("active"));

            boolean active = false;
            if (request.queryParams("active").equals("true")){
                active = true;
            }

            Question question = new Question(0, request.queryParams("situation"), request.queryParams("question"), request.queryParams("answer1"),
                   Integer.parseInt(request.queryParams("category1")), request.queryParams("answer2"), Integer.parseInt(request.queryParams("category2")), request.queryParams("answer3"),
                   Integer.parseInt(request.queryParams("category3")), request.queryParams("answer4"), Integer.parseInt(request.queryParams("category4")), request.queryParams("answer5"),
                   Integer.parseInt(request.queryParams("category5")), "files", active);

            System.out.println("before database");
            //send question to datebase
            gameDao.createNewQuestion(question);

            response.redirect(Path.Web.GAMECONTROL);

            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMECONTROL));
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };


    public static Route updateQuestion = (Request request, Response response) -> {

        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map<String, Object> attributes = new HashMap<>();


            Question question = gameDao.getQuestionById(Integer.parseInt(request.queryParams("id")));

            System.out.println("-------------- Question-Update -----------------");
            System.out.println("situation:" + request.queryParams("situation"));
            System.out.println("question:" + request.queryParams("question"));
            System.out.println("answer1:" + request.queryParams("answer1"));
            System.out.println("category1:" + request.queryParams("category1"));
            System.out.println("active:" + request.queryParams("active"));

            //question.s

            gameDao.updateQuestion(question);

            response.redirect(Path.Web.GAMECONTROL);

            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMECONTROL));
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };




    public static Route evaluateGameScore = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.putAll(ViewUtil.getTemplateVariables(request));

            System.out.println("Hello GameScore");

            String currentUsername = request.session().attribute("currentUser");


            int questionId = Integer.parseInt(request.queryParams("id"));

            Question currentQuestion = gameDao.getQuestionById(questionId);


            int currentAnswer = Integer.parseInt(request.queryParams("answer"));
            int currentCategory;

            switch(currentAnswer){
                case 1:
                    currentCategory = currentQuestion.getCatId1();
                    break;
                case 2:
                    currentCategory = currentQuestion.getCatId2();
                    break;
                case 3:
                    currentCategory = currentQuestion.getCatId3();
                    break;
                case 4:
                    currentCategory = currentQuestion.getCatId4();
                    break;
                case 5:
                    currentCategory = currentQuestion.getCatId5();
                    break;
                default:
                    currentCategory = 0;
            }

            // send current Game to DB
            userDao.getAllUsers();
            User currentUser = userDao.getUserByUsername(currentUsername);

            System.out.println("openGameID: bevor es gespeichert wird:  " + currentUser.getOpenGameId());
            Game currentGame = new Game(0, currentQuestion.getId(), currentUser.getId(), currentCategory, currentUser.getOpenGameId());

            gameDao.saveGame(currentGame);

            // prepare next Question
            Question nextQuestion = gameDao.getNextQuestion(currentUsername);

            if (nextQuestion == null){

                //Durchgespielt...

                //opengameId = 0 zurückdrehen
                userDao.updateOpengameId(currentUser, 0);

                //ToDo: Ermittlung des Gesamtergebnisses

                //ToDo: Insert in gameresult mit dem Gesamtergebnis aller Fragen

                System.out.println("Keine weiteren Fragen verfügbar.");
                return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMEEND));

            }else{
                System.out.println("question in Controller:  " + nextQuestion.getQuestion());
                attributes.put("question", nextQuestion);

                response.redirect(Path.Web.GAME);
            }
            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAME));

        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };



    public static Route restartGame = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.putAll(ViewUtil.getTemplateVariables(request));

            String currentUsername = request.session().attribute("currentUser");

            //set new openGameId


            int opengameId = userDao.getLatestOpengameId(currentUsername) + 1 ;
            System.out.println("opengamedID im Restart + 1:  " + opengameId);

            User currentUser = userDao.getUserByUsername(currentUsername);

            userDao.updateOpengameId(currentUser, opengameId);

           // Question question = gameDao.getNextQuestion(currentUsername);


           // attributes.put("question", question);

            System.out.println("-----------ENDE Restart Game-----------");
            response.redirect(Path.Web.GAME);
            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAME));


        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);

    };

    public static Route serveOneQuestion = (Request request, Response response) -> {

        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.putAll(ViewUtil.getTemplateVariables(request));

            //getQuestionById

            System.out.println("questionId, ausgewählt: " + getParamQuestionId(request));
            Question question = gameDao.getQuestionById(Integer.parseInt(getParamQuestionId(request)));

            attributes.put("question", question);
            return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.UPDATEQUESTION));

        }
        if (clientAcceptsJson(request)) {
            return dataToJson(userDao.getAllUserNames());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };

}
