package pl.isoft.db.data.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;
import pl.isoft.db.data.ShotRM;


public interface ShotDAO
{

	JdbcTemplate getJdbcTemplate();

	int insertShot(ShotRM shot);

	int insertShot(ShotRF shot);

	int insertShot(Shot shot);

	Shot findShotById(Integer id);

	boolean checkConnection();

	
}
