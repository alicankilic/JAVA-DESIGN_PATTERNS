package ChainOResponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//CQS
class Event<Args>
{

	private int index = 0;
	private Map<Integer, Consumer<Args>> handlers = new HashMap<>();


	public int subCustomerArgsHandler(Consumer<Args> handler)
	{
		int i = index;
		handlers.put(i++, handler);
		return i;

	}

	public void unSubCustomerArgsHandler(int idx)
	{
		handlers.remove(idx);
	}

	public void fire(Args args)
	{
		for (int i = 0; i < handlers.size(); i++)
		{
			Consumer<Args> argsConsumer = handlers.get(i);
			argsConsumer.accept(args);
		}
	}
}


class Query
{
	public String creatureName;

	enum Argument
	{
		ATTACK, DEFENSE
	}

	public Argument argument;

	public int result;

	public Query(String creatureName, Argument argument, int result)
	{
		this.creatureName = creatureName;
		this.argument = argument;
		this.result = result;
	}

}


class Game
{
	public Event<Query> queries = new Event<>();
}


class CreatureX
{
	private Game game;
	public String name;
	public int baseAttack, baseDefense;

	public CreatureX(Game game, String name, int baseAttack, int baseDefense)
	{
		this.game = game;
		this.name = name;
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
	}


	public Game getGame()
	{
		return game;
	}

	public String getName()
	{
		return name;
	}


	public int getAttack()
	{
		Query query = new Query(name, Query.Argument.ATTACK, baseAttack);
		game.queries.fire(query);
		return query.result;
	}

	public int getDefense()
	{
		Query query = new Query(name, Query.Argument.DEFENSE, baseDefense);
		game.queries.fire(query);
		return query.result;
	}

	@Override
	public String toString()
	{
		return "Creture{" +
				"game=" + game +
				", attack='" + name + '\'' +
				", baseAttack=" + baseAttack +
				", baseDefense=" + baseDefense +
				'}';
	}


}

public class Eve
{
}
