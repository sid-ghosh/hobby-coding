package jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext_Jdbc.xml" })
public class SpringJdbcTest {
	@Autowired
	ApplicationContext context;

	@Test
	public void testSpringJdbc() {

		EmployeeDao eDao = (EmployeeDao) context.getBean("empdao");

		Employee e = new Employee();
		e.setName("Test-Name3");
		e.setAge(35);
		e.setGender("F");
		e.setSalary(220000);
		e.setDepartment("Test-Department2");
		e.setDesignation("Test-Designation2");
		e.setCountry(3);
		eDao.save(e);
		// eDao.delete(3);
		// eDao.update(e);
		System.out.println("Record inserted successfully...");
	}
}
