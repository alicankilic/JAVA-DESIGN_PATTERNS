package Singleton;

//TODO MONOSTATE just change the parameters to static

class ChiefExecutiveOfficer
{
	private static String name;
	private static int age;


	@Override
	public String toString()
	{
		return "ChiefExecutiveOfficer{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		ChiefExecutiveOfficer.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		ChiefExecutiveOfficer.age = age;
	}
}

public class MonoState
{
	public static void main(String[] args)
	{
		ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
		ceo.setName("adam");
		ceo.setAge(32);

		ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
		System.out.println(ceo2);
	}
}
