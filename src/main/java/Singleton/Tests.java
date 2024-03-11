package Singleton;


import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


interface IDb
{
	int getPopulation(String name);

}

class SingletonDatabase implements IDb
{
	private Dictionary<String, Integer> capitals = new Hashtable<>();

	private static int instanceCount = 0;

	private SingletonDatabase()
	{
		instanceCount++;
		System.out.println("init db");
		try
		{
			AtomicInteger ct = new AtomicInteger(0);
			AtomicInteger roundTrip = new AtomicInteger(4);
			Path fullPath = Paths.get("src/main/java", "res.txt");
			List<String> strings = Files.readAllLines(fullPath);
			strings.stream().forEach(s -> {
				if (roundTrip.get() != 0)
				{
					capitals.put(strings.get(ct.get()).trim().toString(), Integer.parseInt(strings.get(ct.get() + 1).trim()));
					ct.addAndGet(2);
					roundTrip.addAndGet(-1);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static final SingletonDatabase INSTANCE = new SingletonDatabase();

	public static SingletonDatabase getINSTANCE()
	{
		return INSTANCE;
	}

	public static int getInstanceCount()
	{
		return instanceCount;
	}

	public int getPopulation(String name)
	{
		return capitals.get(name);
	}
}


class DummyDb implements IDb
{


	private Dictionary<String, Integer> data = new Hashtable<>();

	public DummyDb()
	{
		data.put("alpha", 1);
		data.put("beta", 2);
		data.put("gamma", 3);
	}

	@Override
	public int getPopulation(String name)
	{
		return data.get(name);
	}
}


class ConfigurableRecordFinder
{
	private IDb db;

	public ConfigurableRecordFinder(IDb db)
	{
		this.db = db;
	}

	public int getTotalPopulation(List<String> names)
	{
		int result = 0;
		for (String name : names)
		{
			result += db.getPopulation(name);
		}
		return result;
	}
}


class SingletonRecordFinder
{

}

public class Tests
{
/*
    //TODO WORKS AS INTEGRATION TEST BECAUSE YOU ARE TESTING BOTH CLASSES AND DB BEHAVIOUR
    @Test
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder singletonRecordFinder = new SingletonRecordFinder();
        List<String> names = List.of("Tokyo", "Seul"); // YOINKS
        int tp = singletonRecordFinder.getTotalPopulation(names);
        Assert.assertEquals(232332311 + 223, tp);
    }

 */

	@Test
	public void dependentPopulationTest()
	{
		DummyDb db = new DummyDb();
		ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
		Assert.assertEquals(4, rf.getTotalPopulation(List.of("alpha", "gamma")));

	}


}
