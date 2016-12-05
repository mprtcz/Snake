package com.mprtcz.snake;

import com.mprtcz.snake.game.GameAgent;
import com.mprtcz.snake.logger.SnakeGameLogger;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Controller {
    private final static Logger logger = Logger.getLogger(SnakeGameLogger.class.getName());
    private Level level = Level.CONFIG;

    public Canvas drawingCanvas;
    public Button startGameButton;
    public Label snakeSpeedLabel, pointsLabel;
    public Slider snakeSpeedSlider;
    public ListView<Integer> pointsListView;
    public Label meanPointsLabel;

    private GameAgent gameAgent;

    public void initialize() {
            startGameButton.setOnKeyPressed(event -> gameAgent.handleKeyPressedEvents(event));
    }

    public void onStartGameButtonClicked() {
        if(gameAgent == null) {
            startNewGame();
        } else if(gameAgent.isGameRunning) {
            gameAgent.terminateGame();
            cleanUpGame();
        } else {
            startNewGame();
        }
    }

    private void playGame() {
        gameAgent = new GameAgent(drawingCanvas);
        gameAgent.setPointsLabel(pointsLabel);
        gameAgent.setSpeed((int) snakeSpeedSlider.getValue());
        Platform.runLater(() -> snakeSpeedLabel.setText("Snake Speed: " +(int) snakeSpeedSlider.getValue()));
        gameAgent.play();
        cleanUpGame();
        addToPointsList(gameAgent.getPoints());
    }

    public void onMouseReleasedSlider() {
        if(gameAgent != null) {
            gameAgent.setSpeed((int) snakeSpeedSlider.getValue());
            snakeSpeedLabel.setText("Snake Speed: " + (int) snakeSpeedSlider.getValue());
            startGameButton.requestFocus();
        }
    }

    private void cleanUpGame() {
        Platform.runLater(() -> startGameButton.setText("Start Game"));
        Platform.runLater(() -> pointsLabel.setText("Points:"));
    }

    private void startNewGame() {
        Thread thread = new Thread(this::playGame);
        thread.setName("Game Thread");
        thread.start();
        startGameButton.setText("Stop Game");
    }

    private void addToPointsList(int points) {
        Platform.runLater(() ->
                pointsListView.getItems().add(0, points));
        if(pointsListView.getItems().size() > 1) {
            int sum = 0;
            int numberOfGames = 0;
            for (int record :
                    pointsListView.getItems()) {
                sum += record;
                numberOfGames++;
            }
            String meanPoints = "Mean points: " +sum/pointsListView.getItems().size() +" / " +numberOfGames + " games";
            Platform.runLater(() -> meanPointsLabel.setText(meanPoints));
        }
    }
}
