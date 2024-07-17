package lml.snir.gestiondesstocksepicerie.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import lml.snir.gestiondesstocksepicerie.client.produit.ProduitControler;

import lml.snir.gestiondesstocksepicerie.client.stock.StockControler;
import lml.snir.gestiondesstocksepicerie.client.magazin.MagazinControler;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;

/**
 *
 * @author joris
 */
public class MainControler implements Initializable {

    private static MainControler instance;
    private Produit produitSelected;
    private Categorie categorieSelected;
    private Magazin magazinSelected;

    private ProduitControler produitControler;
    
    private MagazinControler magazinControler;

    @FXML
    private TableView<Categorie> tableCategorie;

    @FXML
    private Pagination paginationCategorie;

    @FXML
    private TableView tableProduit;

    @FXML
    private Pagination paginationProduit;

    @FXML
    private TableView<Stock> tableStock;
    @FXML
    private Pagination paginationStock;

    @FXML
    private Label labelProduitSeleted;
    
    @FXML    
    private Label labelMagazinSeleted;
    

    @FXML
    private Label labelCategorieSeleted;
    private InvalidationListener CategorieControler;
    private StockControler stockControler;
    private Stock stockSelected;
    private TableView tableMagazin;
    private Pagination paginationMagzin;
    private InvalidationListener magzinControler;

    private void initProduit() throws Exception {
        this.produitControler = new ProduitControler(tableProduit);
        this.produitControler.setPagination(this.paginationProduit);
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener(this.produitControler);
        this.produitControler.init();
    }

    private void initStock() throws Exception {
        this.stockControler = new StockControler(tableStock);
        this.stockControler.setPagination(this.paginationStock);
        this.tableStock.getSelectionModel().selectedItemProperty().addListener(this.stockControler);
        this.stockControler.init();
    }
    
    private void initMagazin() throws Exception {
        this.magazinControler = new MagazinControler(tableMagazin);
        this.magazinControler.setPagination(this.paginationMagzin);
        this.tableMagazin.getSelectionModel().selectedItemProperty().addListener(this.magzinControler);
        this.magazinControler.init();
    }

    private void showErrorMessage(String err) {
        System.err.println(err);
        Alert dlgAlert = new Alert(Alert.AlertType.ERROR);
        dlgAlert.setTitle("Erreur");
        dlgAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dlgAlert.setContentText(err);
        dlgAlert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainControler.instance = this;
        try {
            this.initProduit();

        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuAddProduitClick() {
        try {
            this.produitControler.add();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuAddCategorieClick() {
        
    }

    @FXML
    public void onMenuAddStockClick() {
        try {
            this.stockControler.add();
            this.produitControler.refresh();
            
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
        
    }
    
    @FXML
    public void onMenuAddMagazinClick() {
        try {
            this.stockControler.add();
            this.produitControler.add();
            this.magazinControler.add();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuEditProduitClick() {
        try {
            this.produitControler.update();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuEditCategorieClick() {
//        try {
//            this.categorieControler.update();
//        } catch (Exception ex) {
//            this.showErrorMessage(ex.getMessage());
//        }
    }

    @FXML
    public void onMenuRemoveCategorieClick() {
//        try {
//            this.categorieControler.remove();
//        } catch (Exception ex) {
//            this.showErrorMessage(ex.getMessage());
//        }
    }

    @FXML
    public void onMenuRemoveStockClick() {
        try {
            this.stockControler.remove();
            this.produitControler.refresh();
//            this.categorieControler.refresh();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuRemoveProduitClick() {
        try {
            this.produitControler.remove();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }
    
    @FXML
    public void onMenuRemoveMagazinClick() {
        try {
            this.magazinControler.remove();
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuFindProduitClick() {
        try {
            this.produitControler.search(null);
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuFindCategorieClick() {
//        try {
//            this.categorieControler.search(null);
//        } catch (Exception ex) {
//            this.showErrorMessage(ex.getMessage());
//        }
    }

    @FXML
    public void onMenuFindStockClick() {
        try {
            this.stockControler.search(null);
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    public void onMenuFindMagazinClick() {
        try {
            this.magazinControler.search(null);
        } catch (Exception ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }
    
    @FXML
    public void onMenuFileQuitClick() {
        System.out.println("quit");
        Platform.exit();
    }

    public Produit getProduitSelected() {
        return produitSelected;
    }

    public void setProduitSelected(Produit produitSelected) {
        if (produitSelected != null) {
            this.labelProduitSeleted.setText(produitSelected.toString());
        } else {
            this.labelProduitSeleted.setText("");
        }
        this.produitSelected = produitSelected;
    }

    public Categorie getCategorieSelected() {
        return categorieSelected;
    }

    public void setCategorieSelected(Categorie livreSelected) {
        if (livreSelected != null) {
            this.labelCategorieSeleted.setText(livreSelected.toString());
        } else {
            this.labelCategorieSeleted.setText("");
        }
        this.categorieSelected = livreSelected;
    }


    public Stock getStockSelected() {
        return stockSelected;
    }

    /**
     * @param empruntSelected the empruntSelected to set
     */
    public void setStockSelected(Stock stockSelected) {
        this.stockSelected = stockSelected;
    }
    
     /**
     * @return the adherentSelected
     */
    public Magazin getMagazinSelected() {
        return magazinSelected;
    }

    /**
     * @param adherentSelected the adherentSelected to set
     */
    public void setMagazinSelected(Magazin magazinSelected) {
        if (magazinSelected != null) {
            this.labelMagazinSeleted.setText(magazinSelected.toString());
        } else {
            this.labelMagazinSeleted.setText("");
        }
        this.magazinSelected = magazinSelected;
    }

    /**
     * @return the instance
     */
    public static MainControler getInstance() {
        return instance;
    }

}
