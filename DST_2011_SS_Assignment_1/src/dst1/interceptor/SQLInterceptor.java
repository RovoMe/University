package dst1.interceptor;

import org.hibernate.EmptyInterceptor;

public class SQLInterceptor extends EmptyInterceptor 
{
	private static final long serialVersionUID = 3894614218727237142L;
	private int count = 0;
	private int select = 0;
	private int update = 0;
	private int delete = 0;
	private int unknown = 0;
	private static SQLInterceptor instance = new SQLInterceptor();
	
	public synchronized static SQLInterceptor getInstance()
	{
		return instance;
	}
	
	public synchronized String onPrepareStatement(String sql) 
	{
		if (sql.toLowerCase().startsWith("select"))
		{
//			System.out.println("'"+sql+"'");
//			System.out.println("SELECT");
			getInstance().select++;
		}
		else if (sql.toLowerCase().startsWith("update"))
		{
//			System.out.println("'"+sql+"'");
//			System.out.println("UPDATE");
			getInstance().update++;
		}
		else if (sql.toLowerCase().startsWith("delete"))
		{
//			System.out.println("'"+sql+"'");
//			System.out.println("DELETE");
			getInstance().delete++;
		}
		else
		{
//			System.out.println("'"+sql+"'");
//			System.out.println("UNKNOWN ACTION");
			getInstance().unknown++;
		}
		
		if (sql.toLowerCase().startsWith("select computer") || sql.toLowerCase().startsWith("select execution"))
		{
//			System.out.println(sql);
			getInstance().count++;
		}

		return sql;
	}

	public synchronized void reset()
	{
		getInstance().count = 0;
		getInstance().select = 0;
		getInstance().update = 0;
		getInstance().delete = 0;
	}
	
	public synchronized void getSummary()
	{
		System.out.println("Counted select statements for Computers and Executes: "+getInstance().count);
		System.out.println("Total selects: "+getInstance().select);
		System.out.println("Total updates: "+getInstance().update);
		System.out.println("Total delets: "+getInstance().delete);
		System.out.println("Total unknown queries: "+getInstance().unknown);
		System.out.println("Totla queries: "+(getInstance().select+getInstance().update+getInstance().delete+getInstance().unknown));
	}
}
