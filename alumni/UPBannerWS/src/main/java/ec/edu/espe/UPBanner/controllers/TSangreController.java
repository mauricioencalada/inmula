package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.TSangreVo;
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
public class TSangreController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("tsangre")
	public List<TSangreVo> findTSangre() throws SQLException {
		String q = "SELECT DISTINCT TRIM(SZSTACTDAT_TIPO_SANGRE) as descripcion\r\n" + "FROM SATURN.SZSTACTDAT\r\n"
				+ "ORDER BY 1";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(TSangreVo.class));
	}
}
