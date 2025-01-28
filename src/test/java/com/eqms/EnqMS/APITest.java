package com.eqms.EnqMS;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class APITest {

    @DataProvider(name = "apiTestCases")
    public Object[][] getTestCases() throws IOException {
        String filePath = "src/main/resources/TestCases.xlsx"; // Replace with your Excel file path
        String sheetName = "Sheet1";
        List<Map<String, String>> testCases = ExcelReader.readExcel(filePath, sheetName);

        Object[][] data = new Object[testCases.size()][1];
        for (int i = 0; i < testCases.size(); i++) {
            data[i][0] = testCases.get(i);
        }
        return data;
    }

    @Test(dataProvider = "apiTestCases")
    public void testAPI(Map<String, String> testCase) {
        // Extract values from the test case
        String httpMethod = testCase.get("HTTPMethod").trim().toUpperCase();
        String endpoint = testCase.get("Endpoint");
        String requestBody = testCase.get("RequestBody");
        int expectedStatusCode = (int) Double.parseDouble(testCase.get("ExpectedStatusCode"));
        String expectedResponse = testCase.get("ExpectedResponse");

        // Set base URI
        RestAssured.baseURI = "http://localhost:9090";

        // Log the URL
        System.out.println("Base URI: " + RestAssured.baseURI);
        System.out.println("Endpoint: " + endpoint);

        // API request and response
        Response response = null;
        switch (httpMethod.toUpperCase()) {
            case "GET":
                response = RestAssured.get(endpoint);
                break;
            case "POST":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .post(endpoint);
                break;
            default:
                Assert.fail("Unsupported HTTP Method: " + httpMethod);
        }

        // Log response
        response.then().log().all();

        // Assertions
        Assert.assertNotNull(response, "Response is null!");
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Status code does not match!");

        // Validate response content, ignoring IDs
        if (expectedResponse != null && !expectedResponse.isEmpty()) {
            assertContentIgnoringIds(response.asString(), expectedResponse);
        }
    }

    /**
     * Validates response content, ignoring ID fields.
     *
     * @param actualResponse The actual JSON response as a String.
     * @param expectedResponse The expected JSON response as a String.
     */
    private void assertContentIgnoringIds(String actualResponse, String expectedResponse) {
        try {
            // Parse JSON strings into Maps
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> actualMap = mapper.readValue(actualResponse, Map.class);
            Map<String, Object> expectedMap = mapper.readValue(expectedResponse, Map.class);

            // Remove IDs recursively from both actual and expected maps
            removeIdFields(actualMap);
            removeIdFields(expectedMap);

            // Compare the modified maps
            Assert.assertEquals(actualMap, expectedMap, "Response body does not match (ignoring IDs)!");
        } catch (Exception e) {
            Assert.fail("Failed to parse JSON or compare content: " + e.getMessage());
        }
    }

    /**
     * Removes ID fields from a JSON map recursively.
     *
     * @param map The JSON map.
     */
    private void removeIdFields(Map<String, Object> map) {
        map.remove("id"); // Remove the "id" field at the current level
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                // Recursively remove IDs in nested maps
                removeIdFields((Map<String, Object>) value);
            } else if (value instanceof List) {
                // Recursively remove IDs in lists
                for (Object item : (List<?>) value) {
                    if (item instanceof Map) {
                        removeIdFields((Map<String, Object>) item);
                    }
                }
            }
        }
    }
}
