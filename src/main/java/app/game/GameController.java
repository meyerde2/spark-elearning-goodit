package app.game;

import app.Application;
import app.dashboard.GameResult;
import app.login.LoginController;
import app.user.User;
import app.util.Path;
import app.util.ViewUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Application.gameDao;
import static app.Application.userDao;
import static app.user.UserController.getCurrentUserRole;
import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.*;


public class GameController {

    public static Route serveGamePage = (Request request, Response response) -> {

        LoginController.ensureUserIsLoggedIn(request, response);

        if (clientAcceptsHtml(request)) {

            Map<String, Object> attributes = new HashMap<>();

            attributes.putAll(ViewUtil.getTemplateVariables(request));
            attributes.put("currentPage", "game");

            String currentUsername = request.session().attribute("currentUser");
            userDao.getAllUsers();

            Question question = gameDao.getNextQuestion(currentUsername);
            //end of game?
            if (question == null || userDao.getUserByUsername(currentUsername).getOpenGameId() == 0){
                return Application.freeMarkerEngine.render(new ModelAndView(attributes, Path.Template.GAMEEND));
            }else{
                int questionPosition = 0;
                int i = 0;

                for (Question q : gameDao.getAllActiveQuestions()) {
                    i++;
                    if (q.getId() == question.getId()){
                        questionPosition = i;
                    }
                }
                attributes.put("currentQuestionPosition", questionPosition);
                attributes.put("question", question);
                attributes.put("questionCount", gameDao.getAllActiveQuestions().size());
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
                attributes.put("currentPage", "gamecontrol");


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


            System.out.println("situation:" + request.queryParams("situation"));
            System.out.println("question:" + request.queryParams("question"));
            System.out.println("answer1:" + request.queryParams("answer1"));
            System.out.println("category1:" + request.queryParams("category1"));
            System.out.println("active:" + request.queryParams("active"));

            boolean active = false;
            if ("true".equals(request.queryParams("active"))){
                active = true;
            }

            Question question = new Question(0, request.queryParams("situation"), request.queryParams("question"), request.queryParams("answer1"),
                   Integer.parseInt(request.queryParams("category1")), request.queryParams("answer2"), Integer.parseInt(request.queryParams("category2")), request.queryParams("answer3"),
                   Integer.parseInt(request.queryParams("category3")), request.queryParams("answer4"), Integer.parseInt(request.queryParams("category4")), request.queryParams("answer5"),
                   Integer.parseInt(request.queryParams("category5")), "files", active);

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

            question.setDescription(request.queryParams("situation"));
            question.setQuestion(request.queryParams("question"));

            question.setAnswer1(request.queryParams("answer1"));
            question.setCatId1(Integer.parseInt(request.queryParams("category1")));
            question.setAnswer2(request.queryParams("answer2"));
            question.setCatId2(Integer.parseInt(request.queryParams("category2")));
            question.setAnswer3(request.queryParams("answer3"));
            question.setCatId3(Integer.parseInt(request.queryParams("category3")));

            //If not empty
            if (!request.queryParams("answer4").isEmpty() && !request.queryParams("category4").isEmpty()){
                question.setAnswer4(request.queryParams("answer4"));
                question.setCatId4(Integer.parseInt(request.queryParams("category4")));
            }
            if (!request.queryParams("answer5").isEmpty() && !request.queryParams("category5").isEmpty()){
                question.setAnswer5(request.queryParams("answer5"));
                question.setCatId5(Integer.parseInt(request.queryParams("category5")));
            }

            //ToDo: Files
            //question.setFiles();
            question.setActive(Boolean.parseBoolean(request.queryParams("active")));

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
            attributes.put("currentPage", "gamecontrol");

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

            Game currentGame = new Game(0, currentQuestion.getId(), currentUser.getId(), currentCategory, currentUser.getOpenGameId());

            gameDao.saveGame(currentGame);

            // prepare next Question
            Question nextQuestion = gameDao.getNextQuestion(currentUsername);

            if (nextQuestion == null){

                //Durchgespielt...

                //opengameId = 0 zurückdrehen
                userDao.updateOpengameId(currentUser, 0);

                //get all Questions of the current Game
                List<Game> currentGameList = gameDao.getAllQuestionsOfCurrentGame(currentUser.getId(), currentGame.getOpengameId());

                double d = 0;

                for (Game game : currentGameList) {
                    d += game.getResult();
                }

                d = d / currentGameList.size();

                int gameResultValue = ((int) Math.round(d));

                GameResult gameResult = new GameResult(0, currentUser.getId(), currentGame.getOpengameId(), gameResultValue);

                gameDao.saveGameResult(gameResult);

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
            attributes.put("currentPage", "game");

            String currentUsername = request.session().attribute("currentUser");

            //set new openGameId
            int opengameId = userDao.getLatestOpengameId(currentUsername) + 1 ;


            User currentUser = userDao.getUserByUsername(currentUsername);

            userDao.updateOpengameId(currentUser, opengameId);

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
            attributes.put("currentPage", "gamecontrol");


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
