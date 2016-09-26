package br.com.desktop.agendaenderecos.main;

import java.io.IOException;

import br.com.desktop.agendaenderecos.dao.PessoaDAO;
import br.com.desktop.agendaenderecos.modelo.Pessoa;
import br.com.desktop.agendaenderecos.view.PessoaOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    public Main(){
    		listaPessoas = PessoaDAO.getPessoas();
    }
    
    
    public ObservableList<Pessoa> getListaPessoas() {
		return listaPessoas;
	}


	public void setListaPessoas(ObservableList<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}


	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda de Endereços");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra o person overview dentro do root layout.
     */
    public void showPersonOverview() {
    	 try {
    	        // Carrega a person overview.
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(Main.class.getResource("../view/PersonOverview.fxml"));
    	        AnchorPane personOverview = (AnchorPane) loader.load();

    	        // Define a person overview no centro do root layout.
    	        rootLayout.setCenter(personOverview);

    	        // Dá ao controlador acesso à the main app.
    	        PessoaOverviewController controller = loader.getController();
    	        controller.setMain(this);

    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
