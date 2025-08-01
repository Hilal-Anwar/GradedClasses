package org.graded;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static org.graded.Main.*;


public class DurationReader {
    public static void updateDurationInDatabase() {
        try (var fileWriter = new FileWriter(file)) {
            for (var line : preview) {
                if (defaultAnimationDuration.containsKey(line)) {
                    fileWriter.write(line + ":" + defaultAnimationDuration.get(line).getLayoutDuration() + ":" +
                            defaultAnimationDuration.get(line).getFadeTime() + "\n");
                } else {
                    fileWriter.write(line + ":" + (defaultAnimationDuration.lastEntry().getValue().getLayoutDuration()) + ":"
                            + (defaultAnimationDuration.lastEntry().getValue().getFadeTime()) + "\n");
                    defaultAnimationDuration.put(line, new AnimationDuration(defaultAnimationDuration.lastEntry().getValue().getLayoutDuration(),
                            defaultAnimationDuration.lastEntry().getValue().getFadeTime()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void durationReader() {
        try (var fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] split = line.split(":");
                if (preview.contains(split[0])) {
                    defaultAnimationDuration.put(split[0], new AnimationDuration(Double.parseDouble(split[1]), Double.parseDouble(split[2])));
                }
            }
        } catch (FileNotFoundException _) {

        }
    }

}
