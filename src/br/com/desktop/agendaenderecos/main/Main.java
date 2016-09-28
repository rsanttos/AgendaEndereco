package br.com.desktop.agendaenderecos.main;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.desktop.agendaenderecos.dao.PessoaDAO;
import br.com.desktop.agendaenderecos.modelo.Pessoa;
import br.com.desktop.agendaenderecos.modelo.PessoaListWrapper;
import br.com.desktop.agendaenderecos.view.PessoaEditDialogController;
import br.com.desktop.agendaenderecos.view.PessoaOverviewController;
import br.com.desktop.agendaenderecos.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

	public Main() {
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
		this.primaryStage.getIcons().add(new Image("file:img/address_book.png"));
		this.primaryStage.setTitle("Agenda de Endereços");

		initRootLayout();

		showPessoaOverview();
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

			// Dá ao controller o acesso ao main app.
			RootLayoutController controller = loader.getController();
			controller.setMain(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Tenta carregar o último arquivo de pessoa aberto.
		File file = getPessoaFilePath();
		if (file != null) {
			loadPessoaDataFromFile(file);
		}
	}

	/**
	 * Mostra o pessoa overview dentro do root layout.
	 */
	public void showPessoaOverview() {
		try {
			// Carrega a pessoa overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PersonOverview.fxml"));
			AnchorPane pessoaOverview = (AnchorPane) loader.load();

			// Define a pessoa overview no centro do root layout.
			rootLayout.setCenter(pessoaOverview);

			// Dá ao controlador acesso à the main app.
			PessoaOverviewController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada. Se o
	 * usuário clicar OK, as mudanças são salvasno objeto pessoa fornecido e
	 * retorna true.
	 * 
	 * @param pessoa
	 *            O objeto pessoa a ser editado
	 * @return true Se o usuário clicou OK, caso contrário false.
	 */
	public boolean showPessoaEditDialog(Pessoa pessoa) {
		try {
			// Carrega o arquivo fxml e cria um novo stage para a janela popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Cria o palco dialogStage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar Pessoa");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Define a pessoa no controller.
			PessoaEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPessoa(pessoa);

			// Mostra a janela e espera até o usuário fechar.
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retorna o arquivo de preferências da pessoa, o último arquivo que foi
	 * aberto. As preferências são lidas do registro específico do SO (Sistema
	 * Operacional). Se tais prefêrencias não puderem ser encontradas, ele
	 * retorna null.
	 * 
	 * @return
	 */
	public File getPessoaFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Define o caminho do arquivo do arquivo carregado atual. O caminho é
	 * persistido no registro específico do SO (Sistema Operacional).
	 * 
	 * @param file
	 *            O arquivo ou null para remover o caminho
	 */
	public void setPessoaFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("AgendaEnderecos - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("Agenda de Endereços");
		}
	}

	/**
	 * Carrega os dados da pessoa do arquivo especificado. A pessoa atual será
	 * substituída.
	 * 
	 * @param file
	 */
	public void loadPessoaDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(PessoaListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			PessoaListWrapper wrapper = (PessoaListWrapper) um.unmarshal(file);

			listaPessoas.clear();
			listaPessoas.addAll(wrapper.getPessoas());

			// Save the file path to the registry.
			setPessoaFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Erro").masthead("Não foi possível carregar dados do arquivo:\n" + file.getPath())
					.showException(e);
		}
	}

	/**
	 * Salva os dados da pessoa atual no arquivo especificado.
	 * 
	 * @param file
	 */
	public void savePessoaDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(PessoaListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Envolvendo nossos dados da pessoa.
			PessoaListWrapper wrapper = new PessoaListWrapper();
			wrapper.setPessoas(listaPessoas);

			// Enpacotando e salvando XML no arquivo.
			m.marshal(wrapper, file);

			// Saalva o caminho do arquivo no registro.
			setPessoaFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Erro").masthead("Não foi possível salvar os dados do arquivo:\n" + file.getPath())
					.showException(e);
		}
	}

	/**
	 * Retorna o palco principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
