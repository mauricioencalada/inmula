package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.ProvinciaVo;
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
public class ProvinciaController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("provincias")
	public List<ProvinciaVo> findProvincias() throws SQLException {
		String q = "     SELECT DISTINCT STVSTAT_CODE as codigo, STVSTAT_DESC as descripcion\r\n"
				+ "     FROM STVSTAT \r\n" + "     ORDER BY descripcion\r\n" + "";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ProvinciaVo.class));
	}
}
