package app.game;

/**
 * Created by Dennis on 29.09.2016.
 */
public class Question {
    int id;
    String description;
    String question;
    String answer1;
    int catId1;
    String answer2;
    int catId2;
    String answer3;
    int catId3;
    String answer4;
    int catId4;
    String answer5;
    int catId5;
    String files;
    boolean isActive;

    public Question(int id, String description, String question, String answer1,
                    int catId1, String answer2, int catId2, String answer3,
                    int catId3, String answer4, int catId4, String answer5,
                    int catId5, String files, boolean isActive) {
        this.id = id;
        this.description = description;
        this.question = question;
        this.answer1 = answer1;
        this.catId1 = catId1;
        this.answer2 = answer2;
        this.catId2 = catId2;
        this.answer3 = answer3;
        this.catId3 = catId3;
        this.answer4 = answer4;
        this.catId4 = catId4;
        this.answer5 = answer5;
        this.catId5 = catId5;
        this.files = files;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public int getCatId1() {
        return catId1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public int getCatId2() {
        return catId2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getCatId3() {
        return catId3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getCatId4() {
        return catId4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public int getCatId5() {
        return catId5;
    }

    public String getFiles() {
        return files;
    }

    public boolean isActive() {
        return isActive;
    }
}
