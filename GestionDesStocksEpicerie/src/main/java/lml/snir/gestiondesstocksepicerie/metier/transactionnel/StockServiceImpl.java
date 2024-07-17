package lml.snir.gestiondesstocksepicerie.metier.transactionnel;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.physique.data.PhysiqueDataFactory;
import lml.snir.gestiondesstocksepicerie.physique.data.StockDataService;

/**
 *
 * @author joris
 */
public class StockServiceImpl implements StockService {

    private final StockDataService stockDataService;

    public StockServiceImpl() throws Exception {
        this.stockDataService = PhysiqueDataFactory.getStockDataService();
    }

    @Override
    public Stock add(Stock stock) throws Exception {
        // faire verif
        // si ce magazin à déjà un stock de ce produit je ne peux ajouter
        Stock s = this.stockDataService.getByMagazinEtProduit(stock.getMagazin(), stock.getProduit());
        if (s != null) {
            throw new Exception("produit déjà dans le stock de ce magazin");
        }

        return this.stockDataService.add(stock);
    }

    @Override
    public void remove(Stock stock) throws Exception {
        this.stockDataService.remove(stock);
    }

    @Override
    public void update(Stock stock) throws Exception {
        this.stockDataService.update(stock);
    }

    @Override
    public Stock getById(Long id) throws Exception {
        this.stockDataService.getById(id);
        return null;
    }

    @Override
    public long getCount() throws Exception {
        return this.stockDataService.getCount();
    }

    @Override
    public List<Stock> getAll() throws Exception {
        return this.stockDataService.getAll();
    }

    @Override
    public List<Stock> getAll(int begin, int count) throws Exception {
        return this.stockDataService.getAll(begin, count);
    }

    @Override
    public List<Stock> getByProduit(Produit produit) throws Exception {
        return this.stockDataService.getByProduit(produit);
    }

    @Override
    public Object getByCategorie(Categorie categorie) {
        return this.stockDataService.getByCategorie(categorie);
    }

    @Override
    public List<Stock> getByMagazin(Magazin magazin) throws Exception {
        return this.stockDataService.getByMagazin(magazin);
    }

}
