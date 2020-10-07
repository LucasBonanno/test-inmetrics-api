package br.com.inmetrics.services;

import static io.restassured.RestAssured.given;

import java.nio.file.Paths;

import br.com.inmetrics.dto.UsuarioDTO;
import br.com.inmetrics.utils.ParserUtils;
import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsuarioServiceImpl implements ApiService {

	private static final String JSON_CADASTRO = Paths.get("").toAbsolutePath().toString()
			+ "/src/test/resources/usuario.json";

	private Response response;

	private final String URL = "https://inmetrics:automacao@inm-api-test.herokuapp.com/empregado/%s";
	private final String URL_POST = String.format(URL, "cadastrar");
	private final String URL_GET_ID = String.format(URL, "list/%s");
	private final String URL_GET = String.format(URL, "list_all");
	private final String URL_ALT = String.format(URL, "alterar");

	private static String id = "";

	private RequestSpecBuilder getRequestHeader() {
		RequestSpecBuilder request = new RequestSpecBuilder();
		request.setAccept("*/*");
		request.setContentType("application/json");
		return request;
	}

	private void getResponseFromPost(RequestSpecBuilder request, String url) {
		RequestSpecification resquestSpec = request.build();
		response = given().log().all().spec(resquestSpec).post(url);
		response.prettyPrint();
	}

	public UsuarioDTO getUserFromDatatable(DataTable dataTable) {
		UsuarioDTO usuarioDTO = ParserUtils.jsonToObject(JSON_CADASTRO, UsuarioDTO.class);
		usuarioDTO.setNome(ParserUtils.mapperDataTable(dataTable, 0));
		usuarioDTO.setSexo(ParserUtils.mapperDataTable(dataTable, 1));
		usuarioDTO.setCpf(ParserUtils.mapperDataTable(dataTable, 2));
		usuarioDTO.setDepartamentoId(Integer.parseInt(ParserUtils.mapperDataTable(dataTable, 3)));
		usuarioDTO.setAdmissao(ParserUtils.mapperDataTable(dataTable, 4));
		usuarioDTO.setCargo(ParserUtils.mapperDataTable(dataTable, 5));
		usuarioDTO.setSalario(ParserUtils.mapperDataTable(dataTable, 6));
		usuarioDTO.setComissao(ParserUtils.mapperDataTable(dataTable, 7));
		usuarioDTO.setTipoContratacao(ParserUtils.mapperDataTable(dataTable, 8));
		return usuarioDTO;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void get() {
		RequestSpecBuilder request = getRequestHeader();

		RequestSpecification resquestSpec = request.build();
		response = given().log().all().spec(resquestSpec).get(URL_GET);
		response.prettyPrint();
	}

	@Override
	public void getById(String id) {
		RequestSpecBuilder request = getRequestHeader();

		RequestSpecification resquestSpec = request.build();
		response = given().log().all().spec(resquestSpec).get(String.format(URL_GET_ID, id));
		response.prettyPrint();

	}

	@Override
	public void post(Object obj) {
		RequestSpecBuilder request = getRequestHeader();
		request.setBody(ParserUtils.objectToJson(obj));

		getResponseFromPost(request, URL_POST);
		setId(response.body().jsonPath().getString("empregadoId"));
	}

	@Override
	public void put(Object obj) {

		RequestSpecBuilder request = getRequestHeader();
		request.setBody(ParserUtils.objectToJson(obj));

		getResponseFromPost(request, URL_ALT);
	}

	public static void setId(String id) {
		UsuarioServiceImpl.id = id;
	}

	public static String getId() {
		return id;
	}
}
