package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Project {

    Button button;  //nutonu ala de sus cu numele proiectului de unde schimbi intre proiecte
    int tileWidth;
    int tileHeight;

    ArrayList<Frame> frameArrayList;
    Frame openedFrame;     //frameu deschis acum

    Project(String projectName, int tileWidth, int tileHeight, double canvasWidth, double canvasHeight)
    {
        openedFrame = new Frame(tileWidth, tileHeight, canvasWidth, canvasHeight);
        frameArrayList = new ArrayList<Frame>();

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        button = new Button(projectName);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);
        button.setPrefWidth(80);
        button.setOnAction(event -> changeProject());
    }

    public Button getButton()
    {
        return button;
    }

    public Frame getOpenedFrame()
    {
        return openedFrame;
    }

    public int getTileWidth()
    {
        return tileWidth;
    }

    public int getTileHeight()
    {
        return tileHeight;
    }

    private void changeProject()               //apesi pe buton se schimba proiectu la care esti canvas imagine etc
    {
        deactivateButtons();

        for(int i = 0; i < GUI.getCenterPane().getOpenedProject().getTileHeight(); i ++)
            for(int j = 0; j < GUI.getCenterPane().getOpenedProject().getTileWidth(); j++)
                deleteOldCanvas(i, j);

        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                addNewCanvas(i, j);

        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        GUI.getCenterPane().setOpenedProject(this);
        openedFrame.getCanvas().composeImage();
    }

    public static void deactivateButtons()       //le schimba culoarea butoanelor proiectelor ca si cand nu ar fi deschis proiectu
    {
        ArrayList<Project> projectArrayList = GUI.getCenterPane().getProjectsArrayList();

        for(int i = 0; i < projectArrayList.size(); i++)
            projectArrayList.get(i).getButton().setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(3), Insets.EMPTY)));
    }

    public static void deleteOldCanvas(int i, int j)
    {
        GUI.getCenterPane().getPane().getChildren().removeAll(GUI.getCenterPane().getOpenedProject().getOpenedFrame().getCanvas().getBackgroundRectangle(i, j),
                GUI.getCenterPane().getOpenedProject().getOpenedFrame().getCanvas().getRectangle(i ,j));     //stergem canvasu proiectului vechi
    }

    public void addNewCanvas(int i, int j)     //adauga patratelele canvasului  pe rand
    {
        GUI.getCenterPane().getPane().getChildren().addAll(openedFrame.getCanvas().getBackgroundRectangle(i ,j), openedFrame.getCanvas().getRectangle(i, j));   //adaugam canvasu proiectului actual
    }
}
