package com.example.assertions;

import java.lang.reflect.Field;

import com.example.domain.models.Player;
import com.example.mapper.PlayersMapper;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@NoArgsConstructor
public class ApiAssertions {
    

    public <T> void assertEquals(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public <T> void assertNotNull(T actual) {
        assertThat(actual)
                .as("The field '%s' should not be null", actual)
                .isNotNull();
    }

    public void verifyStatusCode(Response response, int expectedCode) {
        int actualStatusCode = response.statusCode();
        assertThat(actualStatusCode)
                .as("Expected status code to be %d but found %d", expectedCode, actualStatusCode)
                .isEqualTo(expectedCode);
        
    }

    public void assertFieldExists(Response response, String field) {
        boolean exists = response.jsonPath().get(field) != null;
        assertThat(exists)
                .as("Expected field '%s' to exist in response, but it was missing", field)
                .isTrue();
        
    }

    public <T> void assertResponseMatchesMappedObjectIgnoringFields(Response response, Player expectedObject, Class<T> dtoClass, String... ignoreFields) {
        try {
            T responseDto = response.body().as(dtoClass);
            T mappedObject = PlayersMapper.map(expectedObject, dtoClass);

            assertThat(responseDto)
                    .as("The Response DTO does not match the mapped object!")
                    .usingRecursiveComparison()
                    .ignoringFields(ignoreFields)
                    .isEqualTo(mappedObject);
        } catch (Exception ex) {
            fail("Failed to deserialize or map response: %s", ex.getMessage());
        }
    }

    public <T> void assertThatAllFieldsAreNotNull(T dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(dto);
                SoftAssertions.assertSoftly(softly ->
                        softly.assertThat(fieldValue)
                                .as("The field '%s' should not be null", field.getName())
                                .isNotNull());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
    }
}
