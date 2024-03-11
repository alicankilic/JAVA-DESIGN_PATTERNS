package Factory;


import org.javatuples.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface HotDrink
{
	void consume();
}


class Tea implements HotDrink
{

	@Override
	public void consume()
	{
		System.out.println("DELICIOUS");
	}
}

class Coffe implements HotDrink
{

	@Override
	public void consume()
	{
		System.out.println("THIS COFFE IS DELICIOUS");
	}
}

interface HotDrinkFactory
{
	HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory
{

	@Override
	public HotDrink prepare(int amount)
	{
		System.out.println("put in the tea bag,boil water,pour" + amount + "ml,add lemon,enjoy");
		return new Tea();
	}

}

class CoffeFactory implements HotDrinkFactory
{
	@Override
	public HotDrink prepare(int amount)
	{
		System.out.println("put in the coffe,boil water,pour" + amount + "ml,add milk,enjoy");
		return new Coffe();

	}
}

class HotDrinkMachine
{
	private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

	public HotDrinkMachine()
	{
		Set<Class<? extends HotDrinkFactory>> types = new Reflections("Factory").getSubTypesOf(HotDrinkFactory.class);
		for (Class<? extends HotDrinkFactory> type :
				types)
		{
			try
			{
				namedFactories.add(new Pair<>(type.getSimpleName().replace("Factory", ""), type.getDeclaredConstructor().newInstance()));
			}
			catch (InstantiationException | IllegalAccessException | InvocationTargetException |
				   NoSuchMethodException e)
			{
				throw new RuntimeException(e);
			}
		}

	}

	public HotDrink makeDrink() throws Exception
	{
		System.out.println("available drinks");
		for (int i = 0; i < namedFactories.size(); i++)
		{
			System.out.println(" " + i + " " + namedFactories.get(i).getValue0());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			String s;
			int i, amount;
			if ((s = reader.readLine()) != null && (i = Integer.parseInt(s)) >= 0 && i < namedFactories.size())
			{
				System.out.println("Specify amount");
				s = reader.readLine();
				if (s != null && (amount = Integer.parseInt(s)) > 0)
				{
					return namedFactories.get(i).getValue1().prepare(amount);
				}
			}
			System.out.println("incorrect input try again");

		}
	}

}


class DemoX
{
	public static void main(String[] args) throws Exception
	{
		HotDrinkMachine machine = new HotDrinkMachine();
		HotDrink drink = machine.makeDrink();
		drink.consume();
	}
}

public class AbstractFactoryEx
{
}
