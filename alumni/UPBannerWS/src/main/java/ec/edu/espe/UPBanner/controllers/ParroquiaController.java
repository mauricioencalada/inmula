package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.ParroquiaVo;
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
public class ParroquiaController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("parroquia/{canton}/{prov}/{pais}")
    public List<ParroquiaVo> findParroquia(@PathVariable String canton, @PathVariable String prov, @PathVariable String pais) throws SQLException {
        String q = "SELECT DISTINCT GTVZIPC_CODE as nombre \n" +
                "   FROM GTVZIPC\n" +
                "   WHERE GTVZIPC_CNTY_CODE = '"+ canton + "'\n"+
                "   AND GTVZIPC_STAT_CODE =  '"+ prov + "'\n" +
                "   AND GTVZIPC_NATN_CODE =  '"+ pais + "'\n" +
                "   ORDER BY GTVZIPC_CODE";
        System.out.println(q);
        return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ParroquiaVo.class));
    }
}
