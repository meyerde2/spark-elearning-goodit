package app.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dennis on 29.09.2016.
 */

@Data
@AllArgsConstructor
public class Question {
    @Getter @Setter public int id;
    @Getter @Setter public String description;
    @Getter @Setter public String question;
    @Getter @Setter public String answer1;
    @Getter @Setter public int catId1;
    @Getter @Setter public String answer2;
    @Getter @Setter public int catId2;
    @Getter @Setter public String answer3;
    @Getter @Setter public int catId3;
    @Getter @Setter public String answer4;
    @Getter @Setter public int catId4;
    @Getter @Setter public String answer5;
    @Getter @Setter public int catId5;
    @Getter @Setter public String files;
    @Getter @Setter public boolean isActive;

}
