package br.com.desktop.agendaenderecos.modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe auxiliar para envolver uma lista de pessoas. Esta é usada para salvar a
 * lista de pessoas em XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "pessoas")
public class PessoaListWrapper {

    private List<Pessoa> pessoas;

    @XmlElement(name = "pessoa")
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}