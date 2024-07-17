package lml.snir.gestiondesstocksepicerie.metier.transactionnel;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.persistence.CrudService;


/**
 * Défini les services à rendre, elle ne dit pas comment on fait (pas de code !!)
 * @author joris
 */
public interface StockService extends CrudService<Stock> {
    public List<Stock> getByProduit(Produit produit) throws Exception;
    public List<Stock> getByMagazin(Magazin magazin) throws Exception;

    public Object getByCategorie(Categorie categorie);
}

