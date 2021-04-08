package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileMenu {

    Menu menu;
    MenuItem saveMenuItem;
    MenuItem saveAsMenuItem;
    MenuItem exportMenuItem;

    FileMenu()
    {
        menu = new Menu("File");
        saveMenuItem = new MenuItem("Save");
        saveAsMenuItem = new MenuItem("Save As");
        exportMenuItem = new MenuItem("Export");
        exportMenuItem.setOnAction(event -> exportMenuItemAction());

        menu.getItems().addAll(saveMenuItem, saveAsMenuItem, exportMenuItem);
    }

    public Menu getMenu()
    {
        return menu;
    }

    private void exportMenuItemAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(GUI.getStage());

        if(file != null)
        {
            try{
                ImageIO.write(SwingFXUtils.fromFXImage(GUI.getCenterPane().getCanvas().getImage(), null), "png", file);
            }
            catch (IOException exception) {
                System.out.println("COULDN'T SAVE IMAGE");
            }
        }

    }
}
