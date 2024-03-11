package PrototypeD;


import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;


//TODO CLONABLE IS NOT GOOD FOR DEEP COPYING
//TODO ITS FOR SHALLOW COPY
class Adress implements Cloneable
{
	public String streetName;

	public int houseNumber;

	public Adress(String streetName, int houseNumber)
	{
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString()
	{
		return "Adress{" +
				"streetName='" + streetName + '\'' +
				", houseNumber=" + houseNumber +
				'}';
	}


	//DEEP COPY
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Adress(streetName, houseNumber);
	}
}

class Person implements Cloneable
{
	public String[] names;
	public Adress addres;

	public Person(String[] names, Adress adress)
	{
		this.names = names;
		this.addres = adress;
	}

	@Override
	public String toString()
	{
		return "Person{" +
				"names=" + Arrays.toString(names) +
				", addres=" + addres +
				'}';
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Person(names.clone(), (Adress) addres.clone());
	}


}

class DX
{
	public static void main(String[] args)
	{
		Person brixton = new Person(new String[]{"john", "smith"}, new Adress("brixton", 10));
		Person jave;
		try
		{
			jave = (Person) brixton.clone();
			jave.names[0] = "jane";
			jave.addres.houseNumber = 124;
			System.out.println(jave);
		}
		catch (CloneNotSupportedException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			System.out.println(brixton);
		}


	}
}

class Employee
{
	public Employee(String name, AdressParam adress)
	{
		this.name = name;
		this.adress = adress;
	}

	public String name;
	public AdressParam adress;

	@Override
	public String toString()
	{
		return "Employee{" +
				"name='" + name + '\'' +
				", adress=" + adress +
				'}';
	}

	public Employee(Employee other)
	{
		this.adress = new AdressParam(other.adress);
		this.name = other.name;
	}
}

public class Prototy
{
}

class AdressParam
{


	public AdressParam(String streetAdress, String city, String country)
	{
		this.streetAdress = streetAdress;
		this.city = city;
		this.country = country;
	}

	//TODO CONSTRUCTOR COPYING
	public AdressParam(AdressParam other)
	{
		this.streetAdress = other.streetAdress;
		this.country = other.country;
		this.city = other.city;
	}

	@Override
	public String toString()
	{
		return "AdressParam{" +
				"streetAdress='" + streetAdress + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	public String streetAdress, city, country;
}


class Foo implements Serializable
{
	public int stuff;

	public Foo(int stuff, String whatever)
	{
		this.stuff = stuff;
		this.whatever = whatever;
	}

	@Override
	public String toString()
	{
		return "Foo{" +
				"stuff=" + stuff +
				", whatever='" + whatever + '\'' +
				'}';
	}


	public String whatever;

}

class Test
{
	public static void main(String[] args)
	{
		Foo life = new Foo(42, "life");
		//TODO SERIALIZES AND DESERIALIZES IT CAN DO IT VIA REFLECTIONS TOO?
		Foo roundtrip = SerializationUtils.roundtrip(life);

		roundtrip.whatever = "xyz";
		System.out.println(life);
		System.out.println(roundtrip);

	}
}
