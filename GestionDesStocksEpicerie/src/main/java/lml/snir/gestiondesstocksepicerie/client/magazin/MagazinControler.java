package lml.snir.gestiondesstocksepicerie.client.magazin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import javafx.beans.Observable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import lml.snir.javafx.LMLController;
import lml.snir.gestiondesstocksepicerie.client.Main;
import lml.snir.gestiondesstocksepicerie.client.MainControler;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.MagazinService;

/**
 *
 * @author joris
 */
public class MagazinControler extends LMLController {

    private MagazinService magazinSrv;
    private MagazinModel magazinSelected;
    private MagazinTableModel ltm;
    private final TableView<MagazinModel> table;
    private int index;

    public MagazinControler(TableView table) {
        this.table = table;
    }

    @Override
    public void init() throws Exception {
        // to do init here
        this.ltm = new MagazinTableModel(this.table);
        this.ltm.setPagination(this.pagination);
        this.ltm.init();

        this.magazinSrv = MetierFactory.getMagazinService();
    }

    @Override
    public void add() throws Exception {
        AddEditMagazinController addEditDlg = new AddEditMagazinController();
        addEditDlg.createDialogAddEdit(null, Main.class, "/lml/snir/gestiondesstocksepicerie/client/AddEditMagazinView.fxml", Main.primaryStage);
        Magazin m = addEditDlg.getData();
        if (m != null) {
            this.magazinSrv.add(m);
            this.ltm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
        }
    }

    @Override
    public void update() throws Exception {
        if (this.magazinSelected == null) {
            throw new Exception("On ne peut editer que si on à selectionnée un Magazin !");
        }

        AddEditMagazinController addEditDlg = new AddEditMagazinController();
        addEditDlg.createDialogAddEdit(this.magazinSelected, Main.class, "/lml/snir/gestiondesstocksepicerie/client/AddEditMagazinView.fxml", Main.primaryStage);
        Magazin m = addEditDlg.getData();
        if (m != null) {
            this.magazinSrv.update(m);
            this.ltm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
        }
    }

    @Override
    public void remove() throws Exception {
        if (this.magazinSelected == null) {
            throw new Exception("Aucun magazin n'as été selectionné");
        }

        Alert dlg = new Alert(Alert.AlertType.CONFIRMATION);
        dlg.setTitle("Voulez-vous vraiment supprimer ? ");
        dlg.setContentText(this.magazinSelected.getAsObject().toString());
        if (dlg.showAndWait().get() == ButtonType.OK) {
            this.magazinSrv.remove(this.magazinSelected.getAsObject());
            this.ltm.createPage(this.pagination.getCurrentPageIndex());
            this.table.refresh();
            MainControler.getInstance().setCategorieSelected(null);
        }
    }

    @Override
    public void search(Object criteria) throws Exception {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Recherche");
        dialog.setHeaderText("par mots clés");
        dialog.setContentText("Quel mots :");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            StringTokenizer tokenizer = new StringTokenizer(result.get(), " ");
            List<String> mc = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                mc.add(tokenizer.nextToken());
            }
        }

    }

    @Override
    public void invalidated(Observable o) {
        this.magazinSelected = table.getSelectionModel().getSelectedItem();
        this.index = table.getSelectionModel().getSelectedIndex();

        if (this.magazinSelected != null) {
            MainControler.getInstance().setMagazinSelected(this.magazinSelected.getAsObject());
        }
    }

    public void refresh() {
        this.ltm.createPage(this.pagination.getCurrentPageIndex());
        this.table.refresh();
    }

}
