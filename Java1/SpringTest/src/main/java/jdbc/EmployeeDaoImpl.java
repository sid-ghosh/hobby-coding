package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import jdbc.Employee;
import jdbc.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;

	public EmployeeDaoImpl() {
	}

	public void save(final Employee emp) {
		// Anonymous approach
		simpleJdbcTemplate.getJdbcOperations().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "INSERT INTO employee(NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				//pstmt.setInt(1, emp.getId());
				pstmt.setString(1, emp.getName());
				pstmt.setInt(2, emp.getAge());
				pstmt.setString(3, emp.getGender());
				pstmt.setDouble(4, emp.getSalary());
				pstmt.setString(5, emp.getDesignation());
				pstmt.setString(6, emp.getDepartment());
				pstmt.setInt(7, emp.getCountry());
				return pstmt;
			}
		});

		// Short cut approach
		/*
		 * String query =
		 * "INSERT INTO employee(eno, ename, desig, sal) VALUES (?,?,?,?)";
		 * Object[] data = {emp.getEno(), emp.getEname(), emp.getDesig(),
		 * emp.getSal()}; simpleJdbcTemplate.update(query, data);
		 */
	}

	public void delete(final int eno) {
		// TODO Auto-generated method stub
		simpleJdbcTemplate.getJdbcOperations().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "delete from employee where i = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, eno);
				return pstmt;
			}
		});
	}

	public Employee get(final int eno) {
		return new Employee();
	}

	/*
	 * // TODO Auto-generated method stub
	 * simpleJdbcTemplate.getJdbcOperations().update(new
	 * PreparedStatementCreator() { public PreparedStatement
	 * createPreparedStatement(Connection con)throws SQLException { String query
	 * = "select * from employee where eno = ?"; PreparedStatement pstmt
	 * =con.prepareStatement(query); pstmt.setInt(1, eno); return pstmt; } }); }
	 */
	public void update(final Employee e) {
		// TODO Auto-generated method stub
		/*
		 * simpleJdbcTemplate.getJdbcOperations().update(new
		 * PreparedStatementCreator() { public PreparedStatement
		 * createPreparedStatement(Connection con) throws SQLException { String
		 * query =
		 * "update employee set ename = ?, desig = ?, sal = ? where eno = ?";
		 * PreparedStatement pstmt = con.prepareStatement(query);
		 * pstmt.setInt(4, e.getEno()); pstmt.setString(1, e.getEname());
		 * pstmt.setString(2, e.getDesig()); pstmt.setDouble(3, e.getSal());
		 * return pstmt; } });
		 */
	}
}