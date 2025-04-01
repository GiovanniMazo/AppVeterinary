package AppVeterinary;

class Seller {
    private String id, name, password;
    private int age;

    public Seller(String id, String name, int age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
}