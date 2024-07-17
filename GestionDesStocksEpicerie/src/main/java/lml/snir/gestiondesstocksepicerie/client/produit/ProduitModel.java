package lml.snir.gestiondesstocksepicerie.client.produit;

import java.util.logging.Level;
import java.util.logging.Logger;
import lml.snir.javafx.LMLModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;

/**
 *
 * @author fanou
 */
public class ProduitModel extends LMLModel<Produit> {

    private long id;
    private String nom;

    @Override
    public void setObjectModel(Produit t) {
        this.id = t.getId();
        this.nom = t.getNom();
    }

    @Override
    public Produit getAsObject() {
        try {
            return MetierFactory.getProduitService().getById(this.getId());
        } catch (Exception ex) {
            Logger.getLogger(ProduitModel.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

}
