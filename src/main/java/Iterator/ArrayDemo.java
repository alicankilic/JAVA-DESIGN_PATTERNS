package Iterator;


import org.checkerframework.checker.units.qual.C;

import java.awt.event.WindowStateListener;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature
{
	private int strenght, agility, intelligence;

	public int max()
	{
		return Math.max(strenght, Math.max(agility, intelligence));

	}

	public int sum()
	{
		return strenght + agility + intelligence;
	}


	public double average()
	{
		return sum() / 3.0;
	}

	public int getStrenght()
	{
		return strenght;
	}

	public void setStrenght(int strenght)
	{
		this.strenght = strenght;
	}

	public int getAgility()
	{
		return agility;
	}

	public void setAgility(int agility)
	{
		this.agility = agility;
	}

	public int getIntelligence()
	{
		return intelligence;
	}

	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}
}


class Creature implements Iterable<Integer>
{


	//TODO 3,4,5,6,7,8. ..... WHATEVER YOU LIKE
	private int[] stats = new int[3];

	public int getStrength()
	{
		return stats[0];
	}

	public void setStrength(int str)
	{
		this.stats[0] = str;
	}


	public int sum()
	{
		return IntStream.of(stats).sum();
	}

	public int max()
	{
		return IntStream.of(stats).max().getAsInt();

	}

	public double avg()
	{
		return IntStream.of(stats).average().getAsDouble();
	}

	@Override
	public Iterator<Integer> iterator()
	{
		return IntStream.of(stats).iterator();
	}

	@Override
	public void forEach(Consumer<? super Integer> action)
	{
		for (int x : stats)
		{
			action.accept(x);
		}
	}

	@Override
	public Spliterator<Integer> spliterator()
	{
		return IntStream.of(stats).spliterator();
	}
}

public class ArrayDemo
{

	public static void main(String[] args)
	{
		Creature creature = new Creature();
		creature.setStrength(12);

	}

}
