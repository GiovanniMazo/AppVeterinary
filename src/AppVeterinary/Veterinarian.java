package AppVeterinary;

class Veterinarian {
    private String id, name, password;
    private int age;
    
    public Veterinarian(String id, String name, int age, String password) {
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