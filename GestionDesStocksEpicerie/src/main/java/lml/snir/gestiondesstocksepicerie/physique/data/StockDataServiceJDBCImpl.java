package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import java.util.Map;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;

/**
 *
 * @author joris
 * @param <T>
 */
public class StockDataServiceJDBCImpl<T> extends AbstractCrudServiceJDBC<Stock> implements StockDataService {

    public StockDataServiceJDBCImpl() throws Exception {
        String query = null;
        try {
            switch (super.getDBType()) {
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "  `idMagazin` INTEGER NOT NULL,\n"
                            + "  `idProduit` INTEGER NOT NULL\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n" + super.getDBType() + "\n" + ex);
        }
    }

    @Override
    protected Stock createEntity(Map map) throws Exception {
        Stock stock = null;

        long id = (int) map.get("id");
        long idMagazin = (int) map.get("idMagazin");
        long idProduit = (int) map.get("idProduit");

        Produit produit = PhysiqueDataFactory.getProduitDataService().getById(idProduit);
        Magazin magazin = PhysiqueDataFactory.getMagazinDataService().getById(idMagazin);

        stock.setId(id);

        return stock;
    }

    @Override
    public Stock add(Stock stock) throws Exception {
        String query = "INSERT INTO " + super.getEntityName() + " (idProduit) VALUES ('"                    
                + stock.getProduit().getId() + "','";
        stock.setId(super.executeAdd(query));
        return stock;
          
    }

    @Override
    public void remove(Stock stock) throws Exception {
        super.remove(stock.getId());
    }

    @Override
    public void update(Stock stock) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCountByMagazin(Magazin magazin) throws Exception {
        String query = "SELECT COUNT(*) FROM " + super.getEntityName() + " WHERE idAdherent = '" + magazin.getId() + "'";
        return (int) super.getCount(query);
    }

    @Override
    public List<Stock> getByProduit(Produit produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getByCategorie(Categorie categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> getByMagazin(Magazin magazin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stock getByMagazinEtProduit(Magazin magazin, Produit produit) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() 
                     + " WHERE idMagazin = '" + magazin.getId() + "' AND idProduit = '" + magazin.getId() + "'";
        return super.getSingleResult(query);
    }

}
