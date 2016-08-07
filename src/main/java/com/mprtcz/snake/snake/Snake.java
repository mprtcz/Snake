package com.mprtcz.snake.snake;

import com.mprtcz.snake.logger.SnakeGameLogger;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class Snake {
    private final static Logger logger = Logger.getLogger(SnakeGameLogger.class.getName());
    private Level level = Level.CONFIG;

    private LinkedList<Integer> snakeNodes = new LinkedList<>();

    public Snake(int firstNode) {
        snakeNodes.add(firstNode);
        snakeNodes.add(firstNode + 1);
        snakeNodes.add(firstNode + 2);
    }

    public List<Integer> getSnakeNodes() {
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
