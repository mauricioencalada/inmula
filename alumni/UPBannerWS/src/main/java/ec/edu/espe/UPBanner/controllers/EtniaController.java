package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.EtniaVo;
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
public class EtniaController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("etnias")
	public List<EtniaVo> findEtnias() throws SQLException {
		String q = "SELECT STVETHN_CODE as codigo, STVETHN_DESC as descripcion\r\n" + 
				"FROM STVETHN\r\n" + 
				"ORDER BY STVETHN_CODE";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(EtniaVo.class));
	}
}
