package AppVeterinary;

import java.util.List;

public class Pet {
    private String id;
    private String name;
    private String ownerId; // ID del dueño
    private String status;   // Estado del animal (e.g., "En espera", "Atendido")

    // Constructor
    public Pet(String id, String name, String ownerId, String status) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.status = status;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
