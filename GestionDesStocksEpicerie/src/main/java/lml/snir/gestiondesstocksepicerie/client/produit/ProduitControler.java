package lml.snir.gestiondesstocksepicerie.client.produit;

import java.util.Optional;
import javafx.beans.Observable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import lml.snir.javafx.LMLController;
import lml.snir.gestiondesstocksepicerie.client.Main;
import lml.snir.gestiondesstocksepicerie.client.MainControler;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.ProduitService;

/**
 *
 * @author fanou
 */
public class ProduitControler extends LMLController {

    private ProduitService produitSrv;
    private ProduitModel produitSelected;
    private final TableView<ProduitModel> table;
    private int index;

    private ProduitTableModel atm;

    public ProduitControler(TableView table) {
        this.table = table;
    }

    @Override
    public void init() throws Exception {
        // to do init here
        this.atm = new ProduitTableModel(this.table);
        this.atm.setNbPerPage(5);
        this.atm.setPagination(this.pagination);
        this.atm.init();

        this.produitSrv = MetierFactory.getProduitService();
    }

    @Override
    public void add() throws Exception {
        AddEditProduitController addEditDlg = new AddEditProduitController();
        addEditDlg.createDialogAddEdit(null, Main.class, "/lml/snir/gestiondesstocksepicerie/client/AddEditProduitView.fxml", Main.primaryStage);
        Produit p = addEditDlg.getData();
        if (p != null) {
            this.produitSrv.add(p);            
            this.atm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
        }
    }

    @Override
    public void update() throws Exception {
        if (this.produitSelected == null) {
            throw new Exception("On ne peut éditer que si on à selectionnée un produit !");
        }
        
        AddEditProduitController addEditDlg = new AddEditProduitController();
        addEditDlg.createDialogAddEdit(this.produitSelected, Main.class, "/lml/snir/gestiondesstocksepicerie/client/AddEditProduitView.fxml", Main.primaryStage);
        Produit p = addEditDlg.getData();
        if (p != null) {
            this.produitSrv.update(p);            
            this.atm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
        }
    }

    @Override
    public void remove() throws Exception {
        if (this.produitSelected == null) {
            throw new Exception("Aucun produit n'as été selectionné");
        }

        Alert dlg = new Alert(Alert.AlertType.CONFIRMATION);
        dlg.setTitle("Voulez-vous vraiment supprimer ? ");
        dlg.setContentText(this.produitSelected.getNom() + " " + this.produitSelected.getNom());
        if (dlg.showAndWait().get() == ButtonType.OK) {
            this.produitSrv.remove(this.produitSelected.getAsObject());
            this.atm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
            MainControler.getInstance().setProduitSelected(null);
        }
    }

    @Override
    public void search(Object criteria) throws Exception {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Recherche");
        dialog.setHeaderText("par nom");
        dialog.setContentText("Quel nom :");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.atm.findByNom(result.get());            
        }
    }

    @Override
    public void invalidated(Observable o) {
        this.produitSelected = table.getSelectionModel().getSelectedItem();
        this.index = table.getSelectionModel().getSelectedIndex();

        if (this.produitSelected != null) {
            MainControler.getInstance().setProduitSelected(this.produitSelected.getAsObject());
        }
    }
    
    public void refresh() {
        this.atm.createPage(this.pagination.getCurrentPageIndex());
        this.table.refresh();
    }

}
