package pl.isoft.db.data.dao.flat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;
import pl.isoft.db.data.ShotRM;

public class ShotFlatMapper implements RowMapper<Shot>
{
	public Shot mapRow(ResultSet rs, int arg1) throws SQLException
	{
		Shot shot = null;
		try
		{
			Class<?> c = Class.forName(rs.getString("classmapper"));
			shot = (Shot) c.newInstance();
			shot.setId(rs.getInt("id"));
			shot.setFpNumber(rs.getString("name"));
			if (shot instanceof ShotRM)
				((ShotRM) shot).setUsage(rs.getString("usage"));
			else if (shot instanceof ShotRF)
				((ShotRF) shot).setQuality(rs.getString("quality"));

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return shot;
	}
}
