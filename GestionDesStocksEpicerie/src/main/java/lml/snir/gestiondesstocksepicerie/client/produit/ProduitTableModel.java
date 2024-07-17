package lml.snir.gestiondesstocksepicerie.client.produit;

import java.util.List;
import javafx.scene.control.TableView;
import lml.snir.javafx.AbstractJFXTableModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;

/**
 *
 * @author fanou
 */
public class ProduitTableModel extends AbstractJFXTableModel<ProduitModel> {

    public ProduitTableModel(TableView table) throws Exception {
        super(table, ProduitModel.class);
        super.setService(MetierFactory.getProduitService());
    }

    void findByNom(String nom) throws Exception {
        List data = MetierFactory.getProduitService().getByNom(nom);
        super.update(data);
    }
    
}
