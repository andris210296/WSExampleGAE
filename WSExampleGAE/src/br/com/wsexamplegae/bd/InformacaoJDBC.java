package br.com.wsexamplegae.bd;

import br.com.wsexamplegae.model.Informacao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.gson.Gson;

public class InformacaoJDBC implements InformacaoDAO {

	DatastoreService datastore;

	public InformacaoJDBC() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	@Override
	public void inserir(Informacao info) throws SQLException, EntityNotFoundException {
		Entity informacao = new Entity("Informacao");
		informacao.setProperty("string", info.getStringInfo());
		informacao.setProperty("int", info.getIntInfo());
		informacao.setProperty("date", info.getDateInfo());
		informacao.setProperty("double", info.getDoubleInfo());
		datastore.put(informacao); // Comando que cadastra no datastore
	}

	@Override
	public List listar() throws SQLException, EntityNotFoundException {
		// Criação da lista das informações cadastradas
		Query gaeQuery = new Query("Informacao");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		return list;
	}

	@Override
	public void deletar(Informacao info) throws SQLException, EntityNotFoundException {
		Entity informacao = datastore.get(info.getKeyInfo());
		datastore.delete(informacao.getKey());
	}

	@Override
	public void alterar(Informacao info) throws SQLException, EntityNotFoundException {
		Entity informacao = datastore.get(info.getKeyInfo());
		informacao.setProperty("string", info.getStringInfo());
		informacao.setProperty("int", info.getIntInfo());
		informacao.setProperty("date", info.getDateInfo());
		informacao.setProperty("double", info.getDoubleInfo());
		datastore.put(informacao);
	}

}
