package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.PaisVo;
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
public class PaisController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("paises")
	public List<PaisVo> findPaises() throws SQLException {
		String q = "SELECT DISTINCT STVNATN_CODE as codigo, UPPER(STVNATN_NATION) as descripcion\n" +
                " FROM STVNATN\n" +
                " ORDER BY UPPER(STVNATN_NATION)";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(PaisVo.class));
	}

}
