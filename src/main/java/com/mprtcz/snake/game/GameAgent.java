package com.mprtcz.snake.game;

import com.mprtcz.snake.drawer.Drawer;
import com.mprtcz.snake.logger.SnakeGameLogger;
import com.mprtcz.snake.snake.Snake;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class GameAgent {
    private final static Logger logger = Logger.getLogger(SnakeGameLogger.class.getName());
    private Level level = Level.CONFIG;


    private Drawer gameDrawer;
    private Snake snake;
    private Direction direction;
    private Integer nextBrick;

    public GameAgent(Canvas gameCanvas) {
        gameDrawer = new Drawer(gameCanvas);
        snake = new Snake(gameDrawer.getMiddleSquareIndex());
        direction = Direction.LEFT;
        getNextBrick();
    }

    public void play() {
        drawSnakeLater();

        for (int i = 0; i < 50; i++) {
            snake.moveSnake(nextBrick);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getNextBrick();
            drawSnakeLater();
        }
    }

    public void handleKeyPressedEvents(KeyEvent event) {
        direction = Direction.getInstance(event.getCode(), direction);
    }

    private void drawSnakeLater() {
        Platform.runLater(() -> gameDrawer.drawSnake(snake));
    }

    private void getNextBrick() {
        Integer firstNode = snake.getSnakeNodes().get(0);
        if (direction == Direction.LEFT) {
            nextBrick = firstNode - 1;
        } else if (direction == Direction.RIGHT) {
            nextBrick = firstNode + 1;
        } else if (direction == Direction.DOWN){
            nextBrick = firstNode + gameDrawer.getNumberOfColumns();
        } else if (direction == Direction.UP){
            nextBrick = firstNode - gameDrawer.getNumberOfColumns();
        } else {
            System.out.println("Unrecognized direction!");
        }
    }
}


