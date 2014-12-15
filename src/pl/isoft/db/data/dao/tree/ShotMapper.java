package pl.isoft.db.data.dao.tree;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;
import pl.isoft.db.data.ShotRM;

public class ShotMapper implements RowMapper<Shot>
{
	public Shot mapRow(ResultSet rs, int arg1) throws SQLException
	{

		Shot shot = new Shot();
		if (rs.getInt("rmid") != 0)
		{
			shot = new ShotRM();
			shot.setId(rs.getInt("id"));
			shot.setFpNumber(rs.getString("name"));
			((ShotRM) shot).setUsage(rs.getString("usage"));
		} else if (rs.getInt("rfid") != 0)
		{
			shot = new ShotRF();
			shot.setId(rs.getInt("id"));
			shot.setFpNumber(rs.getString("name"));
			((ShotRF) shot).setQuality(rs.getString("quality"));
		} else
		{
			shot.setId(rs.getInt("id"));
			shot.setFpNumber(rs.getString("name"));
		}

		return shot;
	}
}
