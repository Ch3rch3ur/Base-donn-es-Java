package lml.snir.gestiondesstocksepicerie.client.stock;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import lml.snir.javafx.LMLModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;

/**
 *
 * @author fanou
 */
public class StockModel extends LMLModel<Stock> {
    private long id;
    private String produit;
    private String categorie; 

    @Override
    public void setObjectModel(Stock t) {
        this.id = t.getId();
        this.produit = t.getProduit().toString();
////        this.categorie = t.getCategorie().toString();
    }

    @Override
    public Stock getAsObject() {
        try {
            return MetierFactory.getStockService().getById(this.getId());
        } catch (Exception ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the adherent
     */
    public String getProduit() {
        return produit;
    }

    /**
     * @return the livre
     */
    public String getCategorie() {
        return categorie;
    }
    
}
