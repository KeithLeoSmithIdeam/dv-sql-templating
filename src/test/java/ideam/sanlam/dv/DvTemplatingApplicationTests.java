package ideam.sanlam.dv;

import ideam.sanlam.dv.application.internal.commandservices.GenerateSqlCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DvTemplatingApplicationTests {

    @Autowired
    GenerateSqlCommandService svc;

	@Test
	void testOne() {

        Map<String, Object> context = new HashMap<>();
        context.put("name", "Mitchell");

        String templateName = "home";
        //String result = svc.generate(templateName,context);
	}

}
