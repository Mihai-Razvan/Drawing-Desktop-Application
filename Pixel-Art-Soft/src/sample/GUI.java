package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

    private static double Screen_Resolution_Height;
    private static double Screen_Resolution_Width;
    private static BorderPane borderPane;
    private static Scene scene;
    private static CenterPane centerPane;
    private static TopPane topPane;
    private static RightPane rightPane;
    private static BottomPane bottomPane;
    private static LeftPane leftPane;
    private static Stage stage;


    @Override
    public void start(Stage stage) throws Exception{

        this.stage = stage;
        Screen_Resolution_Height = Screen.getPrimary().getVisualBounds().getHeight();
        Screen_Resolution_Width = Screen.getPrimary().getVisualBounds().getWidth();

        createScene();
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createScene()
    {
        centerPane = new CenterPane();
        topPane = new TopPane();
        rightPane = new RightPane();
        bottomPane = new BottomPane();
        leftPane = new LeftPane();

     //   centerPane.getActualCanvas().composeImage();

        borderPane = new BorderPane(centerPane.getPane(), topPane.getPane(), rightPane.getPane(), bottomPane.getPane(), leftPane.getPane());
        scene = new Scene(borderPane, Screen_Resolution_Width, Screen_Resolution_Height);
    }


    public static double getScreen_Resolution_Height()
    {
        return Screen_Resolution_Height;
    }


    public static double getScreen_Resolution_Width()
    {
        return Screen_Resolution_Width;
    }

    public static CenterPane getCenterPane() {
        return centerPane;
    }

    public static TopPane getTopPane() {
        return topPane;
    }

    public static RightPane getRightPane() {
        return rightPane;
    }

    public static BottomPane getBottomPane() {
        return bottomPane;
    }

    public static LeftPane getLeftPane() {
        return leftPane;
    }

    public static Stage getStage(){
        return stage;
    }

}
