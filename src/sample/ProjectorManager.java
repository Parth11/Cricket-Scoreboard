package sample;

import javafx.fxml.Initializable;

public interface ProjectorManager extends Initializable {
    ScoreManager getScoreManager();

    void toggleInning();

    void updateUi();
}
