package com.mprtcz.snake;

import com.mprtcz.snake.game.GameAgent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Controller {

    public Canvas drawingCanvas;
    public Button startGameButton;
    private GameAgent gameAgent;

    public void onStartGameButtonClicked(){
        Thread thread = new Thread(this::playGame);
        thread.setName("Game Thread");
        thread.start();
    }

    private void playGame(){
        gameAgent = new GameAgent(drawingCanvas);
        gameAgent.play();
    }

    public void initialize() {
        startGameButton.setOnKeyPressed(event -> gameAgent.handleKeyPressedEvents(event));
    }
}
