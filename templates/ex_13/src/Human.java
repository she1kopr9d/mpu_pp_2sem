public class Human implements Comparable<Human> {
    private String lastName;
    private String firstName;
    private String secondName;
    private int age;

    public Human(String lastName, String firstName, String secondName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    @Override
    public int compareTo(Human other) {
        int cmp = this.lastName.compareTo(other.lastName);
        if (cmp != 0) return cmp;
        cmp = this.firstName.compareTo(other.firstName);
        if (cmp != 0) return cmp;
        cmp = this.secondName.compareTo(other.secondName);
        if (cmp != 0) return cmp;
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + secondName + " (" + age + ")";
    }
}