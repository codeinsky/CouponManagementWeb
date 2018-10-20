package couponSystem.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import testBeans.Companies;
 

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminFacadeTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private AdminFacadeTest adminFacadeTest ; 
	
	@Before 
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(adminFacadeTest).build();
	}
	
	@Test
	public void testCreateCompany()  throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/createCompany")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(Companies.company1.toString())
				)
				
				 .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
