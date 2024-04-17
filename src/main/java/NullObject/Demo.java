package NullObject;


import java.lang.reflect.Proxy;

interface Log
{
	void info(String msg);

	void warn(String msg);
}

class ConsoleLog implements Log
{

	@Override
	public void info(String msg)
	{
		System.out.println(msg);
	}

	@Override
	public void warn(String msg)
	{
		System.out.println(msg);
	}
}

class BankAccount
{
	private Log log;

	private int balance;

	public BankAccount(Log log)
	{
		this.log = log;
	}

	public void deposit(int amount)
	{
		balance += amount;

		log.info("Deposited" + amount);
	}
}

final class NullLog implements Log
{

	@Override
	public void info(String msg)
	{

	}

	@Override
	public void warn(String msg)
	{

	}
}

public class Demo
{
	@SuppressWarnings("unchecked")
	public static <T> T noOP(Class<T> itf)
	{
		return (T) Proxy.newProxyInstance(itf.getClassLoader(),
				new Class<?>[]{itf},
				(proxy, method, args) ->
				{
					if (method.getReturnType().equals(Void.class))
					{
						return null;
					}
					else
					{
						return method.getReturnType().getConstructor().newInstance();
					}
				});
	}

	public static void main(String[] args)
	{

		/*
		NullLog consoleLog = new NullLog();
		BankAccount bankAccount = new BankAccount(consoleLog);

		bankAccount.deposit(100);
		 */
	}
}
