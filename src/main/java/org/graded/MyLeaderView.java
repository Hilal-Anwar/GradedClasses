package org.graded;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MyLeaderView implements Initializable {
    public Text title;
    @FXML
    VBox root_layout;
    @FXML
    ListView<CustomView> custList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<CustomView> co = new ArrayList<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.execute(() -> {
                StudentDataLoader studentDataLoader = new StudentDataLoader();
                var vr = studentDataLoader.getStudentStream();
                int ind = 1;
                for (var k:vr) {
                    switch (ind) {

                        case 1 ->
                                co.add(new CustomView(1, "b2.png",
                                        Name.make_word_name(k.name()), "Class " + k.grade(),
                                        "" +  (int)k.points(), "#C29B0C20"));
                        case 2 ->
                                co.add(new CustomView(2,
                                        "b3.png", Name.make_word_name(k.name()),
                                        "Class " + k.grade(),
                                        "" +  (int)k.points(), "#98afcf20"));
                        case 3 ->
                                co.add(new CustomView(3,
                                        "b4.png", Name.make_word_name(k.name()),
                                        "Class " + k.grade(),
                                        "" + (int)k.points(), "#99422720"));
                        default -> co.add(new CustomView(ind,
                                Name.make_word_name(k.name().trim()), "Class " + k.grade(),
                                "" + (int) k.points(), "#68926d20"));
                    }
                    ind++;
                    if (ind == 11)
                        break;
                }
                var list = FXCollections.observableList(co);
                custList.setItems(list);
            });
        }

    }

}
