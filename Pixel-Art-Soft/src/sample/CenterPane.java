package sample;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

            pane.getChildren().remove(openedProject.getBackgroundRectangle());
            for(int i = 0; i < openedProject.getTileHeight(); i ++)
                for(int j = 0; j < openedProject.getTileWidth(); j++)
                    Project.deleteOldCanvas(i, j);       //functia asta e statica

            Project.deactivateButtons();
        }

        if(projectsArrayList.size() != 0)       //sterge imageviewurile de la frameurile din opened project
        {
            int framesNumber = openedProject.getFramesNumber();
            ArrayList<Frame> arrayList = openedProject.getFrameArrayList();
            for (int i = 0; i < framesNumber; i++)
                arrayList.get(i).deleteImageView();
        }

            // nu este nevoie sa adaugam aici imageviewurile de la noul proiect pt ca atunci cand creezi un proiect se creeaza si primul frame automat si odata cu el si primul imageview
        pane.getChildren().add(project.getButton());
        openedProject = project;

        pane.getChildren().add(openedProject.getBackgroundRectangle());
        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                Project.addNewCanvas(i, j);

        if(projectsArrayList.size() != 0)
            project.getOpenedFrame().composeImage();

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

    public Canvas getOpenedCanvas()     //canvasu de la openedProject/openedFrame
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
