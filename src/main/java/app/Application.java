package app;


import app.dashboard.DashboardController;
import app.game.GameController;
import app.game.GameDao;
import app.game.GameDaoImpl;
import app.index.IndexController;
import app.login.LoginController;
import app.user.UserController;
import app.user.UserDao;
import app.user.UserDaoImpl;
import app.util.MessageBundle;
import app.util.Path;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static app.util.RequestUtil.getSessionCurrentUser;
import static app.util.RequestUtil.getSessionLocale;
import static spark.Spark.get;
import static spark.Spark.post;


/**
 * Created by Dennis on 18.10.2016.
 */
public class Application{


    public static UserDao userDao;
    public static GameDao gameDao;
    public static  FreeMarkerEngine freeMarkerEngine;

    public static void main(String args[]) {

            Spark.staticFileLocation("/public");
            DebugScreen.enableDebugScreen();

            freeMarkerEngine = new FreeMarkerEngine();
            Configuration freeMarkerConfiguration = new Configuration();

            freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Application.class, "/public/templates/"));
            freeMarkerEngine.setConfiguration(freeMarkerConfiguration);


            try {
                Class.forName("com.mysql.jdbc.Driver");
                String DB_URL = "jdbc:mysql://localhost:3306/goodIT";
                String USER = "root";
                String PASS = "";
                Sql2o sql2o = new Sql2o(DB_URL, USER, PASS);

                userDao = new UserDaoImpl(sql2o);
                gameDao = new GameDaoImpl(sql2o);

                System.out.println(userDao.getAllUsers());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            //Login
            get("/login/", LoginController.serveLoginPage);
            post("/login/", LoginController.handleLoginPost);

            //Logout
            post("/logout/", LoginController.handleLogoutPost);

            //Index
            get("/index/", IndexController.serveIndexPage);

            //Dashboard
            get("/dashboard/", DashboardController.serveDashboardPage);

            //Game
            get("/game/", GameController.serveGamePage);
            get("/gamecontrol/", GameController.serveGamecontrolPage);
            get("/question/:id/", GameController.serveOneQuestion);

            post("/createNewQuestion/", GameController.createNewQuestion);
            post("/gamescore/", GameController.evaluateGameScore);
            post("/restartGame/", GameController.restartGame);

            //User
            get("/usercontrol/", UserController.serveUsercontrolPage);

            get("/user/:username/", UserController.serveOneUser);

            post("/createNewUser/", UserController.serveNewUser);
            post("/updateUser/", UserController.updateUser);


            get("/hello", (request, response) -> {

                System.out.println("hello world......");

                Map<String, Object> attributes = new HashMap<>();
                attributes.put("message", "Hello World, my Friend!");

                attributes.put("users", userDao.getAllUsers());

                // The login.ftl file is located in directory:
                // src/test/resources/spark/template/freemarker

                attributes.put("msg", new MessageBundle(getSessionLocale(request)));
                attributes.put("currentUser", getSessionCurrentUser(request));

                attributes.put("WebPath", Path.Web.class);

                return new ModelAndView(attributes, "/login.ftl");
            }, new FreeMarkerEngine());

        }

    /*
    @Override
    public void init() {
        Spark.staticFileLocation("/public");
        DebugScreen.enableDebugScreen();

        freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();

        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Application.class, "/public/templates/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/goodIT";
            String USER = "root";
            String PASS = "";
            Sql2o sql2o = new Sql2o(DB_URL, USER, PASS);

            userDao = new UserDaoImpl(sql2o);

            System.out.println(userDao.getAllUsers());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //Login
        get("/login/", LoginController.serveLoginPage);
        post("/login/", LoginController.handleLoginPost);

        //Logout
        post("/logout/", LoginController.handleLogoutPost);


        //Index
        get("/index/", IndexController.serveIndexPage);

        //Dashboard
        get("/dashboard/", DashboardController.serveDashboardPage);

        //Game
        get("/game/", GameController.serveGamePage);
        get("/gamecontrol/", GameController.serveGamecontrolPage);

        //Users

        get("/hello", (request, response) -> {

            System.out.println("hello world......");




            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World, my Friend!");

            attributes.put("users", userDao.getAllUsers());

            // The login.ftl file is located in directory:
            // src/test/resources/spark/template/freemarker

            attributes.put("msg", new MessageBundle(getSessionLocale(request)));
            attributes.put("currentUser", getSessionCurrentUser(request));

            attributes.put("WebPath", Path.Web.class);

            return new ModelAndView(attributes, "/login.ftl");
        }, new FreeMarkerEngine());



        get("/user", (request, response) -> {

            System.out.println("hello world..USERNAMES....");


            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "This is the User Page, my Friend!");


            attributes.put("usernames", userDao.getAllUserNames());



            attributes.put("msg", new MessageBundle(getSessionLocale(request)));
            attributes.put("currentUser", getSessionCurrentUser(request));

            attributes.put("WebPath", Path.Web.class);

            // The login.ftl file is located in directory:
            // src/test/resources/spark/template/freemarker
            return new ModelAndView(attributes, "/login.ftl");
        }, new FreeMarkerEngine());

    }

    */

}
