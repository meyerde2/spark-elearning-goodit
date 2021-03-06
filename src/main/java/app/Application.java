package app;

import app.dashboard.DashboardController;
import app.dashboard.DashboardDao;
import app.dashboard.DashboardDaoImpl;
import app.game.GameController;
import app.game.GameDao;
import app.game.GameDaoImpl;
import app.index.IndexController;
import app.login.LoginController;
import app.user.UserController;
import app.user.UserDao;
import app.user.UserDaoImpl;
import app.util.Filters;
import app.util.FreeMarkerEngine;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.sql2o.Sql2o;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;


public class Application implements SparkApplication{


    public static UserDao userDao;
    public static GameDao gameDao;
    public static DashboardDao dashboardDao;

    public static FreeMarkerEngine freeMarkerEngine;

/*
    public static void main(String args[]) {

        Spark.staticFileLocation("/public");

        //Test
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
            dashboardDao = new DashboardDaoImpl(sql2o);

            System.out.println(userDao.getAllUsers());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        before("*", Filters.handleLocaleChange);

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
        post("/updateQuestion/", GameController.updateQuestion);
        post("/gamescore/", GameController.evaluateGameScore);
        post("/restartGame/", GameController.restartGame);

        //User
        get("/usercontrol/", UserController.serveUsercontrolPage);

        get("/user/:username/", UserController.serveOneUser);

        post("/createNewUser/", UserController.serveNewUser);
        post("/updateUser/", UserController.updateUser);

        //Set up after-filters (called after each get/post)
        after("*", Filters.addGzipHeader);

    }

*/

    @Override
    public void init() {
        Spark.staticFileLocation("/public");

        //Test
        //DebugScreen.enableDebugScreen();

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
            dashboardDao = new DashboardDaoImpl(sql2o);

            System.out.println(userDao.getAllUsers());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        before("*", Filters.handleLocaleChange);

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
        post("/updateQuestion/", GameController.updateQuestion);
        post("/gamescore/", GameController.evaluateGameScore);
        post("/restartGame/", GameController.restartGame);

        //User
        get("/usercontrol/", UserController.serveUsercontrolPage);

        get("/user/:username/", UserController.serveOneUser);

        post("/createNewUser/", UserController.serveNewUser);
        post("/updateUser/", UserController.updateUser);

        //Set up after-filters (called after each get/post)
        after("*", Filters.addGzipHeader);

    }

}