package com.mprtcz.snake.game;

import javafx.scene.input.KeyCode;

/**
 * Created by mprtcz on 2016-08-07.
 */
enum Direction {
    UP(KeyCode.UP),
    DOWN(KeyCode.DOWN),
    LEFT(KeyCode.LEFT),
    RIGHT(KeyCode.RIGHT);

    private KeyCode keyCode;

    Direction(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    static Direction getInstance(KeyCode keyCode, Direction currentDirection){
        for (Direction newDirection :
                Direction.values()) {
            if (newDirection.keyCode == keyCode && !isOpposite(newDirection, currentDirection)){
                return newDirection;
            }
        }
        return currentDirection;
    }

    static boolean isOpposite(Direction newDirection, Direction currentDirection){
        return newDirection == getOpposite(currentDirection);
    }

    static Direction getOpposite(Direction direction){
        if(direction == UP) {return DOWN;}
        else if(direction == DOWN) {return UP;}
        else if(direction == RIGHT) {return LEFT;}
        else if(direction == LEFT) {return RIGHT;}
        else {
            System.out.println("Unknown Direction!");
            return null;
        }
    }

}
