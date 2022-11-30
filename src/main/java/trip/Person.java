package trip;

import java.util.Objects;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private int age;

    public Person() {
    }

    public Person(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " age: " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Person p = (Person) o;
        boolean idEquals = (this.id == null && p.id == null)
                || (this.id != null && this.id.equals(p.id));
        boolean firstNameEquals = (this.firstName == null && p.firstName == null)
                || (this.firstName != null && this.firstName.equals(p.firstName));
        boolean lastNameEquals = (this.lastName == null && p.lastName == null)
                || (this.lastName != null && this.lastName.equals(p.lastName));
        boolean ageEquals = this.age == p.age;
        return idEquals && firstNameEquals && lastNameEquals && ageEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age);
    }
}
