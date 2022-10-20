package function;

public class PersonBuilder {
    private String address;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String email;
    private String bankAccount;

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public PersonBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonBuilder setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public Person createPerson() {
        return new Person(address, firstName, lastName, zipCode, email, bankAccount);
    }
}