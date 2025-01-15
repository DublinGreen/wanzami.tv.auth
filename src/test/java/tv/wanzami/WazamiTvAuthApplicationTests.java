package tv.wanzami;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class WazamiTvAuthApplicationTests {
	
	final String BASE_URL = "http://localhost:9081/graphql";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testFindAllUsersQuery() throws Exception {
		
        String query = "{\n"
        		+ "  \"query\": \" query FindAllUsers { findAllUsers { id status username email telephone password } }\"\n"
        		+ "}";

		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(query))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$.data.findAllUsers").value("greendevng"));
	}
	
	@Test
	void testCountUsersQuery() throws Exception {
		
        String query = "{\n"
        		+ "  \"query\": \"query CountUsers { countUsers}\"\n"
        		+ "}";

		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(query))
				.andExpect(status().isOk());
	}
	
	@Test
	void testUserByIdQuery() throws Exception {
		
        String query = "{\n"
        		+ "  \"query\": \"query UserById { userById(id: \\\"1\\\") { id status username email telephone password }}\"\n"
        		+ "}";

		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(query))
				.andExpect(status().isOk());
	}
	
	@Test
	void testCreateCountryMutation() throws Exception {
	    String mutation = "{\n"
	    		+ "  \"query\": \"mutation CreateCountry { createCountry(name: \\\"Test\\\") { id name status }}\"\n"
	    		+ "}";

	    mockMvc.perform(post(BASE_URL)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(mutation))
	            .andExpect(status().isOk());
	}

}
