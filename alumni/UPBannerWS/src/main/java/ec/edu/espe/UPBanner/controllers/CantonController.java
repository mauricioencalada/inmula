package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.CantonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CantonController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("cantones/{prov}")
	public List<CantonVo> findProvincias(@PathVariable String prov) throws SQLException {
		String q = "SELECT DISTINCT STVCNTY_CODE as codigo, STVCNTY_DESC as descripcion\n" +
                " FROM STVCNTY\n" +
                " WHERE SUBSTR(STVCNTY_CODE,1,2)= '"+ prov + "'\n"+
                " ORDER BY STVCNTY_DESC";
		System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(CantonVo.class));
	}
}
