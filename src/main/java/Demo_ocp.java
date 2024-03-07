import java.util.List;
import java.util.stream.Stream;

//TODO OPENCLOSE PRINCIPLE


interface  Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}


class BetterFilter implements Filter<Demo_ocp.Product> {

    @Override
    public Stream<Demo_ocp.Product> filter(List<Demo_ocp.Product> items, Specification<Demo_ocp.Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}


class AndSpecification<T> implements Specification<T> {

    private Specification<T> first, second;

    AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}


class ColorSpecification implements Specification<Demo_ocp.Product> {

    private Demo_ocp.Color color;

    ColorSpecification(Demo_ocp.Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Demo_ocp.Product item) {
        return item.color == color;
    }
}


class SizeSpecification implements Specification<Demo_ocp.Product> {

    private Demo_ocp.Size size;

    SizeSpecification(Demo_ocp.Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Demo_ocp.Product item) {
        return item.size == size;
    }
}

public class Demo_ocp {
    enum Color {
        RED, GREEN, BLUE
    }

    enum Size {
        SMALL, MEDIUM, LARGE, HUGE
    }

    static class Product {
        public String name;
        public Color color;

        public Size size;

        public Product(String name, Color color, Size size) {
            this.name = name;
            this.color = color;
            this.size = size;
        }
    }

    static class ProductFilter {
        public Stream<Product> filterByColor(List<Product> products, Color color) {
            return products.stream().filter(p -> p.color == color);
        }

        public Stream<Product> filterBySize(List<Product> products, Size size) {
            return products.stream().filter(p -> p.size == size);
        }

        public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
            return products.stream().filter(p -> p.size == size && p.color == color);
        }


    }

}

class Demo {
    public static void main(String[] args) {
        Demo_ocp.Product apple = new Demo_ocp.Product("APPLE", Demo_ocp.Color.RED, Demo_ocp.Size.HUGE);
        Demo_ocp.Product tree = new Demo_ocp.Product("tree", Demo_ocp.Color.BLUE, Demo_ocp.Size.MEDIUM);
        Demo_ocp.Product house = new Demo_ocp.Product("house", Demo_ocp.Color.GREEN, Demo_ocp.Size.LARGE);

        List<Demo_ocp.Product> prds = List.of(apple, tree, house);

        Demo_ocp.ProductFilter pf = new Demo_ocp.ProductFilter();
        System.out.println("green products (old):");
        Stream<Demo_ocp.Product> productStream = pf.filterByColor(prds, Demo_ocp.Color.RED);
        System.out.println(productStream);

        BetterFilter bf = new BetterFilter();
        System.out.println("green products (new):");
        bf.filter(prds, new ColorSpecification(Demo_ocp.Color.GREEN));

        System.out.println("large blue items:");
        bf.filter(prds, new AndSpecification<>(new ColorSpecification(Demo_ocp.Color.RED), new SizeSpecification(Demo_ocp.Size.HUGE)));


    }
}




