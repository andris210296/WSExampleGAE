package br.com.wsexamplegae.bd;

import br.com.wsexamplegae.model.Informacao;
import java.sql.SQLException;
import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gson.Gson;
public interface InformacaoDAO {	
	public void inserir(Informacao info)throws SQLException,EntityNotFoundException;	
	public void deletar(Informacao info) throws SQLException,EntityNotFoundException;
	public void alterar(Informacao info)throws SQLException, EntityNotFoundException;
	public List listar()throws SQLException,EntityNotFoundException;	

}
