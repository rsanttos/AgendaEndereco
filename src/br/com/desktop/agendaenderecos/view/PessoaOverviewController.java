package br.com.desktop.agendaenderecos.view;

import br.com.desktop.agendaenderecos.main.Main;
import br.com.desktop.agendaenderecos.modelo.Pessoa;
import br.com.desktop.agendaenderecos.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PessoaOverviewController {
	@FXML
	private TableView<Pessoa> pessoaTable;

	@FXML
	private TableColumn<Pessoa, String> firstNameColumn;
	@FXML
	private TableColumn<Pessoa, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.
	private Main main;

	/**
	 * O construtor. O construtor é chamado antes do método inicialize().
	 */
	// public PessoaOverviewController() {
	//
	// }
	public PessoaOverviewController() {
		super();
		this.firstNameLabel = new Label();
		this.lastNameLabel = new Label();
		this.streetLabel = new Label();
		this.postalCodeLabel = new Label();
		this.cityLabel = new Label();
		this.birthdayLabel = new Label();
		this.pessoaTable = new TableView<Pessoa>();
		this.firstNameColumn = new TableColumn<Pessoa, String>();
		this.lastNameColumn = new TableColumn<Pessoa, String>();
	}

	private void showDetalhesPessoa(Pessoa pessoa) {
		if (pessoa != null) {
			// Preenche as labels com informações do objeto pessoa.
			firstNameLabel.setText(pessoa.getPrimeiroNome());
			lastNameLabel.setText(pessoa.getUltimoNome());
			streetLabel.setText(pessoa.getRua());
			postalCodeLabel.setText(Integer.toString(pessoa.getCodigoPostal()));
			cityLabel.setText(pessoa.getCidade());
			birthdayLabel.setText(DateUtil.format(pessoa.getDataNascimento()));

		} else {
			// Pessoa é null, remove todo o texto.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	/**
	 * Inicializa a classe controller. Este método é chamado automaticamente
	 * após o arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {
		// Inicializa a tablea de pessoa com duas colunas.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().primeiroNomeProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().ultimoNomeProperty());
		// Limpa os detalhes da pessoa.
		showDetalhesPessoa(null);

		// Detecta mudanças de seleção e mostra os detalhes da pessoa quando
		// houver mudança.
		pessoaTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetalhesPessoa(newValue));
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param main
	 */
	public void setMain(Main main) {
		this.main = main;

		// Adiciona os dados da observable list na tabela
		pessoaTable.setItems(main.getListaPessoas());
	}

	public TableView<Pessoa> getPessoaTable() {
		return pessoaTable;
	}

	public void setPessoaTable(TableView<Pessoa> pessoaTable) {
		this.pessoaTable = pessoaTable;
	}

	public TableColumn<Pessoa, String> getFirstNameColumn() {
		return firstNameColumn;
	}

	public void setFirstNameColumn(TableColumn<Pessoa, String> firstNameColumn) {
		this.firstNameColumn = firstNameColumn;
	}

	public TableColumn<Pessoa, String> getLastNameColumn() {
		return lastNameColumn;
	}

	public void setLastNameColumn(TableColumn<Pessoa, String> lastNameColumn) {
		this.lastNameColumn = lastNameColumn;
	}

	public Label getFirstNameLabel() {
		return firstNameLabel;
	}

	public void setFirstNameLabel(Label firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public Label getLastNameLabel() {
		return lastNameLabel;
	}

	public void setLastNameLabel(Label lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	public Label getStreetLabel() {
		return streetLabel;
	}

	public void setStreetLabel(Label streetLabel) {
		this.streetLabel = streetLabel;
	}

	public Label getPostalCodeLabel() {
		return postalCodeLabel;
	}

	public void setPostalCodeLabel(Label postalCodeLabel) {
		this.postalCodeLabel = postalCodeLabel;
	}

	public Label getCityLabel() {
		return cityLabel;
	}

	public void setCityLabel(Label cityLabel) {
		this.cityLabel = cityLabel;
	}

	public Label getBirthdayLabel() {
		return birthdayLabel;
	}

	public void setBirthdayLabel(Label birthdayLabel) {
		this.birthdayLabel = birthdayLabel;
	}

	public Main getMain() {
		return main;
	}

	/**
	 * Chamado quando o usuário clica no botão delete.
	 */
	@FXML
	private void handleDeletePessoa() {
		int selectedIndex = pessoaTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			pessoaTable.getItems().remove(selectedIndex);
		} else {
			// Nada selecionado.

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma Pessoa Selecionada");
			alert.setContentText("Por favor, selecione uma pessoa na tabela.");

			alert.showAndWait();
		}
	}
	/**
	 * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
	 * detalhes da nova pessoa.
	 */
	@FXML
	private void handleNewPessoa() {
	    Pessoa tempPessoa = new Pessoa();
	    boolean okClicked = main.showPessoaEditDialog(tempPessoa);
	    if (okClicked) {
	        main.getListaPessoas().add(tempPessoa);
	    }
	}

	/**
	 * Chamado quando o usuário clica no botão edit. Abre a janela para editar
	 * detalhes da pessoa selecionada.
	 */
	@FXML
	private void handleEditPessoa() {
	    Pessoa selectedPessoa = pessoaTable.getSelectionModel().getSelectedItem();
	    if (selectedPessoa != null) {
	        boolean okClicked = main.showPessoaEditDialog(selectedPessoa);
	        if (okClicked) {
	            showDetalhesPessoa(selectedPessoa);
	        }

	    } else {
	        // Nada seleciondo.
	        Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Nenhuma seleção");
	            alert.setHeaderText("Nenhuma Pessoa Selecionada");
	            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	            alert.showAndWait();
	    }
	}
}