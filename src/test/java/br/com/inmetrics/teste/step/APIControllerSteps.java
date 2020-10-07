package br.com.inmetrics.teste.step;

import org.junit.Assert;

import br.com.inmetrics.dto.UsuarioDTO;
import br.com.inmetrics.services.UsuarioServiceImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class APIControllerSteps {

	private UsuarioServiceImpl usuarioServices = new UsuarioServiceImpl();
	private UsuarioDTO usuarioDTO;

	private static String idUser = "";

	// -------------- @Dado --------------//

	@Dado("que eu tenha os dados para cadastrar o usuario")
	public void queEuTenhaOsDadosParaCadastrarOUsuario(DataTable dataTable) {
		usuarioDTO = this.usuarioServices.getUserFromDatatable(dataTable);
	}

	@Dado("que eu tenha os dados do usuario cadastrado")
	public void queEuTenhaOsDadosDoUsuarioCadastrado() {
		idUser = UsuarioServiceImpl.getId();
	}

	@Dado("que eu envie requisicao {string} para inmetrics-api")
	public void queEuEnvieRequisicaoParaInmetricsApi(String string) {
		this.usuarioServices.get();
	}

	@Dado("que eu tenha os dados para alterar o usuario")
	public void queEuTenhaOsDadosParaAlterarOUsuario(DataTable dataTable) {
		usuarioDTO = this.usuarioServices.getUserFromDatatable(dataTable);
	}

	@Dado("tenha o {string} do usuario")
	public void tenhaODoUsuario(String string) {
	}

	// -------------- @Quando --------------//

	@Quando("enviar requisicao {string} para inmetrics-api")
	public void enviarRequisicaoParaInmetricsApi(String verbo) {
		if ("GET".equalsIgnoreCase(verbo)) {
			usuarioServices.get();
		} else if ("POST".equalsIgnoreCase(verbo)) {
			usuarioServices.post(usuarioDTO);
		} else if ("PUT".equalsIgnoreCase(verbo)) {
			usuarioServices.put(usuarioDTO);
		}
	}

	@Quando("enviar requisicao {string} com {string} para inmetrics-api")
	public void enviarRequisicaoComParaInmetricsApi(String verbo, String id) {
		if ("GET".equalsIgnoreCase(verbo)) {
			usuarioServices.getById(idUser);
		}
	}

	// -------------- @Então --------------//

	@Então("validar {int} retorno")
	public void validarRetorno(Integer int1) {
		Assert.assertTrue(int1 == this.usuarioServices.getResponse().statusCode());
	}

}
