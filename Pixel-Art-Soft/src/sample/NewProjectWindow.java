package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class NewProjectWindow {
                                 /// fereastra aia de apare cand ffaci un proiect nou cu sa pui numele proiectului si cate patratele sa aiba canvasu

    TextField projectNameTF;
    Label projectNameLabel;
    TextField tileWidthTF;
    Label tileWidthLabel;
    TextField tileHeightTF;
    Label tileHeightLabel;
    Button closeButton;
    Button createButton;
    Pane pane;

    NewProjectWindow()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.18, GUI.getScreen_Resolution_Height() * 0.31);
        pane.setLayoutX(640);
        pane.setLayoutY(200);
        pane.setBackground(new Background(new BackgroundFill(Color.web("888888"), new CornerRadii(2), Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.web("666666"), Color.TRANSPARENT, Color.web("666666"), Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, CornerRadii.EMPTY,
                new BorderWidths(33), Insets.EMPTY)));


        instantiateProjectName();
        instantiateProjectSizes();
        instantiateCloseButton();
        instantiateCreateButton();

        pane.getChildren().addAll(projectNameTF, projectNameLabel, tileWidthTF, tileWidthLabel, tileHeightTF, tileHeightLabel, closeButton, createButton);
    }


    private void instantiateProjectName()
    {
        projectNameTF = new TextField("Untitled");
        projectNameTF.setLayoutX(90);
        projectNameTF.setLayoutY(90);
        projectNameTF.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
        projectNameTF.setStyle("-fx-text-inner-color: white");

        projectNameLabel = new Label("Project Name");
        projectNameLabel.setFont(new Font("Arial", 14));
        projectNameLabel.setTextFill(Color.WHITE);
        projectNameLabel.setLayoutX(120);
        projectNameLabel.setLayoutY(60);
    }

    private void instantiateProjectSizes()
    {
        tileWidthTF = new TextField("8");
        tileWidthTF.setLayoutX(90);
        tileWidthTF.setLayoutY(170);
        tileWidthTF.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
        tileWidthTF.setStyle("-fx-text-inner-color: white");

        tileWidthLabel = new Label("Tile Width");
        tileWidthLabel.setFont(new Font("Arial", 14));
        tileWidthLabel.setTextFill(Color.WHITE);
        tileWidthLabel.setLayoutX(127);
        tileWidthLabel.setLayoutY(140);

        tileHeightTF = new TextField("8");
        tileHeightTF .setLayoutX(90);
        tileHeightTF .setLayoutY(250);
        tileHeightTF.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
        tileHeightTF.setStyle("-fx-text-inner-color: white");

        tileHeightLabel = new Label("Tile Height");
        tileHeightLabel.setFont(new Font("Arial", 14));
        tileHeightLabel.setTextFill(Color.WHITE);
        tileHeightLabel.setLayoutX(126);
        tileHeightLabel.setLayoutY(220);

        tileWidthTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*"))
                    tileWidthTF.setText(newValue.replaceAll("[^\\d]", ""));

                if(!tileWidthTF.getText().isBlank() && Integer.parseInt(tileWidthTF.getText()) > 90)
                    tileWidthTF.setText("90");
                else if(!tileWidthTF.getText().isBlank() && Integer.parseInt(tileWidthTF.getText()) < 1)
                    tileWidthTF.setText("1");
            }
        });

        tileHeightTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*"))
                    tileHeightTF.setText(newValue.replaceAll("[^\\d]", ""));

                if(!tileHeightTF.getText().isBlank() && Integer.parseInt(tileHeightTF.getText()) > 90)
                    tileHeightTF.setText("90");
                else if(!tileHeightTF.getText().isBlank() && Integer.parseInt(tileHeightTF.getText()) < 1)
                    tileHeightTF.setText("1");
            }
        });
    }

    private void instantiateCloseButton()
    {
        closeButton = new Button("X");
        closeButton.setFont(new Font("Arial", 10));
        closeButton.setLayoutX(310);
        closeButton.setLayoutY(7);
        closeButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(2), Insets.EMPTY)));
        closeButton.setBorder(new Border(new BorderStroke(Color.web("4e4e4f"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                projectNameTF.setText("Untitled");
                tileWidthTF.setText("8");
                tileHeightTF.setText("8");
                closeButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
                pane.setVisible(false);
            }
        });

        closeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                closeButton.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
            }
        });

        closeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                closeButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    private void instantiateCreateButton()
    {
        createButton = new Button("Create");
        createButton.setLayoutX(150);
        createButton.setLayoutY(294);

        createButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(2), Insets.EMPTY)));
        createButton.setBorder(new Border(new BorderStroke(Color.web("4e4e4f"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
                pane.setVisible(false);
                GUI.getCenterPane().createNewProject(getProjectName(), getTileWidth(), getTileHeight());
                projectNameTF.setText("Untitled");
                tileWidthTF.setText("8");
                tileHeightTF.setText("8");
            }
        });

        createButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createButton.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
            }
        });

        createButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createButton.setBackground(new Background(new BackgroundFill(Color.web("555555"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    public Pane getPane()
    {
        return pane;
    }

    public String getProjectName()
    {
        return projectNameTF.getText();
    }

    public int getTileWidth()
    {
        return Integer.parseInt(tileWidthTF.getText());
    }

    public int getTileHeight()
    {
        return Integer.parseInt(tileHeightTF.getText());
    }
}
