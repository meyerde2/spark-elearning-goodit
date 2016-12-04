package app.dashboard;

import app.game.Game;

import java.util.List;


public interface DashboardDao {

    Game getCurrentGameProgress(String username);

    //Diagramm 2: wie oft wurde welche Frage gespielt?
    List<PlayedQuestion> getPlayedQuestionsCount();

    //Diagramm 3: Wie oft wurden die Spiele insgesamt mit welchen Kategorien abgeschlossen?
    List<GameResult> getAllGameResults();

    //Diagramm 1: Aktuller Spielfortschritt
    int getNumberOfAllQuestions();
    int getNumberOfAllActiveQuestions();

    int getTotalNumberOfAllPlayedGames();
}
