package com.techm.ms.component;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.techm.ms.model.User;
import com.techm.ms.resource.UserResource;
import com.techm.ms.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	   private MockMvc mockMvc;

	    @Mock
	    private UserService userService;

	    @InjectMocks
	    private UserResource userResource;

	    @Mock
	    private User user;
	    
	    @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(userResource)
	                .build();
	    }

	    @Test
	    public void testFindByUserId() throws Exception {

	        when(userService.findByUserId(1)).thenReturn(user);

	        mockMvc.perform(get("/user/1"))
	                .andExpect(status().isOk())
	                .andExpect((ResultMatcher) content());

	        verify(userService).findByUserId(1);
	    }

	    @Test
	    public void testCreateUser() throws Exception {
	    	 String json = "{\n" +
	                 "  \"id\": \"123\",\n" +
	                 "  \"name\": \"Bala\"\n" +
	                 "}";
	        mockMvc.perform((RequestBuilder) ((ResultActions) post("/user")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id", Matchers.is("123")))
	                .andExpect(jsonPath("$.name", Matchers.is("Bala")))
	                .andExpect(jsonPath("$.*", Matchers.hasSize(2))));
	    }

	
}
