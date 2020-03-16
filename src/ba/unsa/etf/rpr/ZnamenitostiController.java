package ba.unsa.etf.rpr;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class ZnamenitostiController {
    public TextField fldNazivv;
    public ImageView imgView;
    private Znamenitost znamenitost;
    private String slika;
    private Grad grad;

    public ZnamenitostiController(Grad grad) {
        this.grad = grad;
    }

    public Znamenitost getZnamenitost() { return znamenitost;
    }

    public void clickOK(ActionEvent actionEvent) {

        boolean sveOk = true;

        if (fldNazivv.getText().trim().isEmpty()) {
            fldNazivv.getStyleClass().removeAll("poljeIspravno");
            fldNazivv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fldNazivv.getStyleClass().removeAll("poljeNijeIspravno");
            fldNazivv.getStyleClass().add("poljeIspravno");
        }


        if (!sveOk) return;

        // dodati ako je sve ispravno u bazu
        if (znamenitost == null) znamenitost = new Znamenitost();
        znamenitost.setNaziv(fldNazivv.getText());
        znamenitost.setSlika(slika);
        znamenitost.setGrad(grad);
        Stage stage = (Stage) fldNazivv.getScene().getWindow();
        stage.close();
    }

    public void clickOdabir(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fldNazivv.getText().trim().isEmpty()) {
            fldNazivv.getStyleClass().removeAll("poljeIspravno");
            fldNazivv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fldNazivv.getStyleClass().removeAll("poljeNijeIspravno");
            fldNazivv.getStyleClass().add("poljeIspravno");
        }
        if (!sveOk) return;

        DialogInput();
        // Pretraga();
    }

    private void DialogInput() {

        TextInputDialog dialog = new TextInputDialog("Tran");

        dialog.setTitle("Apsulutni put do slike");
        dialog.setHeaderText("Unesite apsulutni put do slike:");
        dialog.setContentText("Slika:");


        Optional<String> result = dialog.showAndWait();
        String res = result.get();
        slika = res;
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(res);
        alert.setContentText("You didn't select a file!");
        alert.showAndWait();
        */
        imgView.imageProperty().bind(Bindings.createObjectBinding(() -> {
            File file = new File(res);
            if (file.exists()) {
                return new Image(file.toURI().toString());
            } else {
                return null ;
            }
        }));
    }

    private void Pretraga() {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pretraga.fxml"));
            PretragaContoller pretragaController = new PretragaContoller();
            loader.setController(pretragaController);
            root = loader.load();
            stage.setTitle("Traži");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding(event -> {
                slika = pretragaController.getSlika();
                try {
                    imgView.setImage(new Image(new FileInputStream(slika)));
                } catch (FileNotFoundException e) {
                    //..
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}