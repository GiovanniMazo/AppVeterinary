
package AppVeterinary;

import java.util.UUID;

public class Invoice {
    private String id;
    private String userId;
    private double totalAmount;

    public Invoice(String userId, double totalAmount) {
        this.id = UUID.randomUUID().toString(); // Generar un ID único para la factura
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
