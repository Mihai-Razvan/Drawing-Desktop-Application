package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

    private static double Screen_Resolution_Height;
    private static double Screen_Resolution_Width;
    private static BorderPane borderPane;
    private static Scene scene;
    private static CenterPaneClass centerPaneClass;
    private static TopPaneClass topPaneClass;
    private static RightPaneClass rightPaneClass;
    private static BottomPaneClass bottomPaneClass;
    private static LeftPaneClass leftPaneClass;


    @Override
    public void start(Stage stage) throws Exception{

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
        centerPaneClass = new CenterPaneClass();
        topPaneClass = new TopPaneClass();
        rightPaneClass = new RightPaneClass();
        bottomPaneClass = new BottomPaneClass();
        leftPaneClass = new LeftPaneClass();

        borderPane = new BorderPane(centerPaneClass.getPane(), topPaneClass.getPane(), rightPaneClass.getPane(), bottomPaneClass.getPane(), leftPaneClass.getPane());
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

    public static CenterPaneClass getCenterPaneClass() {
        return centerPaneClass;
    }

    public static TopPaneClass getTopPaneClass() {
        return topPaneClass;
    }

    public static RightPaneClass getRightPaneClass() {
        return rightPaneClass;
    }

    public static BottomPaneClass getBottomPaneClass() {
        return bottomPaneClass;
    }

    public static LeftPaneClass getLeftPaneClass() {
        return leftPaneClass;
    }

}
