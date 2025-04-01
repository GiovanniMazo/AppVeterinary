package AppVeterinary;

class Owner {
    private String id, name;
    private int age;

    public Owner(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}