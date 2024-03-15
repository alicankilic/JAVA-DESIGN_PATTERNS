package Factory;


import java.util.logging.Logger;

interface ICar
{
	void checkEngine();

	void startEngine();
}

interface IMoto
{
	void checkMoto();

	void startMoto();
}


class BmwMoto implements IMoto
{
	Logger logger = Logger.getLogger(getClass().getName());


	@Override
	public void checkMoto()
	{
		logger.info("bmw moto");

	}

	@Override
	public void startMoto()
	{
		logger.info("bmw moto BRRRRRRRRR");

	}
}

class HondaMoto implements IMoto
{
	Logger logger = Logger.getLogger(getClass().getName());


	@Override
	public void checkMoto()
	{
		logger.info("audi moto");

	}

	@Override
	public void startMoto()
	{
		logger.info("audi moto brrrrrrrrrrrrrrrrrrrRRR");
	}
}

interface ICarMotoFactory
{
	ICar createCar();

	IMoto createMoto();


}

class HondaFactory implements ICarMotoFactory
{

	@Override
	public ICar createCar()
	{
		return new Honda();
	}

	@Override
	public IMoto createMoto()
	{
		return new HondaMoto();
	}
}

class BmwFactory implements ICarMotoFactory
{

	@Override
	public ICar createCar()
	{
		return new Bmw();
	}

	@Override
	public IMoto createMoto()
	{
		return new BmwMoto();
	}
}


class Bmw implements ICar
{
	Logger logger = Logger.getLogger(getClass().getName());


	@Override
	public void checkEngine()
	{
		logger.info("bmw");
	}

	@Override
	public void startEngine()
	{
		logger.info("bmw brr");
	}
}

class Honda implements ICar
{

	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void checkEngine()
	{
		logger.info("honda");

	}

	@Override
	public void startEngine()
	{
		logger.info("honda brr");
	}
}

public class AbsCheck
{
	public static void main(String[] args)
	{
		ICarMotoFactory bmw = new BmwFactory();
		ICarMotoFactory honda = new HondaFactory();

		ICar bmwR = bmw.createCar();
		ICar hondaR = honda.createCar();

		IMoto bmwRR = bmw.createMoto();

		IMoto hondaRR = honda.createMoto();

		bmwR.startEngine();
		bmwRR.startMoto();


	}
}
