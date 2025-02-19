class Studentexp4 {
    String name;
    int age;

    // Default constructor
    Studentexp4() {
        this.name = "Default Name";
        this.age = 18;
    }

    // Parameterized constructor
    Studentexp4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    // Main method to execute the class
    public static void main(String[] args) {
        Studentexp4 s1 = new Studentexp4();
        Studentexp4 s2 = new Studentexp4("Gaurav Singh", 20);
        
        s1.display();
        s2.display();
    }
}
