package sample;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CenterPane {

    Pane pane;
    ArrayList<Project> projectsArrayList;
    Project openedProject;        //proiectu deschis acum
    NewProjectWindow newProjectWindow;

    CenterPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.72);
        pane.setBackground(new Background(new BackgroundFill(Color.web("222222"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        projectsArrayList = new ArrayList<>();
        newProjectWindow = new NewProjectWindow();
        pane.getChildren().add(newProjectWindow.getPane());
    }

    public void createNewProject(String name, int tileWidth, int tileHeight)
    {
        Project project = new Project(name, tileWidth, tileHeight, pane.getPrefWidth(), pane.getPrefHeight());

        if(projectsArrayList.size() == 0)   //asta e primu element din arraylist
        {
            project.getButton().setLayoutX(0);
        }
        else
        {
            project.getButton().setLayoutX(projectsArrayList.get(projectsArrayList.size() - 1).getButton().getLayoutX()
                    + projectsArrayList.get(projectsArrayList.size() - 1).getButton().getPrefWidth() + 2);

            for(int i = 0; i < openedProject.getTileHeight(); i ++)
                for(int j = 0; j < openedProject.getTileWidth(); j++)
                    Project.deleteOldCanvas(i, j);       //functia asta e statica

            Project.deactivateButtons();
        }

        pane.getChildren().add(project.getButton());

        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                pane.getChildren().addAll(project.getOpenedFrame().getCanvas().getBackgroundRectangle(i, j), project.getOpenedFrame().getCanvas().getRectangle(i, j));

        openedProject = project;

        if(projectsArrayList.size() != 0)
            project.getOpenedFrame().getCanvas().composeImage();

        projectsArrayList.add(project);
        newProjectWindow.getPane().toFront();    //daca ai un ptoiect deja creat si creezi altu sa se vada fereastra de proiect nou peste canvasu vechi
    }


    public void setOpenedProject(Project project)
    {
        openedProject = project;
    }

    public Pane getPane()
    {
        return pane;
    }

    public NewProjectWindow getNewProjectWindow()
    {
        return newProjectWindow;
    }

    public Canvas getActualCanvas()     //canvasu de la openedProject
    {
        return openedProject.getOpenedFrame().getCanvas();
    }

    public Project getOpenedProject()
    {
        return openedProject;
    }

    public ArrayList<Project> getProjectsArrayList()
    {
        return projectsArrayList;
    }

}
