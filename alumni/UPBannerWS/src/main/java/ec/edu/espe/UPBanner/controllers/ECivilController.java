package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.ECivilVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ECivilController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("ecivil")
	public List<ECivilVo> findECivil() throws SQLException {
		String q = "SELECT STVMRTL_CODE as codigo, STVMRTL_DESC as descripcion\r\n" + "FROM STVMRTL\r\n"
				+ "ORDER BY STVMRTL_DESC";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ECivilVo.class));
	}

}
