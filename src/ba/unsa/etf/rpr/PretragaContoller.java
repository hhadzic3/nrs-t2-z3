package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PretragaContoller implements Initializable {
    public TextField fldTrazi;
    public ListView lista;
    Thread thread;
    private ObservableList<String> apsulutePaths;
    private String slika;


    public void trazi(ActionEvent actionEvent) {
        apsulutePaths.clear();
        lista.setItems((ObservableList) apsulutePaths);


        new Thread( () -> {
            RekruzivnaPretragaThread(fldTrazi.getText(), new File(System.getProperty("user.home")));
        }).start();
    }

    private void RekruzivnaPretragaThread(String uzorak , File file) {

        File[] files = file.listFiles();
        if (file == null) return;

        for (File f : files) {

            if (f.isDirectory())
                RekruzivnaPretragaThread(uzorak, f);

            else if (f.getAbsolutePath().toLowerCase().contains(uzorak.toLowerCase()))
                Platform.runLater(() -> apsulutePaths.add(f.getAbsolutePath()));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //thread = new Thread();
        apsulutePaths = FXCollections.observableArrayList();
        lista.setItems(apsulutePaths);
    }

    public String getSlika() { return slika; }

    public void ClickOk(ActionEvent actionEvent) {
        //thread.interrupt();
        if (!lista.getSelectionModel().isEmpty())
            slika = (String) lista.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) lista.getScene().getWindow();
        stage.close();
    }
}
