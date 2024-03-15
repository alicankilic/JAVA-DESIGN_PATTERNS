package Singleton;

import java.util.concurrent.atomic.AtomicReference;

public class StaticLazySynchronized
{
}



class MySingleton {
	// Private static instance of AtomicReference<MySingleton>
	private static final AtomicReference<MySingleton> INSTANCE = new AtomicReference<>();

	// Private constructor to prevent instantiation
	private MySingleton() {
		// Initialization code here
		System.out.println("Singleton instance created");
	}

	// Public static method to get the singleton instance
	public static MySingleton getInstance() {
		// Double-checked locking for thread safety and laziness
		if (INSTANCE.get() == null) {
			synchronized (MySingleton.class) {
				if (INSTANCE.get() == null) {
					INSTANCE.set(new MySingleton());
				}
			}
		}
		return INSTANCE.get();
	}

	// Add other methods and properties as needed
	public void someMethod() {
		System.out.println("Some method called");
	}

	public static void main(String[] args) {
		// Accessing the singleton instance
		MySingleton.getInstance().someMethod();
		MySingleton.getInstance().someMethod();

		// Trying to create another instance, which won't work
		// MySingleton anotherInstance = new MySingleton(); // This line will give a compiler error
	}
}







