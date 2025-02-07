class Parent {
    void show() {
        System.out.println("This is the parent class method.");
    }
}

class Child extends Parent {
    @Override
    void show() {
        System.out.println("This is the child class method (Overridden).");
    }
}

public class OverridingExample {
    public static void main(String[] args) {
        Parent obj = new Child(); // Upcasting
        Parent obj1 = new Parent();
        Child obj2 = new Child();
        // Child obj3 = new Parent(); // Downcasting

        obj1.show();
        obj.show(); // Calls the overridden method in the child class
        obj2.show();

        // obj3.show(); //Error - incompatible types: Parent cannot be converted to
        // Child
    }
}