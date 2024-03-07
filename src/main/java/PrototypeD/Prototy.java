package PrototypeD;


import java.util.Arrays;

class Adress {
    public String streetName;

    public int houseNumber;

    public Adress(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

class Person {
    public String[] names;
    public Adress addres;

    public Person(String[] names, Adress adress) {
        this.names = names;
        this.addres = adress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", addres=" + addres +
                '}';
    }


}

class DX {
    public static void main(String[] args) {
        Person brixton = new Person(new String[]{"john", "smith"}, new Adress("brixton", 10));
        

    }
}


public class Prototy {
}
