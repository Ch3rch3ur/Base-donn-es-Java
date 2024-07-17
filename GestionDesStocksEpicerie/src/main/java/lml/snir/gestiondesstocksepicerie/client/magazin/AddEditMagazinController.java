package lml.snir.gestiondesstocksepicerie.client.magazin;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lml.snir.gestiondesstocksepicerie.client.produit.ProduitModel;
import lml.snir.javafx.AddEditDialog;
import lml.snir.javafx.AddEditDlgControllable;
import lml.snir.javafx.LMLModel;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;

/**
 *
 * @author joris
 */
public class AddEditMagazinController extends AddEditDialog<Magazin> implements AddEditDlgControllable {

    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private TextField textFieldMdp;
    @FXML
    private CheckBox isAdmin;
    
    private long id;

    private boolean okClicked = false;
    private Stage dialogStage;

    @FXML
    private void buttonOkClick(Event event) throws Exception {
        if (isInputValid()) {

            Magazin m;
            String nom = this.textFieldNom.getText();
            String login = this.textFieldLogin.getText();
            String mdp = this.textFieldMdp.getText();
            if (this.isAdmin.isSelected()) {
                Magazin a = new Magazin(nom, mdp, login);
                m = a;
            } else {
                m = new Magazin(nom, mdp, login);
            }

            super.data = m;
            if (this.id != -1) {
                super.data.setId(this.id);
            }
            okClicked = true;
            dialogStage.close();

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
        MagazinModel pm = (MagazinModel) model;
        super.data = pm.getAsObject();
        if (super.data instanceof Magazin) {
            Magazin b = (Magazin) super.data;
            this.textFieldLogin.setText(b.getLogin());
            this.textFieldMdp.setText(b.getMdp());
        } 
        
        this.textFieldNom.setText(super.data.getNom());
        this.id = pm.getId();
    }

    @Override
    public LMLModel getModelData() {
        MagazinModel model = new MagazinModel();
        model.setObjectModel(super.data);
        return model;
    }

}