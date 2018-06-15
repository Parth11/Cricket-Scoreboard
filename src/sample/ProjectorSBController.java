package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorSBController implements ProjectorManager {
    private static final int NUMBER_OF_OVERS_PER_INNING = 10;
    private static final String SCORE_WICKET_SEPARATOR = "-";
    private static final String OVERS_RUN_SEPARATOR = ".";

    @FXML
    public ImageView team1IV_ID, team2IV_ID;
    @FXML
    public Label numberOfRunsAndWicketsLabel_ID;
    @FXML
    public Label numberOfOversLabel_ID;
    @FXML
    public Label toWinLabel_ID, numbersOfRunsToWinLabel_ID;
    @FXML
    public Label remainingBallsLabel_ID, numbersOfBallsLabel_ID;
    @FXML
    public ComboBox<TeamInfo> team1CB_ID, team2CB_ID;

    private ObservableList<TeamInfo> teamList = FXCollections.observableArrayList(TeamInfo.values());
    private ScoreManager firstInningScoreManager;
    private ScoreManager secondInningScoreManager;
    private ScoreManager currentInningScoreManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstInningScoreManager = new ScoreManager(NUMBER_OF_OVERS_PER_INNING);
        currentInningScoreManager = firstInningScoreManager;
        team1CB_ID.setItems(teamList);
        team2CB_ID.setItems(teamList);
        toggleWinRbPanelOnProjector(false);
    }

    private void onComboBoxChange(ImageView imageView, TeamInfo selectedValue) {
        imageView.setImage(new Image(selectedValue.TEAM_LOGO_FILE_NAME));
    }

    public void comboChangedForTeam1(ActionEvent inputEvent) {
        onComboBoxChange(team1IV_ID, team1CB_ID.getValue());
    }

    public void comboChangedForTeam2(ActionEvent inputEvent) {
        onComboBoxChange(team2IV_ID, team2CB_ID.getValue());
    }

    @Override
    public ScoreManager getScoreManager() {
        return currentInningScoreManager;
    }

    @Override
    public void toggleInning() {
        if(currentInningScoreManager == firstInningScoreManager) {
            startOrUpdateSecondInning();
            currentInningScoreManager = secondInningScoreManager;
            toggleWinRbPanelOnProjector(true);
        }
        else {
            currentInningScoreManager = firstInningScoreManager;
            toggleWinRbPanelOnProjector(false);
        }
        updateUi();
    }

    @Override
    public void updateUi() {
        updateScore();
        updateBalls();
        updateSecondInningUiPanel();
    }

    private void startOrUpdateSecondInning() {
        if(secondInningScoreManager == null) {
            secondInningScoreManager = new ScoreManager(NUMBER_OF_OVERS_PER_INNING, firstInningScoreManager.getScore() + 1);
        } else {
            secondInningScoreManager.resetNewTargetRuns(firstInningScoreManager.getScore() + 1);
        }
        updateUi();
    }

    private void toggleWinRbPanelOnProjector(final boolean enable) {
        Platform.runLater(() -> {
            toWinLabel_ID.setVisible(enable);
            numbersOfRunsToWinLabel_ID.setVisible(enable);
            remainingBallsLabel_ID.setVisible(enable);
            numbersOfBallsLabel_ID.setVisible(enable);
        });
    }

    private void updateScore() {
        Platform.runLater(() -> numberOfRunsAndWicketsLabel_ID.setText(currentInningScoreManager.getScore() + SCORE_WICKET_SEPARATOR
                + currentInningScoreManager.getWickets()));
    }

    private void updateBalls() {
        Platform.runLater(() -> numberOfOversLabel_ID.setText(currentInningScoreManager.getOvers() + OVERS_RUN_SEPARATOR
                + currentInningScoreManager.getBalls()));
    }

    private void updateSecondInningUiPanel() {
        Platform.runLater(() -> {
            numbersOfRunsToWinLabel_ID.setText("" + currentInningScoreManager.getRemainingRunsToWin());
            numbersOfBallsLabel_ID.setText("" + currentInningScoreManager.getRemainingBalls());
        });
    }
}
