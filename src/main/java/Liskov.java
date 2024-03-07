public class Liskov {

     static class Rectangel {
        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        protected int width, height;

        public Rectangel() {

        }

        public Rectangel(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "RECTANGE" + width + height;
        }

        public int getArea() {
            return width * height;
        }


    }


    static class Square extends Rectangel {
        public Square() {

        }


        public Square(int size) {
            width = height = size;
        }

        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            super.setHeight(width);
        }

        @Override
        public void setHeight(int height) {
            super.setHeight(height);
            super.setWidth(height);
        }
    }

}

class DemoL {

    static void userIt(Liskov.Rectangel r) {
        int width = r.getWidth();
        r.setHeight(10);

        // are = width*10
        System.out.println("expect area of " + width * 10 + "got" + r.getArea());
    }

    public static void main(String[] args) {
        Liskov.Rectangel rc = new Liskov.Rectangel(2,3);
        userIt(rc);

        Liskov.Square sqr = new Liskov.Square();
        sqr.setWidth(5);
        userIt(sqr);


    }

}
