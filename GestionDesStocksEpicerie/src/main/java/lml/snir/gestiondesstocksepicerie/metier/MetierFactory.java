package lml.snir.gestiondesstocksepicerie.metier;

import lml.snir.gestiondesstocksepicerie.metier.transactionnel.MagazinService;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.MagazinServiceImpl;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.ProduitService;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.ProduitServiceImpl;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.StockService;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.StockServiceImpl;

public class MetierFactory {  
   private MetierFactory(){}
   
   private static StockService stockSrv = null;
   public synchronized static StockService getStockService() throws Exception {
       if (stockSrv == null) {
           stockSrv = new StockServiceImpl();
       }
       return stockSrv;
   }
   
   private static MagazinService magazinSrv = null;
   public synchronized static MagazinService getMagazinService() throws Exception {
       if (magazinSrv == null) {
           magazinSrv = new MagazinServiceImpl();
       }
       return magazinSrv;
   }
   
   private static ProduitService produitSrv = null;
   public synchronized static ProduitService getProduitService() throws Exception {
       if (produitSrv == null) {
           produitSrv = new ProduitServiceImpl();
       }
       return produitSrv;
   }
   
}
