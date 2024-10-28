package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.QualidadeAguaModel;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroQualidadeAguaService {

    final QualidadeAguaModel qualidadeAguaModel = new QualidadeAguaModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    private String contractPath;

    public void setFieldsEvent(String field, String value) {
        switch (field) {
            case "numeroEvento" -> qualidadeAguaModel.setNumeroEvento(Integer.parseInt(value));
            case "tipoEvento" -> qualidadeAguaModel.setTipoEvento(value);
            case "local" -> qualidadeAguaModel.setLocal(value);
            case "nivelContaminacao" -> qualidadeAguaModel.setNivelContaminacao(Double.parseDouble(value));
            case "dataEvento" -> qualidadeAguaModel.setDataEvento(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }

    public void createEvent(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(qualidadeAguaModel);

        // Log dos dados a serem enviados
        System.out.println("Enviando dados: " + bodyToSend);

        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(bodyToSend)
                    .when()
                    .post(url)
                    .then()
                    .extract()
                    .response();

            // Log para verificar a resposta
            System.out.println("Resposta da API: " + response.asString());

            // Log para depuração em caso de erro
            if (response.statusCode() != 201) {
                System.out.println("Erro ao criar evento: " + response.asString());
            }
        } catch (Exception e) {
            System.out.println("Erro ao enviar requisição: " + e.getMessage());
            response = null; // Define response como nulo em caso de erro
        }
    }

    // Método para definir o caminho do contrato
    public void setContract(String contract) {
        this.contractPath = "src/test/resources/contracts/" + contract + ".json";
    }

    // Método para validar a resposta contra o contrato
    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        // Ler o esquema do contrato
        String schemaString = new String(Files.readAllBytes(Paths.get(contractPath)));

        // Criar uma instância do JsonSchemaFactory
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.builder().build();

        // Criar o schema a partir do contrato
        JsonSchema schema = schemaFactory.getSchema(schemaString);

        // Converter a resposta da API para JsonNode para validação
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseNode = mapper.readTree(response.asString());

        // Validar a resposta contra o esquema
        return schema.validate(responseNode);
    }
}
