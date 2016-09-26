package br.com.desktop.agendaenderecos.dao;

import br.com.desktop.agendaenderecos.modelo.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PessoaDAO {
	public static ObservableList<Pessoa> getPessoas(){
		
		ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
		
		pessoas.add(new Pessoa("nome", "ultimonome"));
		pessoas.add(new Pessoa("nome1", "ultimonome1"));
		pessoas.add(new Pessoa("nome2", "ultimonome2"));
		pessoas.add(new Pessoa("nome3", "ultimonome3"));
		pessoas.add(new Pessoa("nome4", "ultimonome4"));
		pessoas.add(new Pessoa("nome5", "ultimonome5"));
		pessoas.add(new Pessoa("nome6", "ultimonome6"));
		pessoas.add(new Pessoa("nome7", "ultimonome7"));
		pessoas.add(new Pessoa("nome8", "ultimonome8"));
		pessoas.add(new Pessoa("nome9", "ultimonome9"));
		pessoas.add(new Pessoa("nome10", "ultimonome10"));
		pessoas.add(new Pessoa("nome11", "ultimonome11"));
		pessoas.add(new Pessoa("nome12", "ultimonome12"));
		pessoas.add(new Pessoa("nome13", "ultimonome13"));
		pessoas.add(new Pessoa("nome14", "ultimonome14"));
		pessoas.add(new Pessoa("nome15", "ultimonome15"));
		pessoas.add(new Pessoa("nome16", "ultimonome16"));
		pessoas.add(new Pessoa("nome17", "ultimonome17"));
		pessoas.add(new Pessoa("nome18", "ultimonome18"));
		pessoas.add(new Pessoa("nome19", "ultimonome19"));
		pessoas.add(new Pessoa("nome20", "ultimonome20"));
		pessoas.add(new Pessoa("nome21", "ultimonome21"));
		pessoas.add(new Pessoa("nome22", "ultimonome22"));
		pessoas.add(new Pessoa("nome23", "ultimonome23"));
		pessoas.add(new Pessoa("nome24", "ultimonome24"));
		pessoas.add(new Pessoa("nome25", "ultimonome25"));
		pessoas.add(new Pessoa("nome26", "ultimonome26"));
		pessoas.add(new Pessoa("nome27", "ultimonome27"));
		pessoas.add(new Pessoa("nome28", "ultimonome28"));
		pessoas.add(new Pessoa("nome29", "ultimonome29"));
		pessoas.add(new Pessoa("nome30", "ultimonome30"));

		return pessoas;
	}
}
