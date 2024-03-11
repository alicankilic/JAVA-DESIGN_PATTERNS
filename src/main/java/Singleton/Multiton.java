package Singleton;

import java.util.*;


//TODO RESTRICT NUMBER OF INSTANCE THAT ARE CREATED


//Multiton


enum Subsystem
{
	PRIMARY,
	AUXILIARY,
	FALLBACK
}

class Printer
{

	private static int instancect = 0;

	private Printer()
	{
		instancect++;
		System.out.println(instancect);
	}

	private static HashMap<Subsystem, Printer> instances = new HashMap<>();

	public static Printer get(Subsystem ss)
	{

		//TODO LAZY LOADING INSTANCE IS ONLY CREATED WHENEVER A USER ASKS FOR IT

		if (instances.containsKey(ss))
		{
			return instances.get(ss);
		}
		Printer printer = new Printer();
		instances.put(ss, printer);
		return printer;
	}
}


public class Multiton
{
	public static void main(String[] args)
	{
		Printer mainPrinter = Printer.get(Subsystem.PRIMARY);
		Printer aux = Printer.get(Subsystem.AUXILIARY);
		Printer pp = Printer.get(Subsystem.PRIMARY);
	}
}
