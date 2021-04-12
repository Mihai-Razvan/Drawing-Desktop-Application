package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BucketToolOptions {

    ImageView bucketImageView;
    Group group;


    BucketToolOptions()
    {
        instantiateBucketImageView();

        group = new Group();
        group.setLayoutY(25);
        setVisible(false);
        group.getChildren().addAll(bucketImageView);
    }


    private void instantiateBucketImageView()
    {
        Image image = new Image("icon_bucket.png");
        bucketImageView = new ImageView(image);
        bucketImageView.setFitHeight(20);
        bucketImageView.setFitWidth(20);
        bucketImageView.setLayoutX(15);
        bucketImageView.setLayoutY(16);
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
