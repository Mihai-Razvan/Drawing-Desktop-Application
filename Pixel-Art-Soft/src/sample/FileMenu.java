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
    MenuItem newMenuItem;
    MenuItem exportMenuItem;


    FileMenu()
    {
        menu = new Menu("File");
        newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(event -> newMenuItemAction());

        exportMenuItem = new MenuItem("Export");
        exportMenuItem.setOnAction(event -> exportMenuItemAction());

        menu.getItems().addAll(newMenuItem, exportMenuItem);
    }


    private void exportMenuItemAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(GUI.getStage());

        if(file != null)
        {
            try{
                ImageIO.write(SwingFXUtils.fromFXImage(GUI.getCenterPane().getActualCanvas().getImage(), null), "png", file);
            }
            catch (IOException exception) {
                System.out.println("COULDN'T SAVE IMAGE");
            }
        }

    }

    private void newMenuItemAction()
    {
        GUI.getCenterPane().getNewProjectWindow().getPane().setVisible(true);
       // GUI.getCenterPane().createNewProject("Project");
    }

    public Menu getMenu()
    {
        return menu;
    }
}
