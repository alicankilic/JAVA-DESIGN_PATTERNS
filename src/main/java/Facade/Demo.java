package Facade;


import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

class Buffer
{
	private char[] characters;
	private int lineWidth;

	public Buffer(int lineHeight, int lineWidth)
	{
		this.lineWidth = lineWidth;
		characters = new char[lineHeight * lineWidth];
	}

	public char chartAt(int x, int y)
	{
		return characters[y * lineWidth + x];
	}


}

class ViewPort
{
	private final Buffer bfr;
	private final int width;
	private final int height;
	private final int offsetY;
	private final int offsetX;


	public ViewPort(Buffer bfr, int width, int height, int offsetX, int offsetY)
	{
		this.bfr = bfr;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public char charAt(int x, int y)
	{
		return bfr.chartAt(x + offsetX, y + offsetY);
	}
}

class Console
{
	private List<ViewPort> viewPortList = new ArrayList<>();
	int width, height;


	public static Console newConsole(int width, int height)
	{
		Buffer buffer = new Buffer(width, height);
		ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
		Console cs = new Console(width, height);
		cs.addViewPort(viewPort);
		return cs;
	}

	public Console(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public void addViewPort(ViewPort vp)
	{
		viewPortList.add(vp);
	}

	public void render()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				for (ViewPort vp : viewPortList)
				{
					System.out.println(vp.charAt(x, y));
				}
				System.out.println();
			}
		}
	}
}

public class Demo
{
	public static void main(String[] args)
	{
		Console console = Console.newConsole(30, 20);
		console.render();
	}

}
