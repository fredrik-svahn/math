package function;

public class Person {

    private final RealPerson realPerson;

    public Person(String address,
                  String firstName,
                  String lastName,
                  String zipCode,
                  String email,
                  String bankAccount) {
        realPerson = new RealPerson(address, firstName, lastName, zipCode, email, bankAccount);
    }

    public Person(String address,
                  String firstName) {
        this(address, firstName, null, null, null, null);
    }

    public void printStuff() {
        realPerson.printStuff();
    }

    public int addStuff(int a,
                        int b) {
        return realPerson.addStuff(a, b);
    }
}
