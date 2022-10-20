package function;

public class RealPerson {
    protected String address;
    protected String firstName;
    protected String lastName;
    protected String zipCode;
    protected String email;
    protected String bankAccount;

    public RealPerson(String address,
                      String firstName,
                      String lastName,
                      String zipCode,
                      String email,
                      String bankAccount) {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    public void printStuff() {
        System.out.println(address);
    }

    public int addStuff(int a,
                        int b) {
        return a + b;
    }
}
