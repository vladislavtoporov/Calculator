package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog {

    public void doc() throws Exception{
        DialogPane dialogPane = FXMLLoader.load(getClass().getResource("/view/docDialog.fxml"));
        javafx.scene.control.Dialog dialog = new javafx.scene.control.Dialog();
        dialog.setDialogPane(dialogPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/images/16.png").toString()));
        dialog.showAndWait();
    }

    public void about() throws Exception{
        DialogPane dialogPane = FXMLLoader.load(getClass().getResource("/view/aboutDialog.fxml"));
        javafx.scene.control.Dialog dialog = new javafx.scene.control.Dialog();
        dialog.setDialogPane(dialogPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/images/16.png").toString()));
        dialog.showAndWait();
    }
}
