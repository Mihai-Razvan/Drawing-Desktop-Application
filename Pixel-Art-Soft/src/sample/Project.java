package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Project {

    Canvas canvas;
    Button button;

    Project(String projectName, double canvasWidth, double canvasHeight)
    {
        canvas = new Canvas(canvasWidth, canvasHeight);

        button = new Button(projectName);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);
        button.setPrefWidth(80);
        button.setOnAction(event -> changeProject());
    }

    private void changeProject()               //apesi pe buton se schimba proiectu la care esti canvas imagine etc
    {
        deactivateButtons();

        for(int i = 0; i < 16; i ++)
            for(int j = 0; j < 16; j++)
            {
                deleteOldCanvas(i, j);
                addNewCanvas(i, j);
            }

        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        GUI.getCenterPane().setOpenedProject(this);
        canvas.composeImage();
    }

    public Button getButton()
    {
        return button;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public static void deactivateButtons()       //le schimba culoarea butoanelor proiectelor ca si cand nu ar fi deschis proiectu
    {
        ArrayList<Project> projectArrayList = GUI.getCenterPane().getProjectsArrayList();

        for(int i = 0; i < projectArrayList.size(); i++)
            projectArrayList.get(i).getButton().setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(3), Insets.EMPTY)));
    }

    public void deleteOldCanvas(int i, int j)
    {

        GUI.getCenterPane().getPane().getChildren().removeAll(GUI.getCenterPane().getOpenedProject().getCanvas().getBackgroundRectangle(i, j),
                GUI.getCenterPane().getOpenedProject().getCanvas().getRectangle(i ,j));     //stergem canvasu proiectului vechi
    }

    public void addNewCanvas(int i, int j)
    {
        GUI.getCenterPane().getPane().getChildren().addAll(canvas.getBackgroundRectangle(i ,j), canvas.getRectangle(i, j));   //adaugam canvasu proiectului actual
    }
}
