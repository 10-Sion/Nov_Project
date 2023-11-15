package jun;
public class Pharmacy {
    private String name;
    private String address;

    public Pharmacy(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
