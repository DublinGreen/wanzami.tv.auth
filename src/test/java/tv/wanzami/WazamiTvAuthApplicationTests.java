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
	void testFindAllVideosQuery() throws Exception {
		
		String query = """
				{
				  "query": "query FindAllVideos { findAllVideos { id status name description short_description thumbnail video_short_url banner reviews_rating category { id name status } author { id name email telephone status age } videoRating { id status rating description } videoMeta { id video_length video_quality video_url video_trailer_url } } }"
				}
				""";

		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(query))
		.andExpect(status().isOk());
//        .andExpect(jsonPath("$.data.findAllVideos").isArray());
//        .andExpect(jsonPath("$.data.findAllVideos[0].id").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].status").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].name").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].description").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].short_description").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].thumbnail").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].video_short_url").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].banner").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].reviews_rating").exists())
//        
//        .andExpect(jsonPath("$.data.findAllVideos[0].category.id").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].category.name").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].category.status").exists())
//        
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.id").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.name").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.email").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.telephone").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.status").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].author.age").exists())
//        
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoRating.id").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoRating.status").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoRating.rating").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoRating.description").exists())
//        
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoMeta.id").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoMeta.video_length").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoMeta.video_quanlity").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoMeta.video_url").exists())
//        .andExpect(jsonPath("$.data.findAllVideos[0].videoMeta.video_trailer_url").exists());

	}

	@Test
	void testFindAllUsersQuery() throws Exception {
		
        String query = "{\n"
        		+ "  \"query\": \" query FindAllUsers { findAllUsers { id status username email telephone password } }\"\n"
        		+ "}";

		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(query))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$.data.findAllVideos.id").value("greendevng"));
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
