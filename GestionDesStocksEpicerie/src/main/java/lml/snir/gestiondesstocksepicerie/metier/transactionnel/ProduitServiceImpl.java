package lml.snir.gestiondesstocksepicerie.metier.transactionnel;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.physique.data.PhysiqueDataFactory;
import lml.snir.gestiondesstocksepicerie.physique.data.ProduitDataService;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;

/**
 *
 * @author joris
 */
public class ProduitServiceImpl implements ProduitService {

    private final ProduitDataService produitDataService;

    public ProduitServiceImpl() throws Exception {
        this.produitDataService = PhysiqueDataFactory.getProduitDataService();
    }

    @Override
    public List<Produit> getByNom(String nom) throws Exception {
        return this.produitDataService.getByNom(nom);
    }

    @Override
    public Produit add(Produit produit) throws Exception {
        return this.produitDataService.add(produit);
    }

    @Override
    public void remove(Produit produit) throws Exception {
        List<Stock> stocks = MetierFactory.getStockService().getByProduit(produit);
        if (!stocks.isEmpty()) {
            throw new Exception("Impossible de supprimer il y a " + stocks.size() + " stock qui possèdent déjà ce " + produit.toString());
        }

        this.produitDataService.remove(produit);

    }

    @Override
    public void update(Produit produit) throws Exception {
        this.produitDataService.update(produit);
    }

    @Override
    public Produit getById(Long id) throws Exception {
        return this.produitDataService.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.produitDataService.getCount();
    }

    @Override
    public List<Produit> getAll() throws Exception {
        return this.produitDataService.getAll();
    }

    @Override
    public List<Produit> getAll(int begin, int count) throws Exception {
        return this.produitDataService.getAll(begin, count);
    }

}
