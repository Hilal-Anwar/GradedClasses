package org.graded;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader1 = new FXMLLoader(MFXDemoResourcesLoader.loadURL("time_table_view.fxml"));
        FXMLLoader loader2 = new FXMLLoader(MFXDemoResourcesLoader.loadURL("leader_board_view1.fxml"));
        FXMLLoader loader3 = new FXMLLoader(MFXDemoResourcesLoader.loadURL("leader_board_view2.fxml"));
        //StackPane pane1 = loader1.load();
        StackPane pane2 = loader2.load();
        StackPane pane3 = loader3.load();
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        LayoutAnimator layoutAnimator = new LayoutAnimator(root,  pane2, pane3);
        layoutAnimator.animate();
        stage.setScene(scene);
        stage.setTitle("Graded Management");
        scene.setOnKeyPressed(event -> {
            stage.setFullScreen(event.getCode() == KeyCode.F11 && !stage.isFullScreen());
        });
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("__logo.png"))));
        stage.show();
       /* Stage stage2 = new Stage();
        stage2.setTitle("Graded Management");
        stage2.setScene(new Scene(new AnchorPane(),500,600));
        stage2.setResizable(false);
        stage2.show();*/
    }

}
