package pl.isoft.db.data.dao.tree;

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

@Component("treeDAO")
public class ShotDAOImpl implements ShotDAO
{
	private final static Logger LOGGER = Logger.getLogger(ShotDAOImpl.class.getName());

	private JdbcTemplate jdbcTemplate;
	private static final String insert_shot = "INSERT into shot VALUES(?,?,?,?)";
	private static final String insert_shotrm = "INSERT into shotrm VALUES(?,?)";
	private static final String insert_shotrf = "INSERT into shotrf VALUES(?,?)";
	private static final String select_shot = "SELECT shot.id, shot.rmid, shot.rfid,shot.name,rfshot.quality,rmshot.usage from shot "
			+ "LEFT OUTER JOIN rmshot ON shot.rmid=rmshot.id   LEFT OUTER JOIN rfshot ON shot.rfid=rfshot.id  where  shot.id=?";

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
		LOGGER.info("ShotDAOImpl.insertShot(RM)");
		Object[] params = new Object[] { null, shot.getUsage() };
		int foreignId = update(insert_shotrm, params);
		params = new Object[] { null, shot.getFpNumber(), foreignId, null };
		int shotId = update(insert_shot, params);
		return shotId;
	}

	@Override
	@Transactional
	public int insertShot(ShotRF shot)
	{
		LOGGER.info("ShotDAOImpl.insertShot(RF)");
		Object[] params = new Object[] { null, shot.getQuality() };
		int foreignId = update(insert_shotrf, params);
		params = new Object[] { null, shot.getFpNumber(), null, foreignId };
		int shotId = update(insert_shot, params);
		return shotId;
	}

	@Override
	@Transactional
	public int insertShot(Shot shot)
	{
		LOGGER.info("ShotDAOImpl.insertShot()");
		Object[] params = new Object[] { null, shot.getFpNumber(), null, null };
		return update(insert_shot, params);
	}

	@Override
	public Shot findShotById(Integer id)
	{
		JdbcTemplate jt = getJdbcTemplate();
		Object[] params = new Object[] { id };
		List<Shot> result = jt.query(select_shot, params, new ShotMapper());

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
