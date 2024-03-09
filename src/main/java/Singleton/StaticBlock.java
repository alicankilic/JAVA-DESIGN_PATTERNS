package Singleton;// Java Program to Illustrate Execution of Static Block
// Before Constructors

// Class 1
// Helper class
class Test {

    // Case 1: Static variable
    static int i;
    // Case 2: Non-static variable
    int j;

    // Case 3: Static blocks
    static
    {
        i = 10;
        System.out.println("static block called ");
    }

    // Constructor calling
    Test() { System.out.println("Constructor called"); }
}

// Class 2
// Main class
class GFG {

    // Main driver method
    public static void main(String[] args) {
        Test t1 = new Test();

        //TODO STATIC BLOCK IS CALLED ONLY ONCE WHEN WE INTRODUCE OUR CLASS TO MEMORY

    }
}
