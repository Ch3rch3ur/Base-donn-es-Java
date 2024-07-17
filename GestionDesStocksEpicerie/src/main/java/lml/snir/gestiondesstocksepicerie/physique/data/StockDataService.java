package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.persistence.CrudService;

/**
 *
 * @author joris
 */
public interface StockDataService extends CrudService<Stock>{

    public int getCountByMagazin(Magazin magazin) throws Exception;

    public List<Stock> getByProduit(Produit produit);

    public Object getByCategorie(Categorie categorie);

    public List<Stock> getByMagazin(Magazin magazin);

    public Stock getByMagazinEtProduit(Magazin magazin, Produit produit) throws Exception;


}
