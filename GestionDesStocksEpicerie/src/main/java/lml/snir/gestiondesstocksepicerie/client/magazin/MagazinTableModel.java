package lml.snir.gestiondesstocksepicerie.client.magazin;

import java.util.List;
import javafx.scene.control.TableView;
import lml.snir.javafx.AbstractJFXTableModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;

/**
 *
 * @author joris
 */
public class MagazinTableModel extends AbstractJFXTableModel<MagazinModel> {

    public MagazinTableModel(TableView table) throws Exception {
        super(table, MagazinModel.class);
        //super.setService(MetierFactory.getCategorieService());
    }
    void findByNom(String nom) throws Exception {
        List data = MetierFactory.getMagazinService().getByNom(nom);
        super.update(data);
    }
}