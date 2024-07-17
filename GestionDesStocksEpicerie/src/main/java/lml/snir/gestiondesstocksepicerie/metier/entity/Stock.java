package lml.snir.gestiondesstocksepicerie.metier.entity;

/**
 *
 * @author joris
 */

public class Stock {
    private long id;
    private Produit produit;
    private Magazin magazin;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the produit
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * @return the magazin
     */
    public Magazin getMagazin() {
        return magazin;
    }

    /**
     * @param magazin the magazin to set
     */
    public void setMagazin(Magazin magazin) {
        this.magazin = magazin;
    }

    
    
}
