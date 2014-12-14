package pl.isoft.db.data.dao.tree;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRM;

public class ShotRMMapper implements RowMapper<Shot>
{
	public Shot mapRow(ResultSet rs, int arg1) throws SQLException
	{
		ShotRM shot = new ShotRM();
		shot.setId(rs.getInt("id"));
		shot.setFpNumber(rs.getString("name"));
		shot.setUsage(  rs.getString("usage"));		
		return shot;
	}
}
