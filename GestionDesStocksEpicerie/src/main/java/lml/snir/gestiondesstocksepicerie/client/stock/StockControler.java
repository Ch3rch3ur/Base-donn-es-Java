package lml.snir.gestiondesstocksepicerie.client.stock;

import java.util.Date;
import javafx.beans.Observable;
import javafx.scene.control.TableView;
import lml.snir.javafx.LMLController;
import lml.snir.gestiondesstocksepicerie.client.MainControler;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.transactionnel.StockService;

/**
 *
 * @author fanou
 */
public class StockControler extends LMLController {

    private StockService stockSrv;
    private StockModel stockSelected;
    private final TableView<StockModel> table;
    private int index;
    private StockTableModel etm;

    public StockControler(TableView table) {
        this.table = table;
    }

    @Override
    public void init() throws Exception {
        // to do init here
        this.stockSrv = MetierFactory.getStockService();
        this.etm = new StockTableModel(this.table);
        this.etm.setNbPerPage(5);
        this.etm.setPagination(this.pagination);
        this.etm.init();
    }

    @Override
    public void add() throws Exception {
//        Categorie c = MainControler.getInstance().getCategorieSelected();
//        Produit p = MainControler.getInstance().getProduitSelected();
//        if (c == null) {
//            throw new Exception("Il faut sélectionner une catégorie");
//        }
//
//        if (p == null) {
//            throw new Exception("Il faut sélectionner un produit");
//        }
//
//        Stock stock = new Stock(c, p);
//        this.stockSrv.add(stock);
//        this.refresh();
    }

    @Override
    public void update() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() throws Exception {
        Stock s = MainControler.getInstance().getStockSelected();
        if (s == null) {
            throw new Exception(("Aucun stock n'as été selectionné !"));
        }
        
        this.stockSrv.remove(s);
        this.refresh();
        MainControler.getInstance().setStockSelected(null);
    }

    @Override
    public void search(Object criteria) throws Exception {
        Produit p = MainControler.getInstance().getProduitSelected();
        if (p == null) {
            throw new Exception("Aucun produit n'as été selectionné !");
        }
        
        this.etm.findByNom(p);   
        this.table.refresh();
    }

    @Override
    public void invalidated(Observable o) {
        this.stockSelected = table.getSelectionModel().getSelectedItem();
        this.index = table.getSelectionModel().getSelectedIndex();

        if (this.stockSelected != null) {
            MainControler.getInstance().setStockSelected(this.stockSelected.getAsObject());
        }
    }

    
    public void refresh() {
        this.etm.createPage(this.pagination.getCurrentPageIndex());
        this.table.refresh();
    }
}
