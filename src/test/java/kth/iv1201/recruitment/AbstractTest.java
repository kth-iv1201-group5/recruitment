package kth.iv1201.recruitment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Resource: https://www.tutorialspoint.com/spring_boot/spring_boot_rest_controller_unit_test.htmj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RecruitmentApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> typeClass) throws JsonProcessingException {
		return new ObjectMapper().readValue(json, typeClass);
	}
}
