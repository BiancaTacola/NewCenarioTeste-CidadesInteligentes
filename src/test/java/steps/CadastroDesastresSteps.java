package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroDesastresService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroDesastresSteps {

    CadastroDesastresService cadastroDesastresService = new CadastroDesastresService();

    @Dado("que eu tenha os seguintes dados do evento de desastre:")
    public void queEuTenhaOsSeguintesDadosDoEventoDeDesastre(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroDesastresService.setFieldsEvent(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de eventos de desastres")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEventosDeDesastres(String endPoint) {
        cadastroDesastresService.createEvent(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroDesastresService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroDesastresService.gson.fromJson(
                cadastroDesastresService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroDesastresService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroDesastresService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
