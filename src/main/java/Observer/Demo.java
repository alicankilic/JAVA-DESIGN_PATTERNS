package Observer;


import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T>
{
	public T source;
	public String propertyName;
	public Object newValue;

	public PropertyChangedEventArgs(T source, String propertyName, Object newValue)
	{
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}

}

interface Observer<T>
{
	void handle(PropertyChangedEventArgs<T> args);
}


class Observable<T>
{
	private List<Observer<T>> observers = new ArrayList<Observer<T>>();

	public void subscribe(Observer<T> observer)
	{
		observers.add(observer);
	}

	protected void propertyChanged(T source, String propertyName, Object newValue)
	{
		for (Observer<T> ob : observers)
		{
			ob.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
		}
	}
}


class Person extends Observable<Person>
{
	private int age;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		if (this.age == age) return;
		this.age = age;
		propertyChanged(this, "age", age);
	}
}


public class Demo implements Observer<Person>
{
	public static void main(String[] args)
	{
		new Demo();
	}

	public Demo()
	{
		Person person = new Person();
		person.subscribe(this);
		for (int i = 20; i < 25; i++)
		{
			person.setAge(i);
		}
	}

	public void handle(PropertyChangedEventArgs<Person> args)
	{
		System.out.println("Person's" + args.propertyName + "has changed" + args.newValue);
	}
}
