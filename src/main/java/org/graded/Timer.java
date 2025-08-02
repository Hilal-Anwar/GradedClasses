package org.graded;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.graded.Main.defaultAnimationDuration;
import static org.graded.Main.preview;

public class Timer implements Initializable {
    @FXML
    private ListView<HBox> listView;
    ArrayList<HBox> list = new ArrayList<>();

    public Timer() {
    }

    @FXML
    void onApply() {
        DurationReaderData.updateDurationInDatabase();
    }

    @FXML
    void onClose() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < preview.size(); i++) {
            var t = preview.get(i);
          /*  double value = (i == 0) ? defaultAnimationDuration.get(t).getLayoutDuration() :
                    (defaultAnimationDuration.get(preview.get(i)).getLayoutDuration() - defaultAnimationDuration.get(preview.get(i - 1)).getLayoutDuration());*/
            var layout = new FXMLLoader(LeaderboardResourcesLoader.loadURL("fxml/layout_animator_timer.fxml"));

            layout.setControllerFactory(_ -> new LayoutAnimatorTimer(t, defaultAnimationDuration.get(t).layoutDuration, defaultAnimationDuration.get(t).getFadeTime()));
            try {
                list.add(layout.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        listView.setItems(FXCollections.observableList(list));
    }

    @FXML
    private void about() {
        Stage timerStage = new Stage();
        timerStage.setTitle("About");
        try {
            var layout = new FXMLLoader(LeaderboardResourcesLoader.loadURL("fxml/about.fxml"));
            layout.setControllerFactory(_ -> new Timer());
            timerStage.setScene(new Scene(layout.load()));
            timerStage.getIcons().add(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("icons/__logo.png"))));
            timerStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
