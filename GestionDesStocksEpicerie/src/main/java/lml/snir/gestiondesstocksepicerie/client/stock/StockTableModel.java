package lml.snir.gestiondesstocksepicerie.client.stock;

import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import lml.snir.javafx.AbstractJFXTableModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;

/**
 *
 * @author fanou
 */
public class StockTableModel extends AbstractJFXTableModel<StockModel> {

    public StockTableModel(TableView table) throws Exception {
        super(table, StockModel.class);
        super.setService(MetierFactory.getStockService());
    }

    void findByNom(Produit p) throws Exception {
        List data = MetierFactory.getStockService().getByProduit(p);
        if (data.isEmpty()) {
            Alert dlgAlert = new Alert(Alert.AlertType.INFORMATION);
            dlgAlert.setTitle("Information");
            dlgAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            dlgAlert.setContentText("aucun stock pour " + p.toString());
            dlgAlert.showAndWait();
        }
        super.update(data);
    }

}
