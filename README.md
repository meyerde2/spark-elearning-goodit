spark-elearning-goodit
==============================================

How to deploy the application to external jetty?
- delete the main()-method
- add "implements SparkApplication" to "public class Application"
- enable the init()-method.

```java

public class Application implements SparkApplication{


    public static UserDao userDao;
    public static GameDao gameDao;
    public static  FreeMarkerEngine freeMarkerEngine;

@Override
    public void init() {
        Spark.staticFileLocation("/public");
        DebugScreen.enableDebugScreen();

        freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();

        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Application.class, "/public/templates/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

        .....
    }

```
