package app.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
public class GameResult {

    @Getter @Setter int id;
    @Getter @Setter int userId;
    @Getter @Setter int gameId;
    @Getter @Setter int endresult;


}
