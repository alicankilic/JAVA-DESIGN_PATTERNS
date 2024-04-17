package ChainOResponsibility;


import com.google.inject.CreationException;

import org.checkerframework.checker.units.qual.C;

class Creature
{
	public String name;
	public int attack, defence;


	public Creature(String name, int attack, int defence)
	{
		this.name = name;
		this.attack = attack;
		this.defence = defence;
	}

	@Override
	public String toString()
	{
		return "Creature{" +
				"name='" + name + '\'' +
				", attack=" + attack +
				", defence=" + defence +
				'}';
	}

}


class CreatureModifier
{
	protected Creature creature;
	protected CreatureModifier next;

	public CreatureModifier(Creature creature)
	{
		this.creature = creature;
	}

	public void add(CreatureModifier cm)
	{
		if (next != null)
		{
			next.add(cm);
		}
		else
		{
			next = cm;
		}
	}

	public void handle()
	{
		if (next != null)
		{
			next.handle();
		}
	}

}

class DoubleAttackModifier extends CreatureModifier
{

	public DoubleAttackModifier(Creature creature)
	{
		super(creature);
	}

	@Override
	public void handle()
	{
		System.out.println("doubling" + creature.name + "'s attack");
		creature.attack *= 2;
		super.handle();
	}
}

class IncreaseDefenceModifier extends CreatureModifier
{

	public IncreaseDefenceModifier(Creature creature)
	{
		super(creature);
	}

	@Override
	public void handle()
	{
		System.out.println("increasing" + creature.name + "'s defence");
		creature.defence = creature.defence + 3;
		super.handle();
	}
}


class NoBonusesModifier extends CreatureModifier
{

	public NoBonusesModifier(Creature creature)
	{
		super(creature);
	}

	@Override
	public void handle()
	{
		//
		System.out.println("no bonus");
	}
}

public class Demo
{
	public static void main(String[] args)
	{
		Creature gb = new Creature("Gobling", 2, 10);
		System.out.println(gb);

		CreatureModifier root = new CreatureModifier(gb);

		System.out.println(root);
		root.add(new DoubleAttackModifier(gb));


		System.out.println(root + "defence");
		root.add(new IncreaseDefenceModifier(gb));

		root.handle();
		System.out.println(gb);
	}
}
