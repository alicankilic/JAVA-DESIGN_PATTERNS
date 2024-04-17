package Memento;


//TODO MEMENTO SHOULD BE READ ONLY STRUCTURE.
class Memento
{
	public int balance;

	public Memento(int balance)
	{
		this.balance = balance;
	}
}


class BankAccount
{
	private int balance;

	public BankAccount(int balance)
	{
		this.balance = balance;
	}

	//TODO INSTEAD OF MAKING IT RETURN VOID
	//TODO WE ARE CALLING IT TO RETURN *MEMENTO*
	public Memento deposit(int amount)
	{
		balance = balance + amount;
		return new Memento(balance);
	}

	public void restore(Memento m)
	{
		balance = m.balance;
	}


	@Override
	public String toString()
	{
		return "BankAccount{" +
				"balance=" + balance +
				'}';
	}


}


public class Demo
{
	public static void main(String[] args)
	{
		BankAccount bk = new BankAccount(100);
		Memento deposit = bk.deposit(50);
		Memento deposit2 = bk.deposit(20);

		System.out.println(bk);


		bk.restore(deposit);
		System.out.println(bk);

		bk.restore(deposit2);
		System.out.println(bk);

	}
}
