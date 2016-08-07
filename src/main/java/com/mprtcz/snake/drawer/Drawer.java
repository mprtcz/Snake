package com.mprtcz.snake.drawer;

import com.mprtcz.snake.snake.Snake;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Drawer {
    private Canvas gameCanvas;
    private int numberOfColumns = 30;
    private int numberOfBasicSquares;
    private int basicSquareSize;

    public Drawer(Canvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        this.basicSquareSize = (int) gameCanvas.getWidth() / numberOfColumns;
        determineNumberOfBasicSquares();
    }

    private void determineNumberOfBasicSquares() {
        this.numberOfBasicSquares = numberOfColumns * (int) (gameCanvas.getHeight() / basicSquareSize);
    }

    public int getMiddleSquareIndex() {
        return numberOfBasicSquares / 2;
    }

    public void drawSnake(Snake snake) {
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeRect(0, 0, (numberOfColumns * basicSquareSize), (basicSquareSize * numberOfBasicSquares / numberOfColumns));
        for (Integer index : snake.getSnakeNodes()) {
            graphicsContext.fillRoundRect(getXCoordinate(index), getYCoordinate(index), basicSquareSize, basicSquareSize, 10, 10);
        }
    }

    private int getXCoordinate(int index){
        int xCoordinate = (index % numberOfColumns) * basicSquareSize;
        if(index > numberOfBasicSquares -1){
            return -1;
        } else {
            return xCoordinate;
        }
    }

    private int getYCoordinate(int index){
        int yCoordinate = (index / numberOfColumns) * basicSquareSize;
        if(index > numberOfBasicSquares -1){
            return -1;
        } else {
            return yCoordinate;
        }
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
