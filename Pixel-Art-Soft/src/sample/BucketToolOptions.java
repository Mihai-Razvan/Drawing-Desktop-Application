package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BucketToolOptions {

    ImageView imageView;
    Group group;
    Label opacityLabel;
    TextField opacityTF;


    BucketToolOptions()
    {
        instantiateBucketImageView();
        instantiateOpacity();

        group = new Group();
        group.setLayoutY(25);
        setVisible(false);
        group.getChildren().addAll(imageView, opacityTF, opacityLabel);
    }


    private void instantiateBucketImageView()
    {
        Image image = new Image("icon_bucket.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setLayoutX(15);
        imageView.setLayoutY(16);
    }

    private void instantiateOpacity() {
        opacityLabel = new Label("Opacity");
        opacityLabel.setTextFill(Color.WHITE);
        opacityLabel.setFont(Font.font(8));
        opacityLabel.setLayoutX(55);
        opacityLabel.setLayoutY(4);

        opacityTF = new TextField("100");
        opacityTF.setPrefSize(40, 5);
        opacityTF.setLayoutX(50);
        opacityTF.setLayoutY(18);
        opacityTF.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
        opacityTF.setStyle("-fx-text-inner-color: white");

        opacityTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*"))
                    opacityTF.setText(newValue.replaceAll("[^\\d]", ""));

                if (!opacityTF.getText().isBlank() && Integer.parseInt(opacityTF.getText()) > 100)     //mai mic ca 0 nu poate fi ca nu poate fi pus - inainte de nr pt ca aia de mai sus sa poata numa nr
                    opacityTF.setText("100");

                GUI.getLeftPane().getColorPickerClass().setOpacity(getOpacity());
            }
        });
    }


    public void setVisible(boolean b)
    {
        group.setVisible(b);
    }


    public Group getGroup()
    {
        return group;
    }


    public int getOpacity()
    {
        if(opacityTF.getText().isBlank())
            return 0;
        else
            return Integer.parseInt(opacityTF.getText());
    }
}
