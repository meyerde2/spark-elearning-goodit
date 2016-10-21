package app.util;

import lombok.Getter;

public final class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {

        public Web() {
        }

        @Getter public static final String INDEX = "/index/";
        @Getter public static final String LOGIN = "/login/";
        @Getter public static final String USERCONTROL = "/usercontrol/";
        @Getter public static final String LOGOUT = "/logout/";
        @Getter public static final String DASHBOARD ="/dashboard/";
        @Getter public static final String GAMECONTROL ="/gamecontrol/";
        @Getter public static final String GAME ="/game/";

    }

    public static class Template {
        public final static String INDEX = "index/index.ftl";
        public final static String LOGIN = "login/login.ftl";
        public final static String USERCONTROL = "usercontrol/usercontrol.ftl";
        public final static String GAMECONTROL = "gamecontrol/gamecontrol.ftl";
        public final static String GAME = "game/game.ftl";
        public final static String DASHBOARD = "dashboard/dashboard.ftl";
        public static final String NOT_FOUND = "notFound.vm";
        public static final String GAMEEND = "game/gameend.ftl";
        public static final String UPDATEQUESTION = "gamecontrol/updateQuestion.ftl";
        public static final String UPDATEUSER = "usercontrol/updateUser.ftl";
    }

}
