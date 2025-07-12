package org.graded;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader1 = new FXMLLoader(MFXDemoResourcesLoader.loadURL("leader_board_view1.fxml"));
        FXMLLoader loader2 = new FXMLLoader(MFXDemoResourcesLoader.loadURL("leader_board_view2.fxml"));
        StackPane leader1 = loader1.load();
        StackPane leader2 = loader2.load();
        var treeMap = listOfWinners("Winners");
        var list = brandList("Branding");
        System.out.println(list);
        ArrayList<StackPane> panes = new ArrayList<>();
        panes.add(leader1);
        panes.add(leader2);
        for (var m : treeMap.keySet()) {
            panes.add(new ImageSliderShow(treeMap.get(m).name, treeMap.get(m).garde, treeMap.get(m).img_path).getSliderPane());
        }
        for (var a : list) {
            panes.add(new ImageSliderShow(a).getSliderPane());
        }
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        LayoutAnimator layoutAnimator = new LayoutAnimator(root, panes.toArray(new StackPane[0]));
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

    public TreeMap<Integer, WinnerInfo> listOfWinners(String path) {
        TreeMap<Integer, WinnerInfo> brandList = new TreeMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get("G:/My Drive/" + path))) {
            paths.filter(Files::isRegularFile).forEach(p -> {
                String filename = p.getFileName().toString();
                int id = Integer.parseInt(filename.substring(0, filename.indexOf('-')));
                String _class = filename.substring(filename.indexOf('-') + 1, filename.lastIndexOf('-'));
                String name = filename.substring(filename.lastIndexOf('-') +1,filename.lastIndexOf('.'));
                brandList.put(id, new WinnerInfo(name, _class, p.toAbsolutePath().toString()));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    public ArrayList<String> brandList(String path) {
        ArrayList<String> brandList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("G:/My Drive/" + path))) {
            paths.filter(Files::isRegularFile).forEach(p -> {
                brandList.add(p.toAbsolutePath().toString());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    public record WinnerInfo(String name, String garde, String img_path) {

    }

}
