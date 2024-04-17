package Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


interface Element
{
	int eval();
}


class Integer implements Element
{
	private int value;

	public Integer(int value)
	{
		this.value = value;
	}

	@Override
	public int eval()
	{
		return value;
	}
}


class BinaryOperation implements Element
{

	public enum Type
	{
		ADDITION,
		SUBSTRACTION
	}

	public Type type;
	public Element left, right;


	@Override
	public int eval()
	{
		switch (type)
		{
			case ADDITION ->
			{
				return left.eval() + right.eval();
			}
			case SUBSTRACTION ->
			{
				return left.eval() - right.eval();
			}
		}
		return 0;
	}
}


class Token
{
	public enum Type
	{
		INTEGER,
		PLUS,
		MINUS,
		LPAREN,
		RPAREN
	}

	public Type type;

	public String text;

	public Token(Type type, String text)
	{
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString()
	{
		return "`" + text + "`";
	}
}


public class Demo
{

	static List<Token> lex(String inp)
	{
		ArrayList<Token> tokens = new ArrayList<>();

		for (int i = 0; i < inp.length(); i++)
		{
			switch (inp.charAt(i))
			{
				case '+':
					tokens.add(new Token(Token.Type.PLUS, "+"));
					break;
				case '-':
					tokens.add(new Token(Token.Type.MINUS, "-"));
					break;
				case '(':
					tokens.add(new Token(Token.Type.LPAREN, "("));
					break;
				case ')':
					tokens.add(new Token(Token.Type.RPAREN, ")"));
					break;
				default:
					StringBuilder stringBuilder = new StringBuilder("" + inp.charAt(i));
					for (int j = i + 1; j < inp.length(); ++j)
					{
						if (Character.isDigit(inp.charAt(j)))
						{
							stringBuilder.append(inp.charAt(j));
							++i;
						}
						else
						{
							tokens.add(new Token(Token.Type.INTEGER, stringBuilder.toString()));
							break;
						}
					}
					break;
			}
		}
		return tokens;
	}

	static Element parse(List<Token> tokens)
	{
		BinaryOperation result = new BinaryOperation();
		boolean haveLHS = false;
		for (int i = 0; i < tokens.size(); i++)
		{
			Token token = tokens.get(i);
			switch (token.type)
			{
				case INTEGER ->
				{

					Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
					if (!haveLHS)
					{
						result.left = integer;
						haveLHS = true;
					}
					else
					{
						result.right = integer;
					}
				}
				case PLUS ->
				{
					result.type = BinaryOperation.Type.ADDITION;
				}
				case MINUS ->
				{
					result.type = BinaryOperation.Type.SUBSTRACTION;
				}
				case LPAREN ->
				{
					int j = 0;
					for (; j < tokens.size(); j++)
					{
						if (tokens.get(i).type == Token.Type.RPAREN)
						{
							break;
						}
						List<Token> subExpression = tokens.stream().skip(i+1).limit(j-i-1).collect(Collectors.toList());
						Element element = parse(subExpression);
						if (!haveLHS)
						{
							result.left = element;
							haveLHS = true;

						}
						else result.right = element;
						i = j;
					}
				}

			}
		}
		return result;
	}


	public static void main(String[] args)
	{
		String inp = "(13+4)-(20+1)";
		List<Token> tokenList = lex(inp);
		System.out.println(tokenList.stream().map(t -> t.toString()).collect(Collectors.joining("\t")));

		Element parsed = parse(tokenList);
		System.out.println(inp + " = " + parsed.eval());
	}
}
