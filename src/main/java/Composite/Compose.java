package Composite;

import org.checkerframework.checker.units.qual.C;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compose
{

	public Compose()
	{
	}
}


class GraphicObject
{

	public GraphicObject()
	{
	}

	protected String name = "Group";
	public String color;

	public List<GraphicObject> children = new ArrayList<>();

	public GraphicObject(String name)
	{
		this.name = name;
	}


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	private void print(StringBuilder stringBuilder, int depth)
	{
		stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
				.append(depth > 0 ? " " : "")
				.append(((color == null || color.isEmpty() ? "" : color + " ")))
				.append(getName())
				.append(System.lineSeparator());
		for (GraphicObject child : children)
		{
			child.print(stringBuilder, depth + 1);
		}
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		print(stringBuilder, 0);
		return stringBuilder.toString();
	}
}

class Circle extends GraphicObject
{
	public Circle(String color)
	{
		name = "Circle";
		this.color = color;
	}
}

class Square extends GraphicObject
{
	public Square(String color)
	{
		name = "Square";
		this.color = color;
	}
}

class Demo
{
	public static void main(String[] args)
	{
		GraphicObject graphicObject = new GraphicObject();
		graphicObject.setName("cizildi");
		graphicObject.children.add(new Square("red"));
		graphicObject.children.add(new Square("blue"));
		graphicObject.children.add(new Circle("kirmizi"));

//TODO KRITIK OLAN SEY LIST IN HEM SQUARE VE CIRCLERARI ICERMESI
		//TODO HEMDE GRAPHICOBJECT(CONTAINER) I ICERMESI


		GraphicObject rk = new GraphicObject();
		rk.children.add(new Circle("mavi"));
		rk.children.add(new Square("mavi"));
		graphicObject.children.add(rk);

		System.out.println(graphicObject);
	}
}