package Command;


import java.util.List;

class BankAccount
{
	private int balance;
	private int overDraftLimit = -500;

	public void deposit(int amount)
	{
		balance += amount;
		System.out.println("Deposited + " + amount + "balance is now" + balance);
	}

	public void withDraw(int amount)
	{
		if (balance - amount < overDraftLimit)
		{
			System.out.println("cant be lower than overdraft limit");
		}
		else
		{
			balance = balance - amount;
		}
	}

	@Override
	public String toString()
	{
		return "BankAccount{" +
				"balance=" + balance +
				'}';
	}
}


interface Command
{
	void call();
}

class BankAccountCommand implements Command
{
	private BankAccount account;

	public enum Action
	{
		DEPOSIT, WITHDRAW
	}

	private Action action;
	private int amount;


	public BankAccountCommand(BankAccount account, Action action, int amount)
	{
		this.account = account;
		this.action = action;
		this.amount = amount;
	}


	@Override
	public void call()
	{

		switch (action)
		{

			case DEPOSIT ->
			{
				account.deposit(amount);
			}
			case WITHDRAW ->
			{
				account.withDraw(amount);
			}

		}

	}
}


public class Demo
{

	public static void main(String[] args)
	{
		BankAccount acc = new BankAccount();
		System.out.println(acc);

		List<BankAccountCommand> bankAccountCommands = List.of(new BankAccountCommand(acc, BankAccountCommand.Action.DEPOSIT, 100), new BankAccountCommand(acc, BankAccountCommand.Action.WITHDRAW, 2000));

		for (int i = 0; i < bankAccountCommands.size(); i++)
		{
			bankAccountCommands.get(i).call();
			System.out.println(bankAccountCommands);
		}

	}
}
