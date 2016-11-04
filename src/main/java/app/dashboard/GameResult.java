package app.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dennis on 16.10.2016.
 */
@Data
@AllArgsConstructor
public class GameResult {

    @Getter @Setter int id;
    @Getter @Setter int userId;
    @Getter @Setter int endresult;
    @Getter @Setter int gameId;

}
