package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.CiudadaniaVo;
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
public class CiudadaniaController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("ciudadania")
	public List<CiudadaniaVo> findCiudadania() throws SQLException {
		String q = "SELECT STVCITZ_CODE as codigo, STVCITZ_DESC as descripcion\r\n" + "FROM STVCITZ\r\n"
				+ "ORDER BY STVCITZ_DESC";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(CiudadaniaVo.class));
	}
}
