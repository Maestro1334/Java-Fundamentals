package nl.inholland.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.inholland.exam.Line;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AboutView extends VBox {
    List<Line> lines;
    String[] linesArray;

    public AboutView() throws IOException {
        File file = new File("lines.dat");
        lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        this.setSpacing(20);

        String st;
        int index = 0;
        while ((st = br.readLine()) != null) {
            lines.add(new Line(index, st));
            index++;
        }

        for (Line line : lines) {
            this.getChildren().add(new Text(line.getText()));
            if (line.getId() % 2 == 0) {
                // color 1
            } else {
                // color 2

                // not enough time!
            }
        }
    }
}
