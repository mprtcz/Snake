package com.mprtcz.snake;

import com.mprtcz.snake.game.GameAgent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Controller {

    public Canvas drawingCanvas;
    public Button startGameButton;
    public Label snakeSpeedLabel, pointsLabel;
    public Slider snakeSpeedSlider;
    private GameAgent gameAgent;

    public void onStartGameButtonClicked(){
        Thread thread = new Thread(this::playGame);
        thread.setName("Game Thread");
        thread.start();
    }

    private void playGame(){
        gameAgent = new GameAgent(drawingCanvas);
        gameAgent.setPointsLabel(pointsLabel);
        gameAgent.setSpeed((int) snakeSpeedSlider.getValue());
        gameAgent.play();
    }

    public void onMouseReleasedSlider(){
        gameAgent.setSpeed((int) snakeSpeedSlider.getValue());
        startGameButton.requestFocus();
    }

    public void initialize() {
        startGameButton.setOnKeyPressed(event -> gameAgent.handleKeyPressedEvents(event));
    }
}
