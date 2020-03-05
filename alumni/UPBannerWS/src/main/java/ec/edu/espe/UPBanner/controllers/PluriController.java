package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.PluriVo;
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
public class PluriController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("pluri")
	public List<PluriVo> findPluri() throws SQLException {
		String q = "SELECT DISTINCT TRIM(SZSTACTDAT_PLURINAC) as descripcion\r\n" + 
				"FROM SATURN.SZSTACTDAT";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(PluriVo.class));
	}
}
