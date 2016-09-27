package br.com.desktop.agendaenderecos.view;
import br.com.desktop.agendaenderecos.modelo.Pessoa;
import br.com.desktop.agendaenderecos.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog para editar detalhes de uma pessoa.
 * 
 * @author Marco Jakob
 */
public class PessoaEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Pessoa person;
    private boolean okClicked = false;

    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     * 
     * @param person
     */
    public void setPessoa(Pessoa person) {
        this.person = person;

        firstNameField.setText(person.getPrimeiroNome());
        lastNameField.setText(person.getUltimoNome());
        streetField.setText(person.getRua());
        postalCodeField.setText(Integer.toString(person.getCodigoPostal()));
        cityField.setText(person.getCidade());
        birthdayField.setText(DateUtil.format(person.getDataNascimento()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Retorna true se o usuário clicar OK,caso contrário false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Chamado quando o usuário clica OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	
            person.setPrimeiroNome(firstNameField.getText());
            person.setUltimoNome(lastNameField.getText());
            person.setRua(streetField.getText());
            person.setCodigoPostal(Integer.parseInt(postalCodeField.getText()));
            person.setCidade(cityField.getText());
            person.setDataNascimento(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     * 
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Nome inválido!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Sobrenome inválido!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Rua inválida!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "Código Postal inválido!\n"; 
        } else {
            // tenta converter o código postal em um int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Aniversário inválido!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.ERROR);
                      alert.setTitle("Campos Inválidos");
                      alert.setHeaderText("Por favor, corrija os campos inválidos");
                      alert.setContentText(errorMessage);
                alert.showAndWait();

            return false;
        }
    }
}
