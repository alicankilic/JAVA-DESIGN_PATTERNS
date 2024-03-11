package Singleton;

import java.util.function.Supplier;

public class SingletonTakesSupplier
{

	//TODO RUNNABLE VOID DONER FUTURE ICIN CALLABLE OBJE
}


class SingletonTester
{
	public static boolean isSingleton(Supplier<Object> func)
	{
		Object obj = func.get();
		boolean bl = false;
		for (int i = 0; i < 10; i++)
		{
			Object obj2 = func.get();
			if (obj2 == obj)
			{
				bl = true;
			}
			else
			{
				bl = false;
			}
		}
		return bl;
	}
}
