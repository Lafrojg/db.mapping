package pl.isoft.db.data.dao.flat;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.isoft.db.data.Shot;
import pl.isoft.db.data.ShotRF;
import pl.isoft.db.data.ShotRM;
import pl.isoft.db.data.dao.ShotDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springjdbcdao.xml" })
public class TestFlatDAO
{
	private final static Logger LOGGER = Logger.getLogger(TestFlatDAO.class.getName()); 
	
	@Autowired
	@Qualifier("flatDAO")
	ShotDAO shotDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void shotTest()
	{
		Shot shot = new Shot();
		shot.setFpNumber("NUMER 0003");
		int shotId = shotDAO.insertShot(shot);
		Shot retrievedShot = shotDAO.findShotById(shotId);
		LOGGER.info(  retrievedShot.toString());
		assertTrue(retrievedShot instanceof Shot);

	}

	@Test
	public void shotRFTest()
	{
		ShotRF shotRF = new ShotRF();
		shotRF.setFpNumber("RF NUMBER");
		shotRF.setQuality("HIGH");
		int shotId = shotDAO.insertShot(shotRF);
		Shot retrievedShot = shotDAO.findShotById(shotId);
		LOGGER.info(  retrievedShot.toString());
		assertTrue(retrievedShot instanceof ShotRF);
	}

	@Test
	public void shotRMTest()
	{
		ShotRM shotRM = new ShotRM();
		shotRM.setFpNumber("RM NUMBER");
		shotRM.setUsage("USAGE");
		int shotId = shotDAO.insertShot(shotRM);
		Shot retrievedShot = shotDAO.findShotById(shotId);
		LOGGER.info(  retrievedShot.toString());
		assertTrue(retrievedShot instanceof ShotRM);
	}

	@Test
	public void smokeTest()
	{
		assertTrue(shotDAO.checkConnection());

	}

}
