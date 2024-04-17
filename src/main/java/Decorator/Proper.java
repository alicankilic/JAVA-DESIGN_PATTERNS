package Decorator;


import java.util.function.Supplier;

interface Shape
{
	String info();
}

class Circle implements Shape
{

	private float radius;

	public Circle(float radius)
	{
		this.radius = radius;
	}

	void resize(float factor)
	{
		radius *= factor;
	}

	public Circle()
	{
	}

	@Override
	public String info()
	{
		return "a circle of radius" + radius;
	}
}

class Square implements Shape
{

	private float side;

	public Square()
	{

	}

	public Square(float side)
	{
		this.side = side;
	}

	@Override
	public String info()
	{
		return "side of" + this.side;
	}
}

/*
class ColoredShape implements Shape
{

	private Shape shape;
	private String color;

	public ColoredShape(Shape shape, String color)
	{
		this.shape = shape;
		this.color = color;
	}

	@Override
	public String info()
	{
		return shape.info() + "has the color" + color;
	}
}


class TransparentShape implements Shape
{

	private Shape shape;
	private int transparency;

	public TransparentShape(Shape shape, int transparency)
	{
		this.shape = shape;
		this.transparency = transparency;
	}

	@Override
	public String info()
	{
		return shape.info() + " has " + transparency + "% transparency";
	}
}

 */


class ColoredShape<T extends Shape> implements Shape
{

	private Shape shape;
	private String color;


	public ColoredShape(Supplier<? extends T> constructor, String clr)
	{
		this.color = clr;
		this.shape = constructor.get();
	}

	@Override
	public String info()
	{
		return shape.info() + "has the color " + this.color;
	}
}


class TransparentShape<T extends Shape> implements Shape
{

	private int transparency;

	private Shape shape;

	public TransparentShape(Supplier<? extends T> supplier, int Transparency)
	{
		this.transparency = Transparency;
		this.shape = supplier.get();
	}

	@Override
	public String info()
	{
		return "this has" + "percentage of" + transparency + "and this shape" + shape.info();
	}
}


public class Proper
{
	public static void main(String[] args)
	{

		ColoredShape<Square> blueSquare = new ColoredShape<>(() -> new Square(5), "Blue");
		System.out.println(blueSquare.info());

		TransparentShape<Circle> circTransparent = new TransparentShape<>(() -> new Circle(20), 123);
		System.out.println(circTransparent.info());





	/*	Circle circle = new Circle(10);
		System.out.println(circle.info());

	 */
/*
		ColoredShape shapeB = new ColoredShape(new Square(20), "blue");

		System.out.println(shapeB.info());

		TransparentShape greenCirc = new TransparentShape(new ColoredShape(new Circle(5), "green"), 50);
		System.out.println(greenCirc.info());

 */


	}
}
