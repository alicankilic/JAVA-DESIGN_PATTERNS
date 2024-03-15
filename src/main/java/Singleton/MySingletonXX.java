package Singleton;

import java.util.concurrent.atomic.AtomicReference;



interface MyDependency {
	void doSomething();
}

class MyDependencyImpl implements MyDependency {
	@Override
	public void doSomething() {
		System.out.println("Doing something in MyDependencyImpl");
	}
}

class MyDependencyImpl2 implements MyDependency{

	@Override
	public void doSomething()
	{
		System.out.println("ahmet");
	}
}


class MySingletonXX
{
	// Private static instance of AtomicReference<MySingleton>
	private static final AtomicReference<MySingletonXX> INSTANCE = new AtomicReference<>();

	private final MyDependency dependency;

	// Private constructor to prevent instantiation
	private MySingletonXX(MyDependency dependency)
	{
		this.dependency = dependency;
		// Initialization code here
		System.out.println("Singleton instance created");
	}

	// Public static method to get the singleton instance
	public static MySingletonXX getInstance(MyDependency mc)
	{
		// Double-checked locking for thread safety and laziness
		if (INSTANCE.get() == null)
		{
			synchronized (MySingleton.class)
			{
				if (INSTANCE.get() == null)
				{
					INSTANCE.set(new MySingletonXX(mc));
				}
			}
		}
		return INSTANCE.get();
	}

	// Method to access the dependency
	public MyDependency getDependency()
	{
		return dependency;
	}

	// Add other methods and properties as needed
	public void someMethod()
	{
		System.out.println("Some method called");
	}

	public static void main(String[] args)
	{

		MySingletonXX.getInstance(new MyDependencyImpl2()).someMethod();

		MySingletonXX.getInstance(new MyDependencyImpl()).getDependency().doSomething();

		// Trying to create another instance, which won't work
		// MySingleton anotherInstance = new MySingleton(); // This line will give a compiler error
	}
}
