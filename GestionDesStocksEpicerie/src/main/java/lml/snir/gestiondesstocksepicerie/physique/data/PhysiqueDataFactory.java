package lml.snir.gestiondesstocksepicerie.physique.data;

import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;

/**
 *
 * @author joris
 */
public class PhysiqueDataFactory {

    private PhysiqueDataFactory() {

    }

    private static ProduitDataService produitDataSrv = null;

    public static ProduitDataService getProduitDataService() throws Exception {
        if (produitDataSrv == null) {
            produitDataSrv = new ProduitDataServiceJDBCImpl();
        }
        return produitDataSrv;

    }

    private static MagazinDataService magazinDataSrv = null;

    public static MagazinDataService getMagazinDataService() throws Exception {
        if (magazinDataSrv == null) {
            magazinDataSrv = new MagazinDataServiceJDBCImpl();
        }
        return magazinDataSrv;
    }

    private static StockDataService stockDataSrv = null;

    public static StockDataService getStockDataService() throws Exception {
        if (stockDataSrv == null) {
            stockDataSrv = new StockDataServiceJDBCImpl();
        }
        return stockDataSrv;
    }

}
