package com.mprtcz.snake.game;

import com.mprtcz.snake.drawer.Drawer;
import com.mprtcz.snake.logger.SnakeGameLogger;
import com.mprtcz.snake.snake.Snake;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.Objects;
import java.util.Random;
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
    private Integer pointBrick;
    private Direction tempDirection;

    public GameAgent(Canvas gameCanvas) {
        gameDrawer = new Drawer(gameCanvas);
        snake = new Snake(gameDrawer.getMiddleSquareIndex());
        direction = tempDirection = Direction.LEFT;
        determineDirection();
    }

    public void play() {
        pickRandomBrick();
        drawSnakeLater();

        for (int i = 0; i < 500; i++) {
            if(Objects.equals(nextBrick, pointBrick)){
                snake.moveSnakeAndIncrement(nextBrick);
            } else {
                snake.moveSnake(nextBrick);
            }
            pickRandomBrick();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            direction = tempDirection;

            determineDirection();
            drawSnakeLater();
        }
    }

    public void handleKeyPressedEvents(KeyEvent event) {
        tempDirection = Direction.getInstance(event.getCode(), direction);
    }

    private void drawSnakeLater() {
        Platform.runLater(() -> gameDrawer.drawSnake(snake, pointBrick));
    }

    private void determineDirection() {
        Integer firstNode = snake.getSnakeNodes().get(0);
            if (direction == Direction.LEFT) {
                nextBrick = firstNode - 1;
            } else if (direction == Direction.RIGHT) {
                nextBrick = firstNode + 1;
            } else if (direction == Direction.DOWN) {
                nextBrick = firstNode + gameDrawer.getNumberOfColumns();
            } else if (direction == Direction.UP) {
                nextBrick = firstNode - gameDrawer.getNumberOfColumns();
            } else {
                System.out.println("Unrecognized direction!");
            }
    }

    private void pickRandomBrick(){
        Random random = new Random();
        Integer pickedIndex = random.nextInt(gameDrawer.getNumberOfBasicSquares() - 1);
        if(snake.getSnakeNodes().contains(pickedIndex)){
            System.out.println("Snake " + snake.toString() + " contains " +pickedIndex);
            pickRandomBrick();
        }
        pointBrick = pickedIndex;
    }
}


