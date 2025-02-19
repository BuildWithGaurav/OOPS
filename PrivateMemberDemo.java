class Superclass {
    protected int privateVar = 10;   //access modifier ?

    public int getPrivateVar() {
        return privateVar;
    }
}

class Subclass extends Superclass {
    void display() {
        System.out.println("Accessing private variable through method: " + getPrivateVar());
    }
}

public class PrivateMemberDemo {
    public static void main(String[] args) {
        var obj = new Subclass();  
        obj.display();
    }
}
