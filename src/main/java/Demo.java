import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//TODO SINGLE CLASS RESPONSIBILITY

class Journal
{
	private final List<String> list = new ArrayList<>();
	private static int count = 0;


	public void addEntry(String txt)
	{
		count++;
		list.add(txt);
	}

	public void deleteEntry(String txt, int index)
	{
		list.remove(index);
	}

	@Override
	public String toString()
	{
		return String.join(System.lineSeparator(), list);
	}

}

class Persistance
{

	public void save(Journal jl, String fl)
	{
		try
		{
			PrintStream out = new PrintStream(fl);
			System.out.println(toString());
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	public void load(Journal jl)
	{
	}

	public void load(Journal jl, URL url)
	{
	}
}

class DemoMan
{

	public static void main(String[] args)
	{
		Journal j1 = new Journal();

		j1.addEntry("a");
		j1.addEntry("b");

		System.out.println(j1);

	}
}