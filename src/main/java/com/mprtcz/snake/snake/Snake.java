package com.mprtcz.snake.snake;

import com.mprtcz.snake.logger.SnakeGameLogger;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Snake {
    private final static Logger logger = Logger.getLogger(SnakeGameLogger.class.getName());
    private Level level = Level.CONFIG;

    private LinkedBlockingDeque<Integer> snakeNodes = new LinkedBlockingDeque<>();

    public Snake(int firstNode) {
        snakeNodes.add(firstNode);
        snakeNodes.add(firstNode + 1);
        snakeNodes.add(firstNode + 2);
    }

    public LinkedBlockingDeque<Integer> getSnakeNodes() {
        logger.log(level, "snakeNodes = " + snakeNodes);
        return snakeNodes;
    }

    public void moveSnake(Integer nextNode) {
        snakeNodes.addFirst(nextNode);
        snakeNodes.removeLast();
    }

    public void moveSnakeAndIncrement(Integer nextNode){
        snakeNodes.addFirst(nextNode);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "snakeNodes=" + snakeNodes +
                '}';
    }
}
