package Proxy;


import java.util.Objects;

class Creature
{

	//TODO LOGGING VEYA CONTROLLING ICIN PRIMITIVE BIR INT YERINE BOYLE BIR CLASS IMPLEMENT ETMEK DAHA IYI
	private Property<Integer> agility = new Property<Integer>(11);

	public void setAgility(int val)
	{
		agility.setValue(val);
	}

	public int getAgility()
	{
		return agility.getValue();
	}
}


class Property<T>
{
	private T value;

	public Property(T value)
	{
		this.value = value;
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Property<?> property = (Property<?>) o;
		return Objects.equals(value, property.value);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(value);
	}
}


public class PropertyProxy
{
	public static void main(String[] args)
	{
		Creature cr = new Creature();
		cr.setAgility(3);
	}
}
