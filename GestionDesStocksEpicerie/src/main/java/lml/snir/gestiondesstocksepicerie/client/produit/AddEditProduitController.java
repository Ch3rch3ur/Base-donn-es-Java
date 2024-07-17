package lml.snir.gestiondesstocksepicerie.client.produit;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lml.snir.javafx.AddEditDialog;
import lml.snir.javafx.AddEditDlgControllable;
import lml.snir.javafx.LMLModel;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;

/**
 *
 * @author joris
 */
public class AddEditProduitController extends AddEditDialog<Produit> implements AddEditDlgControllable {

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML
    private TextField textFieldNom;


    @FXML
    private void buttonOkClick(Event event) throws Exception {
        if (isInputValid()) {

            Produit p;
            String nom = this.textFieldNom.getText();

        }
    }

    @FXML
    private void buttonCancelClick(Event event) throws Exception {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (this.textFieldNom.getText() == null || this.textFieldNom.getText().length() == 0) {
            errorMessage += "nom invallide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public boolean isOkClicked() {
        return this.okClicked;
    }

    @Override
    public void setModelData(LMLModel model) {
        ProduitModel pm = (ProduitModel) model;
        super.data = pm.getAsObject();
    }

    @Override
    public LMLModel getModelData() {
        ProduitModel model = new ProduitModel();
        model.setObjectModel(super.data);
        return model;
    }

}
