package Mediator;


//CHAT ROOM

// BUTUN ISLEMLERI MEDIATOR UZERINDEN YAPIYORLAR
// BIRBIRLERINE DIREK REFERANS YOK

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

class Person
{
	public String name;
	public ChatRoom room;
	private List<String> chatLog = new ArrayList<>();

	public Person(String name)
	{
		this.name = name;
	}

	public void receive(String sender, String message)
	{
		String s = sender + " : '" + message + "'";
		System.out.println("[" + name + "'s chat session]" + s + "\n");
		chatLog.add(s);
	}

	public void say(String message)
	{
		room.broadCast(name, message);
	}

	public void privateMessage(String toWho, String message)
	{
		room.messageto(name, toWho, message);
	}
}

class ChatRoom
{

	private List<Person> people = new ArrayList<>();


	public void join(Person p)
	{
		String joinMessage = p.name + "joins the room \n";
		broadCast("room", joinMessage);
		p.room = this;
		people.add(p);
	}

	public void messageto(String name, String toWho, String message)
	{
		people.stream().filter(p -> p.name.equals(toWho)).findFirst().ifPresent(p -> p.receive(name, message));

	}

	public void broadCast(String source, String message)
	{
		for (Person person : people)
		{
			if (!person.name.equals(source))
			{
				person.receive(source, message);
			}
		}
	}
}


public class Demo
{

	public static void main(String[] args)
	{
		ChatRoom room = new ChatRoom();
		Person john = new Person("john");
		Person ahmet = new Person("ahmet");

		room.join(john);
		room.join(ahmet);


		john.say("hi room");
		ahmet.say("nabersiniz");


		Person draken = new Person("draken");

		room.join(draken);

		draken.privateMessage("ahmet","naber");

	}
}
