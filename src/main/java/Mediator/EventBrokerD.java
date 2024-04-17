/*package Mediator;


//rx

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

class EventBrokerX extends Observable<Integer>
{

	private List<Observer<? super Integer>> observers = new ArrayList<>();

	@Override
	protected void subscribeActual(@NonNull Observer<? super Integer> observer)
	{
		observers.add(observer);
	}

	public void publish(int n)
	{
		for (Observer<? super Integer> o : observers)
		{
			o.onNext(n);
		}
	}
}


class FootballPlayer
{
	private int scoredGoals = 0;

	private EventBrokerX broker;

	public String name;

	public FootballPlayer(EventBrokerX broker, String name)
	{
		this.broker = broker;
		this.name = name;
	}

	public void score()
	{
		broker.publish(++scoredGoals);
	}

}


class FootballCoach
{
	public FootballCoach(EventBrokerX broker)
	{
		broker.subscribe(new Observer<Integer>()
		{
			@Override
			public void onSubscribe(@NonNull Disposable d)
			{

			}

			@Override
			public void onNext(@NonNull Integer integer)
			{
				System.out.println(" hey you scored " + integer);
			}

			@Override
			public void onError(@NonNull Throwable e)
			{

			}

			@Override
			public void onComplete()
			{

			}
		});
	}
}

public class EventBrokerD
{
	public static void main(String[] args)
	{
		EventBrokerX broker = new EventBrokerX();

		FootballPlayer jones = new FootballPlayer(broker, "jones");

		FootballCoach coach = new FootballCoach(broker);


		jones.score();
		jones.score();
		jones.score();
	}
}


*/


package Mediator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

// Custom class to hold information about the goal event
class GoalEvent {
	public final FootballPlayer player;
	public final int goalsScored;

	public GoalEvent(FootballPlayer player, int goalsScored) {
		this.player = player;
		this.goalsScored = goalsScored;
	}
}

class EventBrokerX extends Observable<GoalEvent> {

	private List<Observer<? super GoalEvent>> observers = new ArrayList<>();

	@Override
	protected void subscribeActual(@NonNull Observer<? super GoalEvent> observer) {
		observers.add(observer);
	}

	public void publish(FootballPlayer player, int goalsScored) {
		GoalEvent goalEvent = new GoalEvent(player, goalsScored);
		for (Observer<? super GoalEvent> o : observers) {
			o.onNext(goalEvent);
		}
	}
}

class FootballPlayer {
	private int scoredGoals = 0;
	private EventBrokerX broker;
	public String name;

	public FootballPlayer(EventBrokerX broker, String name) {
		this.broker = broker;
		this.name = name;
	}

	public void score() {
		broker.publish(this, ++scoredGoals); // Publish both player object and goals scored
	}
}

class FootballCoach {
	public FootballCoach(EventBrokerX broker) {
		broker.subscribe(new Observer<GoalEvent>() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {}

			@Override
			public void onNext(@NonNull GoalEvent goalEvent) {
				System.out.println(goalEvent.player.name + " scored " + goalEvent.goalsScored + " goal(s)");
			}

			@Override
			public void onError(@NonNull Throwable e) {}

			@Override
			public void onComplete() {}
		});
	}
}

public class EventBrokerD {
	public static void main(String[] args) {
		EventBrokerX broker = new EventBrokerX();

		FootballPlayer jones = new FootballPlayer(broker, "Jones");
		FootballPlayer smith = new FootballPlayer(broker, "Smith");

		FootballCoach coach = new FootballCoach(broker);

		jones.score();
		jones.score();
		smith.score();
		jones.score();
		smith.score();
	}
}

