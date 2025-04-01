
package AppVeterinary;

public class User {
    private String id;
    private String name;
    private int age;
    private String role;  // "Veterinarian", "Seller", "Owner"
    private String password;

    public User(String id, String name, int age, String role, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
        this.password = password;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
