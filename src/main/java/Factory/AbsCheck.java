package Factory;


interface ICar {
    void checkEngine();

    void startEngine();
}

interface IMoto {
    void checkMoto();

    void startMoto();
}


class BmwMoto implements IMoto {

    @Override
    public void checkMoto() {
        System.out.println("bmw moto");

    }

    @Override
    public void startMoto() {
        System.out.println("bmw moto BRRRRRRRRR");

    }
}

class HondaMoto implements IMoto {

    @Override
    public void checkMoto() {
        System.out.println("audi moto");

    }

    @Override
    public void startMoto() {
        System.out.println("audi moto brrrrrrrrrrrrrrrrrrrRRR");
    }
}

interface ICarMotoFactory {
    ICar createCar();

    IMoto createMoto();


}

class HondaFactory implements ICarMotoFactory {

    @Override
    public ICar createCar() {
        return new Honda();
    }

    @Override
    public IMoto createMoto() {
        return new HondaMoto();
    }
}

class BmwFactory implements ICarMotoFactory {

    @Override
    public ICar createCar() {
        return new Bmw();
    }

    @Override
    public IMoto createMoto() {
        return new BmwMoto();
    }
}


class Bmw implements ICar {

    @Override
    public void checkEngine() {
        System.out.println("bmw");
    }

    @Override
    public void startEngine() {
        System.out.println("bmw brr");
    }
}

class Honda implements ICar {

    @Override
    public void checkEngine() {
        System.out.println("honda");

    }

    @Override
    public void startEngine() {
        System.out.println("honda brr");
    }
}

public class AbsCheck {
    public static void main(String[] args) {
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
