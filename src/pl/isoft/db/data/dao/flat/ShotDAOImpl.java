package pl.isoft.db.data.dao.flat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;
import pl.isoft.db.data.ShotRM;
import pl.isoft.db.data.dao.ShotDAO;

@Component("flatDAO")
public class ShotDAOImpl implements ShotDAO
{
	private final static Logger LOGGER = Logger.getLogger(ShotDAOImpl.class.getName()); 
	
	private JdbcTemplate jdbcTemplate;
	private static final String insert_shot = "INSERT into shotflat VALUES(?,?,?,?,?)";
	private static final String select_shot = "SELECT * from shotflat where  id=?";


	@Override
	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate template)
	{
		jdbcTemplate = template;
	}

	@Override
	@Transactional
	public int insertShot(ShotRM shot)
	{
		System.out.println("ShotDAOImpl.insertShot(RM)");
		Object[] params = new Object[] { null, "pl.isoft.db.data.ShotRM", shot.getFpNumber(), shot.getUsage(), null };
		int shotId = update(insert_shot, params);
		return shotId;
	}

	@Override
	@Transactional
	public int insertShot(ShotRF shot)
	{
		System.out.println("ShotDAOImpl.insertShot(RF)");
		Object[] params = new Object[] { null, "pl.isoft.db.data.ShotRF", shot.getFpNumber(), null, shot.getQuality() };
		int shotId = update(insert_shot, params);
		return shotId;
	}

	@Override
	@Transactional
	public int insertShot(Shot shot)
	{
		System.out.println("ShotDAOImpl.insertShot()");
		Object[] params = new Object[] { null, "pl.isoft.db.data.Shot", shot.getFpNumber(), null, null };
		int shotId = update(insert_shot, params);
		return shotId;
	}

	@Override
	public Shot findShotById(Integer id)
	{
		JdbcTemplate jt = getJdbcTemplate();
		Object[] params = new Object[] { id };
		List<Shot> result;
		result = jt.query(select_shot, params, new ShotFlatMapper());
		if (result != null && result.size() == 1)
			return result.get(0);
		else
			return null;
	}

	public List<Shot> getShots()
	{
		List<Shot> l = new ArrayList<>();
		return l;
	}

	private int update(String queryStatement, Object params[])
	{
		JdbcTemplate jt = getJdbcTemplate();
		int ret = jt.update(queryStatement, params);
		return jt.queryForObject("select @@identity", Long.class).intValue();
	}

	@Override
	public boolean checkConnection()
	{
		try
		{
			getJdbcTemplate().execute("select 1");
			return true;
		} catch (DataAccessException e)
		{

			e.printStackTrace();
			return false;
		}
	}

}
