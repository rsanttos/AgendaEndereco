package br.com.desktop.agendaenderecos.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {

    private final StringProperty primeiroNome;
    private final StringProperty ultimoNome;
    private final StringProperty rua;
    private final IntegerProperty codigoPostal;
    private final StringProperty cidade;
    private final ObjectProperty<LocalDate> dataNascimento;

    /**
     *  Construtor padrão.
     */
    public Pessoa() {
        this(null, null);
    }

    public Pessoa(String primeiroNome, String ultimoNome) {
        this.primeiroNome = new SimpleStringProperty(primeiroNome);
        this.ultimoNome = new SimpleStringProperty(ultimoNome);

        // Alguns dados de exemplo, apenas para testes.
        this.rua = new SimpleStringProperty("rua qualquer");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.cidade = new SimpleStringProperty("melhor cidade");
        this.dataNascimento = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getPrimeiroNome() {
        return primeiroNome.get();
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome.set(primeiroNome);
    }

    public StringProperty primeiroNomeProperty() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome.get();
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome.set(ultimoNome);
    }

    public StringProperty ultimoNomeProperty() {
        return ultimoNome;
    }

    public String getRua() {
        return rua.get();
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public IntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public String getCidade() {
        return cidade.get();
    }

    public void setCidade(String cidade) {
        this.cidade.set(cidade);
    }

    public StringProperty cidadeProperty() {
        return cidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }

    public ObjectProperty<LocalDate> dataNascimentoProperty() {
        return dataNascimento;
    }
}
