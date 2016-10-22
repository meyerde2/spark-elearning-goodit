package app.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dennis on 06.10.2016.
 */
@Data
@AllArgsConstructor
public class Game {
    @Getter @Setter public int id;
    @Getter @Setter public int questionId;
    @Getter @Setter public int userId;
    @Getter @Setter public int result;
    @Getter @Setter public int opengameId;
}
