package pl.isoft.db.data;


public class Shot
{
	private Integer id;
	private String fpNumber;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFpNumber()
	{
		return fpNumber;
	}

	public void setFpNumber(String fpNumber)
	{
		this.fpNumber = fpNumber;
	}

	@Override
	public String toString()
	{
		return "Welcome in " + this.getClass();
	}

}
