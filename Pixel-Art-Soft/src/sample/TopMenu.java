package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.File;
import java.io.IOException;


public class TopMenu {
                                      //bara alba de sus de tot cu file save etc
    MenuBar menuBar;

    FileMenu fileMenu;

    TopMenu()
    {
        menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        menuBar.setPrefWidth(GUI.getScreen_Resolution_Width());

        fileMenu = new FileMenu();

        menuBar.getMenus().add(fileMenu.getMenu());
    }

    public MenuBar getMenuBar()
    {
        return menuBar;
    }

}
