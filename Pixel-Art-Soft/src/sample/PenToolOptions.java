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


public class PenToolOptions {
                                            //aia din toppane cu detalii si optiuni pt pen
    ImageView penImageView;
    Group group;
    Label opacityLabel;
    TextField opacityTF;

    PenToolOptions()
    {
        instantiatePenImageView();
        instantiateOpacity();

        group = new Group();
        setVisible(false);
        group.setLayoutY(25);
        group.getChildren().addAll(penImageView, opacityTF, opacityLabel);
    }

    private void instantiateOpacity()
    {
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

                if(!opacityTF.getText().isBlank() && Integer.parseInt(opacityTF.getText()) > 100)     //mai mic ca 0 nu poate fi ca nu poate fi pus - inainte de nr pt ca aia de mai sus sa poata numa nr
                    opacityTF.setText("100");

                GUI.getLeftPane().getColorPickerClass().setOpacity(getOpacity());
            }
        });
    }

    private void instantiatePenImageView()
    {
        Image image = new Image("icon_pen.png");
        penImageView = new ImageView(image);
        penImageView.setFitHeight(15);
        penImageView.setFitWidth(15);
        penImageView.setLayoutX(15);
        penImageView.setLayoutY(16);
    }

    public Group getGroup()
    {
        return group;
    }

    public void setVisible(boolean b)
    {
        group.setVisible(b);
    }

    public int getOpacity()
    {
        if(opacityTF.getText().isBlank())
            return 0;
        else
            return Integer.parseInt(opacityTF.getText());
    }
}
