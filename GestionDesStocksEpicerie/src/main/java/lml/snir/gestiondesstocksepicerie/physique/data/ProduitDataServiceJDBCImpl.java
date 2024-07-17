/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import java.util.Map;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;

/**
 *
 * @author saturne
 */
public class ProduitDataServiceJDBCImpl extends AbstractCrudServiceJDBC<Produit> implements ProduitDataService {

    public ProduitDataServiceJDBCImpl() throws Exception {
        String query = null;
        try {
            switch (super.getDBType()) {
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "  `nom` varchar(30) NOT NULL,\n"
                            + "  `categorie` varchar(30) NOT NULL\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n" + super.getDBType() + "\n" + ex);
        }
    }

    @Override
    protected Produit createEntity(Map map) throws Exception {
        long id = (long) map.get("id");
        String nom = (String) map.get("nom");
        String categorie = (String) map.get("categorie");

        Produit p = new Produit();

        p.setId(id);
        p.setNom(nom);
        p.setCategorie(Categorie.valueOf(categorie));

        return p;
    }

    @Override
    public Produit add(Produit produit) throws Exception {
        
        String nom = produit.getNom();
        String query;

        query = "INSERT INTO " + super.getEntityName() + " (nom, categorie) VALUES ('"
                    + nom + "','"
                    + produit.getCategorie().name() + "')";

        produit.setId(super.executeAdd(query));
        return produit;
    }

    @Override
    public void remove(Produit produit) throws Exception {
        super.remove(produit.getId());
    }

    @Override
    public void update(Produit produit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> getByNom(String nom) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE nom = '" + nom + "'";
        return super.getResults(query);
    }

}
