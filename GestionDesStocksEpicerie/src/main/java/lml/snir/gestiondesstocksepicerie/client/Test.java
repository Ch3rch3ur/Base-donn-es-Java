package lml.snir.gestiondesstocksepicerie.client;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.ProduitService;
import lml.snir.gestiondesstocksepicerie.physique.data.PhysiqueDataFactory;
import lml.snir.gestiondesstocksepicerie.physique.data.ProduitDataService;


/**
 *
 * @author joris
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ProduitService produitSrv = MetierFactory.getProduitService();
        Produit produit;
        
        
        produit = new Produit();
        produit.setCategorie(Categorie.BOUCHERIE);
        produit.setNom("saucisse");
        produit = produitSrv.add(produit);
        
        produit = new Produit();
        produit.setCategorie(Categorie.BOUCHERIE);
        produit.setNom("steack");
        produit = produitSrv.add(produit);
        
        produit = new Produit();
        produit.setCategorie(Categorie.BRICOLAGE);
        produit.setNom("perceuse");
        produit = produitSrv.add(produit);
        
        produit = new Produit();
        produit.setCategorie(Categorie.ALIMENTAIRE);
        produit.setNom("fromage");
        produit = produitSrv.add(produit);
        
        Magazin magazin = new Magazin("market", "secret", "login");
        magazin = MetierFactory.getMagazinService().add(magazin);
        
        Stock stock = new Stock();
        stock.setMagazin(magazin);
        stock.setProduit(produit);
        MetierFactory.getStockService().add(stock);
        MetierFactory.getStockService().add(stock);
        
    }
}
