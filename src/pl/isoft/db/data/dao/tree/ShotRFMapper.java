package pl.isoft.db.data.dao.tree;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;

public class ShotRFMapper implements RowMapper<Shot>
{
	public Shot mapRow(ResultSet rs, int arg1) throws SQLException
	{
		
		ShotRF shot = new ShotRF();
		shot.setId(rs.getInt("id"));
		shot.setFpNumber(rs.getString("name"));
		shot.setQuality(rs.getString("quality"));
		return shot;
	}
}
