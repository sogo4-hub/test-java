package com.example.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HelloServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private HelloServlet servlet;
    private StringWriter stringWriter;
    private PrintWriter writer;
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new HelloServlet();
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        gson = new Gson();
        
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGet_ReturnsCorrectJSON() throws Exception {
        // Execute
        servlet.doGet(request, response);
        writer.flush();

        // Verify content type was set
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");

        // Parse JSON response
        String jsonResponse = stringWriter.toString();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(jsonResponse, type);

        // Verify JSON content
        assertNotNull("Response should not be null", responseMap);
        assertTrue("Response should contain 'message' key", responseMap.containsKey("message"));
        assertEquals("Message should match expected value", 
                     "Hola desde el servlet!", 
                     responseMap.get("message"));
    }

    @Test
    public void testDoGet_SetsCorrectContentType() throws Exception {
        // Execute
        servlet.doGet(request, response);

        // Verify
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
    }

    @Test
    public void testDoGet_WritesValidJSON() throws Exception {
        // Execute
        servlet.doGet(request, response);
        writer.flush();

        // Get response
        String jsonResponse = stringWriter.toString();

        // Verify it's valid JSON by parsing
        assertNotNull("Response should not be empty", jsonResponse);
        assertFalse("Response should not be empty", jsonResponse.trim().isEmpty());
        
        // Should not throw exception when parsing
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(jsonResponse, type);
        assertNotNull("Parsed JSON should not be null", responseMap);
    }
}
