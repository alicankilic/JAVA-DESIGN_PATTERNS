import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Depinv {

}

enum RelationShip {
    PARENT, CHILD, SIBLING
}


class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

// low level because its doing processes
class RelationShips implements RelationShipBrowser {
    public List<Triplet<Person, RelationShip, Person>> getRelations() {
        return relations;
    }

    private List<Triplet<Person, RelationShip, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, RelationShip.PARENT, child));
        relations.add(new Triplet<>(child, RelationShip.CHILD, parent));
    }

    public static void main(String[] args) {

    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        List<Person> p1 = new ArrayList<>();
        relations.stream().filter(ch -> Objects.equals(ch.getValue0().name, name)).forEach(x -> p1.add(x.getValue2()));
        return p1;

    }
}

//high level taking low level code
//kullanirken sadece output vericek
// yukaridaki ise helperwise
//TODO YOU SHOULD DEPEND ON ABSTRACTION EG. INTERFACES OR ABSTRACT CLASSES
class Research {
    public static void Research(RelationShipBrowser relationShip) {
        //TODO SEARCH HAPPENS IN LOW LEVEL-MODEL
        relationShip.findAllChildrenOf("john").forEach(x -> System.out.println(x.name));
    }
}


interface RelationShipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class DM {
    public static void main(String[] args) {
        Person p1 = new Person("john");
        Person p2 = new Person("jonathan");
        Person p3 = new Person("edgar");

        RelationShips rl = new RelationShips();
        rl.addParentAndChild(p1, p2);
        rl.addParentAndChild(p1, p3);

        Research.Research(rl);


    }
}