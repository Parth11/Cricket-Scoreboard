package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoardManagerController implements Initializable {

    @FXML
    public Button btnOneRun, btnTwoRuns, btnThreeRuns, btnFourRuns, btnFiveRuns, btnSixRuns, btnSevenRuns, btnUndo;
    @FXML
    public Button btnIncrementOneBall, btnDecrementOneBall;
    @FXML
    public Button btnIncrementOneWicket, btnDecrementOneWicket;
    @FXML
    public ToggleButton toggleButtonToWinOrRB;

    private ProjectorManager firstController = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectorSB.fxml"));
            Parent roo1 = loader.load();
            firstController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("ATMIYA PREMIERE LEAGUE");
            stage.setScene(new Scene(roo1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
    }

    public void incrementRuns(ActionEvent event) {
        if (event.getSource().equals(btnOneRun)) {
            firstController.getScoreManager().addRuns(1);
        } else if (event.getSource().equals(btnTwoRuns)) {
            firstController.getScoreManager().addRuns(2);
        } else if (event.getSource().equals(btnThreeRuns)) {
            firstController.getScoreManager().addRuns(3);
        } else if (event.getSource().equals(btnFourRuns)) {
            firstController.getScoreManager().addRuns(4);
        } else if (event.getSource().equals(btnFiveRuns)) {
            firstController.getScoreManager().addRuns(5);
        } else if (event.getSource().equals(btnSixRuns)) {
            firstController.getScoreManager().addRuns(6);
        } else if (event.getSource().equals(btnSevenRuns)) {
            firstController.getScoreManager().addRuns(7);
        } else if (event.getSource().equals(btnUndo)) {
            firstController.getScoreManager().decrementRun();
        } else if (event.getSource().equals(btnIncrementOneWicket)) {
            firstController.getScoreManager().addWicket();
        } else if (event.getSource().equals(btnDecrementOneWicket)) {
            firstController.getScoreManager().decrementWicket();
        } else if (event.getSource().equals(btnIncrementOneBall)) {
            firstController.getScoreManager().addBall();
        } else if (event.getSource().equals(btnDecrementOneBall)) {
            firstController.getScoreManager().decrementBall();
        } else if (event.getSource().equals(toggleButtonToWinOrRB)) {
            firstController.toggleInning();
        }
        firstController.updateUi();
    }
}
