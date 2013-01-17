package dst2.client;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

public class ConsoleOutput implements Runnable
{
	private List<Line> output;
	private boolean done = false; 
	private String name;
	private PrintStream out;
	private PrintStream err;
	private boolean modified;
	private LineType lastType = LineType.NEWLINE;
	
	public ConsoleOutput(String name, PrintStream out, PrintStream err)
	{
		this.name = name;
		this.out = out;
		this.err = err;
		this.output = new Vector<Line>();
	}
	
	public ConsoleOutput(String name)
	{
		this.name = name;
		this.out = System.out;
		this.err = System.err;
		this.output = new Vector<Line>();
	}
	
	public void add(String string)
	{
		synchronized(output)
		{
			this.output.add(new Line(string, LineType.LINE));
			this.modified = true;
		}
	}
	
	public void addNewLine(String string)
	{
		synchronized(output)
		{
			this.output.add(new Line(string, LineType.NEWLINE));
			this.modified = true;
		}
	}
	
	public void addError(String string)
	{
		synchronized(output)
		{
			this.output.add(new Line(string, LineType.ERROR));
			this.modified = true;
		}
	}
	
	public void run()
	{
		while (!this.done)
		{
			synchronized(output)
			{
				if (this.modified)
				{			
					
					for (Line line : this.output)
					{
						if (lastType == LineType.NEWLINE)
							this.out.print(name+"> ");
						
						String sLine = line.getLine();
						if (line.getType() == LineType.NEWLINE)
							this.out.println(sLine);
						else if (line.getType() == LineType.LINE)
							this.out.print(sLine);
						else if (line.getType() == LineType.ERROR)
							this.err.println(sLine);
						
						lastType = line.getType();
					}
					// Remove already shown lines
					this.output.clear();
				}
			}
		}
	}
	
	public void stop()
	{
		this.done = true;
	}
	
	private enum LineType
	{
		LINE,
		NEWLINE,
		ERROR
	}
	
	private class Line
	{
		private String output;
		private LineType type;
		
		public Line(String output, LineType type)
		{
			this.output = output;
			this.type = type;
		}
		
		public String getLine() { return this.output; }
		public LineType getType() { return this.type; }
	}
}
