package br.com.desktop.agendaenderecos.view;

import java.io.File;

import br.com.desktop.agendaenderecos.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 * O controlador para o root layout. O root layout provê um layout básico para a
 * aplicação contendo uma barra de menu e um espaço onde outros elementos JavaFX
 * podem ser colocados.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

	// Referência à aplicação principal
	private Main main;

	/**
	 * É chamado pela aplicação principal para referenciar a si mesma.
	 * 
	 * @param main
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Cria uma agenda vazia.
	 */
	@FXML
	private void handleNew() {
		main.getListaPessoas().clear();
		main.setPessoaFilePath(null);
	}

	/**
	 * Abre o FileChooser para permitir o usuário selecionar uma agenda para
	 * carregar.
	 */
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		// Define um filtro de extensão
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Mostra a janela de salvar arquivo
		File file = fileChooser.showOpenDialog(main.getPrimaryStage());

		if (file != null) {
			main.loadPessoaDataFromFile(file);
		}
	}

	/**
	 * Salva o arquivo para o arquivo de pessoa aberto atualmente. Se não houver
	 * arquivo aberto, a janela "salvar como" é mostrada.
	 */
	@FXML
	private void handleSave() {
		File pessoaFile = main.getPessoaFilePath();
		if (pessoaFile != null) {
			main.savePessoaDataToFile(pessoaFile);
		} else {
			handleSaveAs();
		}
	}

	/**
	 * Abre um FileChooser para permitir o usuário selecionar um arquivo para
	 * salvar.
	 */
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Define o filtro de extensão
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Mostra a janela para salvar arquivo
		File file = fileChooser.showSaveDialog(main.getPrimaryStage());

		if (file != null) {
			// Certifica de que esta é a extensão correta
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			main.savePessoaDataToFile(file);
		}
	}

	/**
	 * Abre uma janela Sobre.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Agenda de Endereços");
		alert.setHeaderText("Sobre");
		alert.setContentText("Autor: Marco Jakob\nWebsite: http://code.makery.ch\nAdapted by: Ramon Santos");
		alert.showAndWait();
	}

	/**
	 * Fecha a aplicação.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}