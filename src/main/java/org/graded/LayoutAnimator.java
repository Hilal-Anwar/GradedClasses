package org.graded;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LayoutAnimator {
    private final Pane[] nodes;
    private int imgIndex = 0;
    private final Pane pane;

    public LayoutAnimator(Pane pane, Pane... nodes) {
        this.nodes = nodes;
        this.pane = pane;
    }


    public void animate() {
        Timeline animation = getTimeline();
        pane.getChildren().add(nodes[imgIndex]);
        animation.play();
    }

    private Timeline getTimeline() {
        EventHandler<ActionEvent> eventHandler = e -> {
            if (imgIndex < nodes.length - 1) {
                pane.getChildren().remove(nodes[imgIndex]);
                imgIndex++;
                pane.getChildren().add(nodes[imgIndex]);
                FadeTransition ft = new FadeTransition(Duration.seconds(2), nodes[imgIndex]);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            } else if (imgIndex == nodes.length - 1) {
                imgIndex = 0;
                pane.getChildren().remove(nodes[nodes.length - 1]);
                pane.getChildren().add(nodes[imgIndex]);
                FadeTransition ft = new FadeTransition(Duration.seconds(2), nodes[imgIndex]);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            }
            System.out.println("This is a " + imgIndex + " iteration");
        };
        int k = 15;
        KeyFrame[] keyFrames = new KeyFrame[nodes.length];
        keyFrames[0] = new KeyFrame(Duration.seconds(k), eventHandler);
        keyFrames[1] = new KeyFrame(Duration.seconds(2 * k), eventHandler);
        k = k * 2;
        for (int i = 2; i < nodes.length; i++) {
            keyFrames[i] = new KeyFrame(Duration.seconds(k + 7), eventHandler);
            k = k + 7;
        }
        System.out.println(nodes.length);
        Timeline animation = new Timeline(keyFrames);

        animation.setCycleCount(Timeline.INDEFINITE);
        return animation;
    }
}