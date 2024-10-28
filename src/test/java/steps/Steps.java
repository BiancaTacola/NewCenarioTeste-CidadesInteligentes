package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroDesastresService;
import services.CadastroQualidadeAguaService;
import services.CadastroQualidadeArService;

import java.util.List;
import java.util.Map;

public class Steps {

    private final CadastroDesastresService cadastroDesastresService = new CadastroDesastresService();
    private final CadastroQualidadeAguaService cadastroQualidadeAguaService = new CadastroQualidadeAguaService();
    private final CadastroQualidadeArService cadastroQualidadeArService = new CadastroQualidadeArService();

    // Passos para cadastro de eventos dos desastres naturais
    @Dado("que eu tenha os seguintes dados do evento de desastre:")
    public void queEuTenhaOsSeguintesDadosDoEventoDeDesastre(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            cadastroDesastresService.setFieldsEvent(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de eventos de desastres")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEventosDeDesastres(String endPoint) {
        cadastroDesastresService.createEvent(endPoint);
    }

    @Então("o status code da resposta de desastre deve ser {int}")
    public void oStatusCodeDaRespostaDeDesastreDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroDesastresService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroDesastresService.gson.fromJson(
                cadastroDesastresService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }

    // Passos para cadastro de eventos da qualidade da água
    @Dado("que eu tenha os seguintes dados do evento de qualidade da água:")
    public void queEuTenhaOsSeguintesDadosDoEventoDeQualidadeDaÁgua(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            cadastroQualidadeAguaService.setFieldsEvent(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de eventos de qualidade da água")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEventosDeQualidadeDaÁgua(String endPoint) {
        cadastroQualidadeAguaService.createEvent(endPoint);
    }

    @Então("o status code da resposta de qualidade da água deve ser {int}")
    public void oStatusCodeDaRespostaDeQualidadeDaAguaDeveSer(int statusCode) {
        Assert.assertNotNull("A resposta está nula, a requisição falhou", cadastroQualidadeAguaService.response);
        Assert.assertEquals(statusCode, cadastroQualidadeAguaService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api para qualidade da água deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiParaQualidadeDaAguaDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroQualidadeAguaService.gson.fromJson(
                cadastroQualidadeAguaService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }

    // Passos para cadastro de eventos da qualidade do Ar
    @Dado("que eu tenha os seguintes dados do evento de qualidade do ar:")
    public void queEuTenhaOsSeguintesDadosDoEventoDeQualidadeDoAr(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            cadastroQualidadeArService.setFieldsEvent(columns.get("campo"), columns.get("valor"));
        }
    }
    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de eventos de qualidade do ar")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEventosDeQualidadeDoAr(String endPoint) {
        cadastroQualidadeArService.createEvent(endPoint);
    }


    @Então("o status code da resposta de qualidade do ar deve ser {int}")
    public void oStatusCodeDaRespostaDeQualidadeDoArDeveSer(int statusCode) {
        Assert.assertNotNull("A resposta está nula, a requisição falhou", cadastroQualidadeArService.response);
        Assert.assertEquals(statusCode, cadastroQualidadeArService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api para qualidade do ar deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiParaQualidadeDoArDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroQualidadeArService.gson.fromJson(
                cadastroQualidadeArService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }


    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int arg0) {
    }
}
