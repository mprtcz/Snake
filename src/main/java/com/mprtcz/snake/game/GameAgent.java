package com.mprtcz.snake.game;

import com.mprtcz.snake.drawer.Drawer;
import com.mprtcz.snake.logger.SnakeGameLogger;
import com.mprtcz.snake.snake.Snake;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
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
    private boolean isGameRunning;
    private Label pointsLabel;
    private int points;
    private int speed;

    public GameAgent(Canvas gameCanvas) {
        gameDrawer = new Drawer(gameCanvas);
        snake = new Snake(gameDrawer.getMiddleSquareIndex());
        direction = tempDirection = Direction.LEFT;
        calculateNextBrick();
    }

    public void play() {
        pickRandomBrick();
        drawSnakeLater();

        isGameRunning = true;
        while (isGameRunning) {
            if (Objects.equals(nextBrick, pointBrick)) {
                snake.moveSnakeAndIncrement(nextBrick);
                addPoints();
                pickRandomBrick();
            } else {
                snake.moveSnake(nextBrick);
            }
            try {
                Thread.sleep(1000/speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            direction = tempDirection;

            calculateNextBrick();
            checkAllCollisions();
            drawSnakeLater();
        }
    }

    public void handleKeyPressedEvents(KeyEvent event) {
        tempDirection = Direction.getInstance(event.getCode(), direction);
    }

    private void drawSnakeLater() {
        Platform.runLater(() -> gameDrawer.drawSnake(snake, pointBrick));
    }

    private void calculateNextBrick() {
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

    private void pickRandomBrick() {
        Random random = new Random();
        Integer pickedIndex = random.nextInt(gameDrawer.getNumberOfBasicSquares() - 1);
        if (snake.getSnakeNodes().contains(pickedIndex)) {
            System.out.println("Snake " + snake.toString() + " contains " + pickedIndex);
            pickRandomBrick();
        }
        pointBrick = pickedIndex;
    }

    private void checkSnakeCollision() {
        if (snake.getSnakeNodes().contains(nextBrick)) {
            isGameRunning = false;
        }
    }

    private void checkUpperWallCollision(){
        if(nextBrick < 0){
            isGameRunning = false;
        }
    }

    private void checkLowerWallCollision(){
        if(nextBrick > gameDrawer.getNumberOfBasicSquares() - 1) {
            isGameRunning = false;
        }
    }

    private void checkSideWallsCollision(){
        if(direction == Direction.LEFT || direction == Direction.RIGHT) {
            int nextBrickColumn = nextBrick%gameDrawer.getNumberOfColumns();
            int firstNodeColumn = snake.getSnakeNodes().get(0)%gameDrawer.getNumberOfColumns();
            if (Math.abs(firstNodeColumn - nextBrickColumn) > 1) {
                isGameRunning = false;
            }
        }
    }

    private void checkAllCollisions(){
        checkSnakeCollision();
        checkUpperWallCollision();
        checkLowerWallCollision();
        checkSideWallsCollision();
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setPointsLabel(Label label){
        this.pointsLabel = label;
    }

    private void addPoints(){
        points += speed;
        String pointsString = "Points:\n" + String.valueOf(points);
        Platform.runLater(() -> pointsLabel.setText(pointsString));
    }
}


