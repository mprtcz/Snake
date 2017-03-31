package com.mprtcz.snake;

import com.mprtcz.snake.logger.SnakeGameLogger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mprtcz on 2016-08-07.
 */
public class App extends Application {

    private final static Logger logger = Logger.getLogger(SnakeGameLogger.class.getName());
    private Level level = Level.INFO;
    private static boolean isLogger = false;

    @Override
    public void start(Stage window) throws Exception {
        if (isLogger) {
            SnakeGameLogger.initializeLogger();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 460);
        window.setOnCloseRequest(e -> {
            logger.log(level, "Close Requested.");
            Platform.exit();
            System.exit(0);
        });
        window.setTitle("Snake");
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-log")) {
                isLogger = true;
            }
        }
        launch(args);
    }
}
