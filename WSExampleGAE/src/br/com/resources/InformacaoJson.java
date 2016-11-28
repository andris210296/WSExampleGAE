package br.com.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.ws.rs.*;

import java.io.InputStream;
import java.lang.reflect.*;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.images.*;

import com.google.appengine.labs.repackaged.org.json.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import br.com.wsexamplegae.bd.*;
import br.com.wsexamplegae.model.*;
import java.lang.reflect.Type;

@Path("/informacaojson/")
public class InformacaoJson {

	@GET
	@Produces("application/json")
	@Path("/getinformacao")
	public String getInformacao() {

		Gson g = new Gson();
		InformacaoDAO infoDAO = new InformacaoJDBC();
		
		try {
			return g.toJson(infoDAO.listar());

		} catch (Exception e) {
			return e.toString();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/setinformacao")
	public String setInformacao(String content) {
		Gson g = new Gson();

		try {
			String conteudo = content.replaceAll("\\[", "").replaceAll("\\]", "");

			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> map = gson.fromJson(conteudo, type);

			Informacao i = new Informacao();
			i.setStringInfo(map.get("string"));
			i.setIntInfo(Integer.valueOf(map.get("int")));
			i.setDoubleInfo(Double.valueOf(map.get("double")));

			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(String.valueOf(map.get("date")));
			i.setDateInfo(date);

			InformacaoDAO infoDAO = new InformacaoJDBC();

			// Verifica qual ação deve ser tomada, ou cadstra um novo ou altera ou deleta
			// o ideal é criar um método para cada ação do CRUD
			if (map.get("action").matches("new") ){
				infoDAO.inserir(i);
			}			
			else if(map.get("action").matches("update")){
				Key key = KeyFactory.createKey("Informacao", Long.valueOf(map.get("key")));
				i.setKeyInfo(key);
				infoDAO.alterar(i);
				
			}else if(map.get("action").matches("delete")){
				Key key = KeyFactory.createKey("Informacao", Long.valueOf(map.get("key")));
				i.setKeyInfo(key);
				infoDAO.deletar(i);
			}			
			return g.toJson(infoDAO.listar());

		} catch (Exception e) {
			e.printStackTrace();
			return g.toJson("RespostaServer: Erro: " + e.getMessage());

		}

	}

}
