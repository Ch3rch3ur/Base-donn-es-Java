package lml.snir.gestiondesstocksepicerie.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lml.snir.javafx.LMLController;

/**
 *
 * @author fanou
 */
public class Main extends Application {

    public static Stage primaryStage;
    public static Scene scene;
    public static LMLController view;

    @Override
    public void start(Stage stage) throws Exception {
        Main.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lml/snir/gestiondesstocksepicerie/client/mainView.fxml"));
        Parent root = loader.load();
        Main.scene = new Scene(root);

        stage.setTitle("GestionDesStocksEpicerie");
        stage.setResizable(false);
        stage.setScene(Main.scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.launch(args);
    }

}
