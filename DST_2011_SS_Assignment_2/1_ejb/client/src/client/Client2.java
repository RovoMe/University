package client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.Future;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dst2.ejb.dto.AuditDTO;
import dst2.ejb.dto.ParameterDTO;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.exceptions.UserNotLoggedInException;
import dst2.ejb.interfaces.GridManagement;


public class Client2
{	
	private final static int WAIT_TIME = 70;
	
	public static void main(String[] args)
	{
		new Client2();
	}

	public Client2()
	{
		Scanner scanner = null;
		try
		{
			InitialContext context = new InitialContext();
			GridManagement gridBean = (GridManagement) context.lookup("java:global/dst2_1/GridManagementFacade!"+GridManagement.class.getName());
			
			System.out.println("Welcome to the GridManagementSystem-Client.\nStart the program by hitting ENTER: ");
			
			scanner = new Scanner(System.in);
			scanner.nextLine();
			
			if (gridBean != null)
			{
				gridBean.init();
				System.out.println("Data initialized");
			}
			else
				throw new Exception("GridManagement not found!\n");

			System.out.println();
			System.out.println("Please press enter to add new price steps to the sytem:");
			scanner.nextLine();

			System.out.println(gridBean.setPriceForJobNumber(10, new BigDecimal(30)));
			System.out.println(gridBean.setPriceForJobNumber(50, new BigDecimal(20)));
			System.out.println(gridBean.setPriceForJobNumber(100, new BigDecimal(15)));
			
			try
			{
				System.out.println();
				System.out.println("Login user abc");
				scanner.nextLine();
				// login fails
				if (!gridBean.login("abc", "def"))
					System.out.println("Login failed");
				else
					System.out.println("Login succeeded");
				
				System.out.println();
				
				// login succeeds
				System.out.println("Login user Rovo");
				scanner.nextLine();
				if (!gridBean.login("Rovo", "password"))
					System.out.println("Login failed");
				else
				{
					System.out.println("Login succeeded.");
					
					System.out.println("Adding Jobs");
					List<String> parameter = new ArrayList<>();
					parameter.add("test");
					parameter.add("test2");
					List<String> parameter2 = new ArrayList<>();
					parameter2.add("z");
					parameter2.add("y");
					parameter2.add("x");
				
					long gridId = gridBean.getIdForGrid("GRID VIE");
					
					try
					{
						System.out.print("Adding 1. job: ");
						gridBean.addJob(gridId, 5, "workflow", parameter);
						System.out.println("SUCCEEDED");
						
						System.out.print("Adding 2. job: ");
						gridBean.addJob(gridId, 4, "another workflow", parameter2);
						System.out.println("SUCCEEDED");
						
						System.out.println("Currently are "+gridBean.getAmountOfTemporaryStoredJobsForGrid(gridId)+" jobs stored temporarly to grid "+gridId);
						System.out.println("Currently are "+gridBean.getAmountOfJobsForGrid(gridId)+" jobs assigned to grid "+gridId+" (executing jobs + temporarly stored)");
						
						System.out.print("Submit temporary job list for grid "+gridId+": ");
						gridBean.submitJobs(gridId);
						System.out.println("SUCCEEDED");
					}
					catch (NotEnoughCPUsAvailableException necaEx)
					{
						System.err.println("FAILED! Reason: "+necaEx.getLocalizedMessage());
					}
					catch (EmptyJobListException ejlEx)
					{
						System.err.println(ejlEx.getLocalizedMessage());
						ejlEx.printStackTrace();
					}
					catch (UserNotLoggedInException unliEx)
					{
						System.err.println("FAILED! Reason: "+unliEx.getLocalizedMessage());
					}
		
					System.out.println();
				}
			}
			catch(Exception ex)
			{
				System.err.println(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (gridBean != null)
				{
					gridBean.logout();
					gridBean = null;
				}
			}
			
			try
			{
				System.out.println("\n\nAdding Jobs for different user");
				scanner.nextLine();
				gridBean = (GridManagement) context.lookup("java:global/dst2_1/GridManagementFacade!"+GridManagement.class.getName());
				System.out.println("Login user MMuster");
				if (!gridBean.login("MMuster", "muster"))
					System.out.println("Login failed");
				else
				{
					System.out.println("Login succeeded.");
					System.out.println();
					
					System.out.println("Adding Jobs");
					List<String> parameter = new ArrayList<>();
					parameter.add("muster");
					List<String> parameter2 = new ArrayList<>();
					parameter2.add("m");
					List<String> parameter3 = new ArrayList<>();
					parameter3.add("0");
					
					long gridId = gridBean.getIdForGrid("GRID GER");
					
					try
					{
						System.out.print("Adding 1. job: ");
						gridBean.addJob(gridId, 5, "muster", parameter);
						System.out.println("SUCCEEDED");
						
						scanner.nextLine();
						
						System.out.print("Adding 2. job: ");
						gridBean.addJob(gridId, 8, "muster2", parameter2);
						System.out.println("SUCCEEDED");
						
						scanner.nextLine();
						
						System.out.print("Adding 3. job: ");
						gridBean.addJob(gridId, 6, "muster3", parameter3);
						System.out.println("SUCCEEDED");
						
						scanner.nextLine();
						
						System.out.println("Currently are "+gridBean.getAmountOfTemporaryStoredJobsForGrid(gridId)+" jobs stored temporarly to grid "+gridId);
						System.out.println("Currently are "+gridBean.getAmountOfJobsForGrid(gridId)+" jobs assigned to grid "+gridId+" (executing jobs + temporarly stored)");
						
						scanner.nextLine();
						
						System.out.print("Submit temporary job list ");
						gridBean.submitJobs(gridId);
						System.out.println("SUCCEEDED");
						
//						System.out.println("Currently are "+jbBean.getAmountOfTemporaryStoredJobsForGrid(gridId)+" jobs stored temporarly to grid "+gridId);
//						System.out.println("Currently are "+jbBean.getAmountOfJobsForGrid(gridId)+" jobs assigned to grid "+gridId+" (executing jobs + temporarly stored)");
					}
					catch (NotEnoughCPUsAvailableException necaEx)
					{
						System.err.println("FAILED! Reason: "+necaEx.getLocalizedMessage());
						
						System.out.print("Clearing temporary job list: ");
						gridBean.removeAllJobsForGrid(gridId);
						System.out.println("SUCCEEDED");
						
						System.out.println("Currently are "+gridBean.getAmountOfTemporaryStoredJobsForGrid(gridId)+" jobs stored temporarly to grid "+gridId);
						System.out.println("Currently are "+gridBean.getAmountOfJobsForGrid(gridId)+" jobs assigned to grid "+gridId+" (executing jobs + temporarly stored)");
						
						System.out.println("Re-adding Jobs");
						try
						{
							System.out.print("Adding 1. job: ");
							gridBean.addJob(gridId, 4, "muster", parameter);
							System.out.println("SUCCEEDED");
							
							System.out.print("Submit temporary job list for grid "+gridId+": ");
							gridBean.submitJobs(gridId);
							System.out.println("SUCCEEDED");
						}
						catch (NotEnoughCPUsAvailableException necaEx2)
						{
							System.err.println("FAILED! Reason: "+necaEx2.getLocalizedMessage());
						}
						catch (EmptyJobListException ejlEx2)
						{
							System.err.println(ejlEx2.getLocalizedMessage());
							ejlEx2.printStackTrace();
						}
						catch (UserNotLoggedInException unliEx)
						{
							System.err.println("FAILED! Reason: "+unliEx.getLocalizedMessage());
						}
					}
					catch (EmptyJobListException ejlEx)
					{
						System.err.println(ejlEx.getLocalizedMessage());
					}
					catch (UserNotLoggedInException unliEx)
					{
						System.err.println("FAILED! Reason: "+unliEx.getLocalizedMessage());
					}
					System.out.println();
				}
			}
			catch (Exception ex)
			{
				System.err.println(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (gridBean != null)
				{
					gridBean.logout();
					gridBean = null;
				}
			}
				
			waiting(WAIT_TIME);
			
			try
			{
				System.out.println();
				System.out.println("Please press enter to get the bill: ");
				scanner.nextLine();
				gridBean = (GridManagement) context.lookup("java:global/dst2_1/GridManagementFacade!"+GridManagement.class.getName());
				Future<String> bill = gridBean.getBill("Rovo");
				System.out.println(bill.get());
			}
			catch (Exception ex)
			{
				System.err.println(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (gridBean != null)
					gridBean = null;
			}
			
			System.out.println("Printing Log");
			scanner.nextLine();
			try
			{
				gridBean = (GridManagement) context.lookup("java:global/dst2_1/GridManagementFacade!"+GridManagement.class.getName());
				for (AuditDTO audit : gridBean.getAuditLog())
				{
					String log = "TIME: "+audit.getTime()+" | METHOD: "+audit.getMethod()+": ";
					if (audit.getParameters().size()>0)
						log += " | PARAM {";
					for(ParameterDTO p : audit.getParameters())
					{
						log += "{"+p.getIndex()+" | "+p.getClassName()+" | "+p.getValue()+"}, ";
					}
					if (audit.getParameters().size()>0)
					{
						log = log.substring(0, log.lastIndexOf(", "));
						log += "}";
					}
					if (audit.getValue() != null && !audit.getValue().equals(""))
						log += " | VALUE: "+audit.getValue();
					if (audit.getFailure()!= null && !audit.getFailure().equals(""))
						log += " | FAILURE: "+audit.getFailure();

					System.out.println(log);
				}
			}
			catch (Exception ex)
			{
				System.err.println(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (gridBean != null)
				{
					gridBean.unload();
					gridBean = null;
				}
			}
		}
		catch (NamingException e)
		{
			System.err.println("Lookup failed due to: "+e.getLocalizedMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (scanner != null)
				scanner.close();
		}
	}
	
	 private static void waiting (int n)
	 {
		 long t0, t1;
		 t0 =  System.currentTimeMillis();
		 do
		 {
			 t1 = System.currentTimeMillis();
		 }
		 while (t1 - t0 < n*1000);
		 
	 }
}
