package ec.edu.espe.UPBanner.controllers;

import ec.edu.espe.UPBanner.vo.CarrerasVo;
import ec.edu.espe.UPBanner.vo.UserVo;
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
public class UserController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("user/pidm/{id}")
	public List<UserVo> findbyPIDM(@PathVariable Long id) throws SQLException {
		String q = "SELECT SUBSTR(f_getspridenid(SPBPERS_PIDM),1,12) AS ID, SPBPERS.SPBPERS_SSN AS CEDULA,\r\n" +
				"\r\n" +
				"                SUBSTR(f_format_name(SPBPERS_PIDM,'LFMI'),1,60) AS NOMBRES, NVL(SPBPERS_ETHN_CODE,'01') AS COD_ETNIA, SPBPERS_SEX AS SEXO,\r\n" +
				"\r\n" +
				"                TO_CHAR(SPBPERS_BIRTH_DATE,'YYYY-MM-DD') AS FECHA_NACIMIENTO, SPBPERS_CITZ_CODE AS COD_CIUDADANIA, SPBPERS_MRTL_CODE AS COD_ESTADO_CIVIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(STVSITE_CODE)\r\n" +
				"\r\n" +
				"                FROM SGBSTDN, STVSITE\r\n" +
				"\r\n" +
				"                WHERE SGBSTDN_TERM_CODE_EFF = (SELECT MAX(SGBSTDN_TERM_CODE_EFF) FROM SGBSTDN\r\n" +
				"\r\n" +
				"                WHERE SGBSTDN_LEVL_CODE = 'UG'\r\n" +
				"\r\n" +
				"                AND SGBSTDN_PIDM = SPBPERS_PIDM )\r\n" +
				"\r\n" +
				"                AND SGBSTDN_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGBSTDN_SITE_CODE = STVSITE_CODE),'C50') AS COD	_CENTRO_APOYO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM ),'N') AS POSEE_DISCAPACIDAD,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'NUM_CONADIS'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM),NULL) AS NUM_CARNET_CONADIS,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DA'),'N') AS DISCAPACIDAD_AUDITIVA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DA'),NULL) AS PORCENTAJE_AUDITIVA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DF'),'N') AS DISCAPACIDAD_FISICA,\r\n" +
				"\r\n" +
				"               NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DF'),NULL) AS PORCENTAJE_FISICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DI'),'N') AS DISCAPACIDAD_INTELECTUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DI'),NULL) AS PORCENTAJE_INTELECTUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DL'),'N') AS DISCAPACIDAD_LENGUAJE,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DL'),NULL) AS PORCENTAJE_LENGUAJE,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),'N') AS DISCAPACIDAD_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),NULL) AS PORCENTAJE_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),'N') AS DISCAPACIDAD_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),NULL) AS PORCENTAJE_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),'N') AS DISCAPACIDAD_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),NULL) AS PORCENTAJE_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),'45') AS COD_PAIS_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PROVINCIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_CANTON_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS PARROQUIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PAIS_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"               WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PROVINCIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_CANTON_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS PARROQUIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE1)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE1,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_HOUSE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS N_CASA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE2)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE2,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE3)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE3,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE4)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE4,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_CELULAR,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),NULL) AS PORCENTAJE_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),'N') AS DISCAPACIDAD_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),NULL) AS PORCENTAJE_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),'N') AS DISCAPACIDAD_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),NULL) AS PORCENTAJE_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PAIS_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PROVINCIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_CANTON_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS PARROQUIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PAIS_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"               WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PROVINCIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_CANTON_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS PARROQUIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE1)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE1,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_HOUSE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS N_CASA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE2)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE2,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE3)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE3,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE4)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE4,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_CELULAR,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_CELULAR,\r\n" +
				"\r\n" +
				"                SPBPERS_MRTL_CODE AS COD_ESTADO_CIVIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOREMAL_EMAIL_ADDRESS) FROM GOREMAL WHERE GOREMAL_EMAL_CODE = 'PERS' AND GOREMAL_PIDM = SPBPERS_PIDM),NULL) AS EMAIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT SORHSCH_SBGI_CODE FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS COLEGIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT TO_CHAR(SORHSCH_GRADUATION_DATE,'YYYY-MM-DD') FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS FECHA_GRADUA,\r\n" +
				"\r\n" +
				"                NVL((SELECT SORHSCH_GPA FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS PROMEDIO\r\n" +
				"\r\n" +
				"                FROM SPBPERS WHERE SPBPERS_PIDM ="+ id + " ";
		System.out.println("DATOS POR PIDM: "+id);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(UserVo.class));
	}
	@GetMapping("user/cedula/{id}")
	public List<UserVo> findbyID(@PathVariable String id) throws SQLException {
		String q = "SELECT SUBSTR(f_getspridenid(SPBPERS_PIDM),1,12) AS ID, SPBPERS.SPBPERS_SSN AS CEDULA,\r\n" +
				"\r\n" +
				"                SUBSTR(f_format_name(SPBPERS_PIDM,'LFMI'),1,60) AS NOMBRES, NVL(SPBPERS_ETHN_CODE,'01') AS COD_ETNIA, SPBPERS_SEX AS SEXO,\r\n" +
				"\r\n" +
				"                TO_CHAR(SPBPERS_BIRTH_DATE,'YYYY-MM-DD') AS FECHA_NACIMIENTO, SPBPERS_CITZ_CODE AS COD_CIUDADANIA, SPBPERS_MRTL_CODE AS COD_ESTADO_CIVIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(STVSITE_CODE)\r\n" +
				"\r\n" +
				"                FROM SGBSTDN, STVSITE\r\n" +
				"\r\n" +
				"                WHERE SGBSTDN_TERM_CODE_EFF = (SELECT MAX(SGBSTDN_TERM_CODE_EFF) FROM SGBSTDN\r\n" +
				"\r\n" +
				"                WHERE SGBSTDN_LEVL_CODE = 'UG'\r\n" +
				"\r\n" +
				"                AND SGBSTDN_PIDM = SPBPERS_PIDM )\r\n" +
				"\r\n" +
				"                AND SGBSTDN_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGBSTDN_SITE_CODE = STVSITE_CODE),'C50') AS COD_CENTRO_APOYO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM ),'N') AS POSEE_DISCAPACIDAD,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'NUM_CONADIS'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM),NULL) AS NUM_CARNET_CONADIS,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DA'),'N') AS DISCAPACIDAD_AUDITIVA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DA'),NULL) AS PORCENTAJE_AUDITIVA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DF'),'N') AS DISCAPACIDAD_FISICA,\r\n" +
				"\r\n" +
				"               NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DF'),NULL) AS PORCENTAJE_FISICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DI'),'N') AS DISCAPACIDAD_INTELECTUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DI'),NULL) AS PORCENTAJE_INTELECTUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DL'),'N') AS DISCAPACIDAD_LENGUAJE,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DL'),NULL) AS PORCENTAJE_LENGUAJE,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),'N') AS DISCAPACIDAD_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),NULL) AS PORCENTAJE_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),'N') AS DISCAPACIDAD_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),NULL) AS PORCENTAJE_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),'N') AS DISCAPACIDAD_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),NULL) AS PORCENTAJE_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),'45') AS COD_PAIS_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PROVINCIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_CANTON_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS PARROQUIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PAIS_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"               WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PROVINCIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_CANTON_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS PARROQUIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE1)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE1,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_HOUSE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS N_CASA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE2)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE2,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE3)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE3,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE4)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE4,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_CELULAR,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DM'),NULL) AS PORCENTAJE_MOTORA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),'N') AS DISCAPACIDAD_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DP'),NULL) AS PORCENTAJE_PSICOLOGICA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT 'S' FROM SGRDISA\r\n" +
				"\r\n" +
				"                WHERE SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),'N') AS DISCAPACIDAD_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOVSDAV_VALUE_AS_CHAR)\r\n" +
				"\r\n" +
				"                FROM GOVSDAV, SGRDISA\r\n" +
				"\r\n" +
				"                WHERE GOVSDAV_TABLE_NAME = 'SGRDISA'\r\n" +
				"\r\n" +
				"                AND GOVSDAV_ATTR_NAME = 'PORCENTAJE'\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = TO_NUMBER(SUBSTR(GOVSDAV_PK_PARENTTAB,1,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)-1)))\r\n" +
				"\r\n" +
				"                AND SGRDISA_TERM_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(INSTR(GOVSDAV_PK_PARENTTAB, CHR(1),1,1)+1),6))\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = TO_CHAR(SUBSTR(GOVSDAV_PK_PARENTTAB,(LENGTH(GOVSDAV_PK_PARENTTAB)-1),2))\r\n" +
				"\r\n" +
				"                AND SGRDISA_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SGRDISA_DISA_CODE = 'DV'),NULL) AS PORCENTAJE_VISUAL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PAIS_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_PROVINCIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS COD_CANTON_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'DN')),NULL) AS PARROQUIA_NACIMIENTO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_NATN_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PAIS_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STAT_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"               WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_PROVINCIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_CNTY_CODE)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS COD_CANTON_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_ZIP)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS PARROQUIA_RESIDENCIA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE1)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE1,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_HOUSE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS N_CASA,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE2)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE2,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE3)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE3,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRADDR_STREET_LINE4)\r\n" +
				"\r\n" +
				"                FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRADDR_SEQNO = (SELECT MAX(SPRADDR_SEQNO) FROM SPRADDR\r\n" +
				"\r\n" +
				"                WHERE SPRADDR_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRADDR_ATYP_CODE = 'PR')),NULL) AS CALLE4,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'PR'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_DOMICILIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_AREA)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_COD_AREA_CELULAR,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(SPRTELE_PHONE_NUMBER)\r\n" +
				"\r\n" +
				"                FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM\r\n" +
				"\r\n" +
				"                AND SPRTELE_SEQNO = (SELECT MAX(SPRTELE_SEQNO) FROM SPRTELE\r\n" +
				"\r\n" +
				"                WHERE SPRTELE_TELE_CODE = 'TM'\r\n" +
				"\r\n" +
				"                AND SPRTELE_PIDM = SPBPERS_PIDM)),NULL) AS TEL_CELULAR,\r\n" +
				"\r\n" +
				"                SPBPERS_MRTL_CODE AS COD_ESTADO_CIVIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT DISTINCT MAX(GOREMAL_EMAIL_ADDRESS) FROM GOREMAL WHERE GOREMAL_EMAL_CODE = 'PERS' AND GOREMAL_PIDM = SPBPERS_PIDM),NULL) AS EMAIL,\r\n" +
				"\r\n" +
				"                NVL((SELECT SORHSCH_SBGI_CODE FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS COLEGIO,\r\n" +
				"\r\n" +
				"                NVL((SELECT TO_CHAR(SORHSCH_GRADUATION_DATE,'YYYY-MM-DD') FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS FECHA_GRADUA,\r\n" +
				"\r\n" +
				"                NVL((SELECT SORHSCH_GPA FROM SORHSCH WHERE SORHSCH_PIDM = SPBPERS_PIDM AND ROWNUM <= 1),'') AS PROMEDIO\r\n" +
				"\r\n" +
				"                FROM SPBPERS WHERE SPBPERS_SSN = '"+ id + "' ";
		System.out.println("DATOS POR ID: "+ id);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(UserVo.class));
	}

    @GetMapping("user/alumni/{cedula}")
    public List<UserVo> findbyCI(@PathVariable String cedula) throws SQLException {
        String q = "SELECT DISTINCT SUBSTR(f_getspridenid(SPBPERS_PIDM),1,12) AS ID, SPBPERS_SSN AS CEDULA,\n" +
                "SUBSTR(f_format_name(SPBPERS_PIDM,'LFMI'),1,60) AS NOMBRES,\n" +
                "TO_CHAR(SPBPERS_BIRTH_DATE,'YYYY-MM-DD') AS FECHA_NACIMIENTO,\n" +
                "SPBPERS_SEX AS GENERO,\n" +
                "STVMRTL_CODE AS COD_ESTADO_CIVIL,\n" +
                "STVMRTL_DESC AS ESTADO_CIVIL,\n" +
                "STVETHN_CODE AS CODIGO_ETNIA,\n" +
                "STVETHN_DESC AS ETNIA,\n" +
                "NVL((SELECT max(STVNATN_CODE ||'-' ||STVNATN_NATION) FROM STVNATN, SPRADDR\n" +
                "WHERE SPRADDR_NATN_CODE = STVNATN_CODE\n" +
                "AND SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "AND SPRADDR_ATYP_CODE = 'PR'\n" +
                "AND SPRADDR_SEQNO  = (SELECT MAX(SPRADDR_SEQNO)\n" +
                "FROM SPRADDR\n" +
                "WHERE SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "AND SPRADDR_ATYP_CODE = 'PR')),'') AS PAIS_RES,\n" +
                "NVL((SELECT STVSTAT_CODE || '-' ||STVSTAT_DESC\n" +
                "      FROM SPRADDR DIR, STVSTAT\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')\n" +
                "        AND DIR.SPRADDR_STAT_CODE = STVSTAT.STVSTAT_CODE),' ') AS PROVINCIA,\n" +
                "NVL((SELECT STVCNTY_CODE || '-' ||STVCNTY_DESC\n" +
                "      FROM SPRADDR DIR, STVCNTY\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')\n" +
                "        AND DIR.SPRADDR_CNTY_CODE = STVCNTY.STVCNTY_CODE),' ') AS CANTON,\n" +
                "NVL((SELECT DIR.SPRADDR_ZIP\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS PARROQUIA,\n" +
                "NVL((SELECT DIR.SPRADDR_STREET_LINE1\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS CALLE_PRINCIPAL,\n" +
                "NVL((SELECT DIR.SPRADDR_HOUSE_NUMBER\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS NUM_DOMICILIO,\n" +
                "NVL((SELECT DIR.SPRADDR_STREET_LINE2\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS CALLE_SECUNDARIA,\n" +
                "NVL((SELECT DIR.SPRADDR_STREET_LINE4\n" +
                "SPRADDR_HOUSE_NUMBER\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS REFERENCIA,\n" +
                "NVL((SELECT DIR.SPRADDR_STREET_LINE3\n" +
                "      FROM SPRADDR DIR\n" +
                "      WHERE DIR.SPRADDR_PIDM = SPBPERS_PIDM\n" +
                "        AND DIR.SPRADDR_ATYP_CODE = 'PR'\n" +
                "        AND DIR.SPRADDR_SEQNO = (SELECT MAX(B.SPRADDR_SEQNO)\n" +
                "                FROM SPRADDR B\n" +
                "                WHERE B.SPRADDR_PIDM = DIR.SPRADDR_PIDM\n" +
                "                  AND B.SPRADDR_ATYP_CODE = 'PR')),' ') AS BARRIO,\n" +
                "NVL((SELECT distinct MAX(F.SPRTELE_PHONE_AREA || F.SPRTELE_PHONE_NUMBER || F.SPRTELE_PHONE_EXT)\n" +
                "FROM SPRTELE F\n" +
                "WHERE SPRTELE_PIDM = SPBPERS_PIDM\n" +
                "AND SPRTELE_TELE_CODE = 'PR'\n" +
                "AND F.SPRTELE_SEQNO = (SELECT MAX(T.SPRTELE_SEQNO)\n" +
                "FROM SPRTELE T\n" +
                "WHERE T.SPRTELE_PIDM = F.SPRTELE_PIDM\n" +
                "AND T.SPRTELE_TELE_CODE = 'PR')), '') AS CONVENCIONAL,\n" +
                "NVL((SELECT distinct MAX(F.SPRTELE_PHONE_AREA || F.SPRTELE_PHONE_NUMBER || F.SPRTELE_PHONE_EXT)\n" +
                "FROM SPRTELE F\n" +
                "WHERE F.SPRTELE_PIDM = SPBPERS_PIDM\n" +
                "AND F.SPRTELE_TELE_CODE = 'TM'\n" +
                "AND F.SPRTELE_SEQNO = (SELECT MAX(T.SPRTELE_SEQNO)\n" +
                "FROM SPRTELE T\n" +
                "WHERE T.SPRTELE_PIDM = F.SPRTELE_PIDM\n" +
                "AND T.SPRTELE_TELE_CODE = 'TM')), '') AS CELULAR,\n" +
                "NVL((SELECT DISTINCT MAX (GOREMAL.GOREMAL_EMAIL_ADDRESS)\n" +
                "      FROM GOREMAL\n" +
                "      WHERE GOREMAL.GOREMAL_PIDM = SPBPERS_PIDM\n" +
                "        AND GOREMAL.GOREMAL_EMAL_CODE = 'STAN'), '') AS CORREO_INSTITUCIONAL,\n" +
                "NVL((SELECT DISTINCT MAX (GOREMAL.GOREMAL_EMAIL_ADDRESS)\n" +
                "      FROM GOREMAL\n" +
                "      WHERE GOREMAL.GOREMAL_PIDM = SPBPERS_PIDM\n" +
                "        AND GOREMAL.GOREMAL_EMAL_CODE = 'PERS'), '') AS CORREO_PERSONAL,\n     " +
				"NVL((SELECT DISTINCT 'MILITAR'\n" +
				"    FROM SGRVETN\n" +
				"    WHERE SGRVETN_PIDM = SPBPERS_PIDM),'CIVIL') AS TIPO_ESTUDIANTE\n" +
                "FROM SPBPERS, STVMRTL, STVETHN\n" +
                "WHERE SPBPERS_SSN = '"+ cedula + "'\n"+
                "AND STVMRTL_CODE = SPBPERS_MRTL_CODE\n" +
                "AND STVETHN_CODE = SPBPERS_ETHN_CODE\n ";
        return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(UserVo.class));
    }


    @GetMapping("carreras/{cedula}")
    public List<CarrerasVo> findCarreras(@PathVariable String cedula) throws SQLException {
        String q = "select DISTINCT NVL((SELECT DISTINCT Z.SPRIDEN_LAST_NAME\n" +
				"FROM SPRIDEN Z\n" +
				"WHERE Z.SPRIDEN_PIDM = 7631\n" +
				"AND Z.SPRIDEN_CHANGE_IND IS NULL),'ESPE') AS INSTITUCION,\n" +
				"SUBSTR(f_get_desc_fnc('STVLEVL',SORLCUR_LEVL_CODE , 30),1,30) AS NIVEL,\n" +
				"SORLCUR_LEVL_CODE AS COD_NIVEL,\n" +
				"SUBSTR(f_get_desc_fnc('STVDEGS',SHRDGMR_DEGS_CODE , 30),1,30) AS STATUS,\n" +
				"SHRDGMR_ACYR_CODE  AS ANIO,\n" +
				"SUBSTR(f_get_desc_fnc('STVCAMP',SORLCUR_CAMP_CODE, 30),1,30) AS CAMPUS,\n" +
				"SMRPRLE_PROGRAM_DESC AS CARRERA,\n" +
				"SUBSTR(f_getspridenid(SHRDGMR_PIDM),1,12) AS \"ID\",\n" +
				"SPBPERS_SSN AS CEDULA,\n" +
				"SUBSTR(f_format_name(SHRDGMR_PIDM,'LFMI'),1,30) AS NOMBRES,\n" +
				"SHRDGMR_GRAD_DATE AS FECHA_GRADO\n" +
				"FROM SHRDGMR, SPBPERS, SMRPRLE, SPRIDEN, SORLCUR\n" +
				"WHERE SHRDGMR_DEGS_CODE = 'GR'\n" +
				"AND SORLCUR_LEVL_CODE = SMRPRLE_LEVL_CODE\n" +
				"AND SHRDGMR_LEVL_CODE = SMRPRLE_LEVL_CODE\n" +
				"AND SHRDGMR_PIDM = SPBPERS_PIDM\n" +
				"AND SHRDGMR_PIDM = SPRIDEN_PIDM\n" +
				"AND SPRIDEN_CHANGE_IND IS NULL\n" +
				"AND SORLCUR_PROGRAM = SMRPRLE_PROGRAM\n" +
				"AND SORLCUR_PROGRAM = SHRDGMR_PROGRAM\n" +
				"AND SORLCUR_CAMP_CODE = SHRDGMR_CAMP_CODE\n" +
				"AND SHRDGMR_PIDM = SORLCUR_PIDM\n" +
				"AND SHRDGMR_PIDM = SORLCUR_PIDM\n" +
				"AND SORLCUR_LMOD_CODE = 'OUTCOME'\n" +
				"AND SORLCUR_LEVL_CODE = SMRPRLE_LEVL_CODE\n" +
				"AND SORLCUR_SEQNO = (SELECT MAX(SORLCUR_SEQNO) FROM SORLCUR\n" +
				"WHERE SORLCUR_PIDM = SHRDGMR_PIDM\n" +
				"AND SORLCUR_LMOD_CODE = 'OUTCOME'\n" +
				"AND SORLCUR_LEVL_CODE = SMRPRLE_LEVL_CODE\n" +
				"AND SORLCUR_PROGRAM = SMRPRLE_PROGRAM)\n" +
				"AND SORLCUR_PIDM IN (SELECT SPBPERS_PIDM FROM SPBPERS " +
				"WHERE SPBPERS_SSN = '"+ cedula + "')\n"+
				"ORDER BY SMRPRLE_PROGRAM_DESC, NOMBRES";
        System.out.println(q);
        return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(CarrerasVo.class));
    }
}
