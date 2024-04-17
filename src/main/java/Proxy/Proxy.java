package Proxy;

interface IDrivable
{
	void drive();
}

class Car implements IDrivable
{

	protected Driver driver;


	public Car(Driver driver)
	{
		this.driver = driver;
	}

	@Override
	public void drive()
	{
		System.out.println("car beign driven");
	}
}

class Driver
{
	public int age;

	public Driver(int age)
	{
		this.age = age;
	}


}


class CarProxy extends Car
{
	public CarProxy(Driver driver)
	{
		super(driver);
	}

	@Override
	public void drive()
	{
		if (driver.age > 18)
		{
			super.drive();
		}
		else
		{
			System.out.println("driver too young");
		}
	}
}


public class Proxy
{
	public static void main(String[] args)
	{
		//TODO USAGE OF PROXY VIA INHERITANCE
		Car c1 = new CarProxy(new Driver(11));
		c1.drive();


	}
}
