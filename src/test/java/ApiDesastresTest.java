import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

public class ApiDesastresTest {

    @Test
    public void testResponseSchema() throws Exception {
        String jsonResponse = given().when().get("/api/desastres").asString();
        System.out.println("Response: " + jsonResponse);

        // Carregar o esquema JSON do arquivo
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(new File("src/test/resources/schemas/cadastro-bem-sucedido.json"));

        // Criar o validador de esquema
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(com.networknt.schema.SpecVersion.VersionFlag.V7); // Versão especificada
        JsonSchema schema = factory.getSchema(schemaNode);

        // Validar a resposta JSON contra o esquema
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        Set<ValidationMessage> validationMessages = schema.validate(jsonNode);

        assertTrue(validationMessages.isEmpty(), "JSON não corresponde ao esquema: " + validationMessages);
    }
}
