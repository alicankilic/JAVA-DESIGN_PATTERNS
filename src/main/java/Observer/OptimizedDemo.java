package Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Targs>
{
	private int count = 0;
	private Map<Integer, Consumer<Targs>> handlers = new HashMap<>();

	public Subscription addHandler(Consumer<Targs> handler)
	{
		int i = count;
		handlers.put(count++, handler);
		return new Subscription(this, i);
	}

	public void fire(Targs args)
	{
		for (Consumer<Targs> handler : handlers.values())
		{
			handler.accept(args);
		}
	}

	public class Subscription implements AutoCloseable
	{

		private Event<Targs> event;

		private int id;

		public Subscription(Event<Targs> event, int id)
		{
			this.event = event;
			this.id = id;
		}

		@Override
		public void close() throws Exception
		{
			event.handlers.remove(id);
		}
	}
}


class PropertyChangedEventArgsX
{
	public Object source;
	public String propertyName;

	public PropertyChangedEventArgsX(Object source, String propertyName)
	{
		this.source = source;
		this.propertyName = propertyName;
	}
}


class PersonX
{
	public Event<PropertyChangedEventArgsX> propertyChangedEventArgsXEvent = new Event<PropertyChangedEventArgsX>();

	private int age;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		if (this.age == age) return;
		this.age = age;
		propertyChangedEventArgsXEvent.fire(new PropertyChangedEventArgsX(this, "age"));
	}
}


public class OptimizedDemo
{
	public static void main(String[] args)
	{
		PersonX PersonX = new PersonX();
		Event<PropertyChangedEventArgsX>.Subscription Sub = PersonX.propertyChangedEventArgsXEvent.addHandler(new Consumer<PropertyChangedEventArgsX>()
		{
			@Override
			public void accept(PropertyChangedEventArgsX propertyChangedEventArgsX)
			{
				System.out.println("Persons" + propertyChangedEventArgsX.propertyName + "has changed");
			}
		});

		PersonX.setAge(11);
		PersonX.setAge(17);

	}
}
