package br.com.test.rest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.test.entity.Cidade;
import br.com.test.entity.Estado;
import br.com.test.service.CidadeService;
import br.com.test.service.EstadoService;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Path("/restApi")
public class RestApi {

	@EJB
	private CidadeService cidadeService;

	@EJB
	private EstadoService estadoService;

	// 7
	@GET
	@Path("/save/ibge_id={ibge_id},uf_id={uf_id},nome={nome},capital={capital},lon={lon},lat={lat},"
			+ "no_accents={no_accents},alternative_names={alternative_names},microregion={microregion},mesoregion={mesoregion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String save(@PathParam("ibge_id") String idIbge, @PathParam("uf_id") String estado,
			@PathParam("nome") String nome, @PathParam("capital") String capital, @PathParam("lat") String lat,
			@PathParam("lon") String lon, @PathParam("no_accents") String noAccents,
			@PathParam("alternative_names") String alternative, @PathParam("microregion") String microregion,
			@PathParam("mesoregion") String mesoregiom) {
		Cidade cidade = new Cidade();
		cidade.setIbgeId(new Long(idIbge));

		Estado es = new Estado();
		es.setId(new Long(estado));
		cidade.setEstado(es);
		cidade.setNome(nome);
		cidade.setCapital(noAccents);
		cidade.setLongitude(Float.valueOf(lon));
		cidade.setLatitude(Float.valueOf(lat));
		cidade.setNoAccents(noAccents);
		cidade.setAlternativeNames(alternative);
		cidade.setMicroregion(microregion);
		cidade.setMacroregion(mesoregiom);

		try {
			cidadeService.save(cidade);
		} catch (Exception e) {
			return "Erro ao salvar: " + e.getMessage();
		}
		return "Registro salvo com sucesso: " + cidade.getNome();
	}

	// 8
	@GET
	@Path("/remove/{ibge_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeCidade(@PathParam("ibge_id") String idIbge) {
		try {

			Cidade cidade = new Cidade();
			cidade.setIbgeId(new Long(idIbge));
			cidadeService.delete(cidade);

		} catch (Exception e) {
			return "Erro ao excluir: " + e.getMessage();
		}
		return "Excluido: " + idIbge;
	}

	// 11
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Long count() {
		return cidadeService.count();
	}

	// 5
	@GET
	@Path("/load/{idIbge}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cidade load(@PathParam("idIbge") String idIbge) {
		return cidadeService.load(new Long(idIbge));
	}

	// 4
	@GET
	@Path("/getEstados")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estado> getEstados() {
		return estadoService.loadAll();
	}

	// 6
	@GET
	@Path("/getCidadesByEstado/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> getCidadesByEstado(@PathParam("id") String id) {
		return cidadeService.getCidadesByEstado(new Long(id));
	}

	// 2
	@GET
	@Path("/getCapitais/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> getCapitais(@PathParam("id") String id) {
		return cidadeService.getCapitais();
	}

	// 3
	@GET
	@Path("/getEstadosMaiorMenor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estado> getEstadosMaiorMenor() {
		return estadoService.getEstadosMaiorMenor();
	}

	// 1
	@GET
	@Path("/processCSV")
	@Produces(MediaType.APPLICATION_JSON)
	public String processarCSV() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("cidades.csv")));
			String linha = null;

			while ((linha = reader.readLine()) != null) {
				String[] dadosLinha = linha.split(",");
				Cidade cidade = new Cidade();
				Estado estado = new Estado();

				cidade.setIbgeId(new Long(dadosLinha[0]));
				estado = estadoService.loadByName(dadosLinha[1]);
				if (estado != null) {
					cidade.setEstado(estado);
				} else {
					estado = new Estado();
					estado.setNome(dadosLinha[1]);
					estado.setQuant(1);
					estadoService.save(estado);
				}
				cidade.setNome(dadosLinha[2]);
				cidade.setCapital(dadosLinha[3]);
				cidade.setLongitude(new Float(dadosLinha[4]));
				cidade.setLatitude(new Float(dadosLinha[5]));
				cidade.setNoAccents(dadosLinha[6]);
				cidade.setAlternativeNames(dadosLinha[7]);
				cidade.setMicroregion(dadosLinha[8]);
				cidade.setMacroregion(dadosLinha[8]);

				cidadeService.save(cidade);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			return "Erro ao importar planilha: " + e.getMessage();
		} catch (IOException e) {
			return "Erro ao importar planilha: " + e.getMessage();
		}

		return "Planilha importada com sucesso";
	}

	//9
	@GET
	@Path("/findBy/{propriedade}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> findBy(@PathParam("propriedade") String propriedade,
			@PathParam("valor") String valor) {
		return cidadeService.findBy(propriedade, valor);
	}
	
	

	//10
	@GET
	@Path("/countBy/{propriedade}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Long countBy(@PathParam("propriedade") String propriedade,
			@PathParam("valor") String valor) {
		return cidadeService.countBy(propriedade, valor);
	}
	
	//12
	@GET
	@Path("/getMoreDistance")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> getMoreDistance() {
		return cidadeService.getMoreDistance();
	}
}
