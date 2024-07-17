package lml.snir.gestiondesstocksepicerie.client.magazin;

import java.util.logging.Level;
import java.util.logging.Logger;
import lml.snir.javafx.LMLModel;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;

/**
 *
 * @author joris
 */
public class MagazinModel extends LMLModel<Magazin> {
    private long id;
    private String nom;

    @Override
    public void setObjectModel(Magazin m) {
        
        this.id = m.getId();
        this.nom = m.getNom();
        
    }

    @Override
    public Magazin getAsObject() {
        try {
            return MetierFactory.getMagazinService().getById(this.getId());
        } catch (Exception ex) {
            Logger.getLogger(MagazinModel.class.getName()).log(Level.SEVERE, null, ex);
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
