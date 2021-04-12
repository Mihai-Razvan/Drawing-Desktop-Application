package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EraserToolOptions {

    ImageView eraserImageView;
    Group group;


    EraserToolOptions()
    {
        instantiateEraserImageView();

        group = new Group();
        group.setLayoutY(25);
        setVisible(false);
        group.getChildren().addAll(eraserImageView);
    }


    private void instantiateEraserImageView()
    {
        Image image = new Image("icon_eraser.png");
        eraserImageView = new ImageView(image);
        eraserImageView.setFitHeight(20);
        eraserImageView.setFitWidth(20);
        eraserImageView.setLayoutX(15);
        eraserImageView.setLayoutY(16);
    }

    public Group getGroup()
    {
        return group;
    }

    public void setVisible(boolean b)
    {
        group.setVisible(b);
    }
}
