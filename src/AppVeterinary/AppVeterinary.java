package AppVeterinary;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Main Application Class

public class AppVeterinary extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private static List<User> users = new ArrayList<>();
    private static List<Invoice> invoices = new ArrayList<>();
    private static List<Pet> pets = new ArrayList<>();
    private DefaultTableModel petTableModel;
    private JTable petTable;
    
    public AppVeterinary() {
        setTitle("Veterinary Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Solicitar el registro del administrador si no existe
        ensureAdminExists();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        
        JButton loginButton = new JButton("Login");
        JButton registerUserButton = new JButton("Register User");
        JButton registerPetButton = new JButton("Register Animal");
        JButton generateInvoiceButton = new JButton("Generate Invoice");
        JButton viewInvoiceHistoryButton = new JButton("View Invoice History");
        JButton viewPetHistoryButton = new JButton("View Pet History");
        JButton refreshButton = new JButton("Refresh Table");
        JButton exitButton = new JButton("Exit");
        JButton updatePetStatusButton = new JButton("Update Pet Status");
        
        panel.add(loginButton);
        panel.add(registerUserButton);
        panel.add(registerPetButton);
        panel.add(generateInvoiceButton);
        panel.add(viewInvoiceHistoryButton);
        panel.add(viewPetHistoryButton);
        panel.add(refreshButton);
        panel.add(updatePetStatusButton);
        panel.add(exitButton);
        
        add(panel, BorderLayout.WEST);
        
        // Tabla de usuarios
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Role"}, 0);
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        
        // Tabla de animales
        petTableModel = new DefaultTableModel(new String[]{"Pet ID", "Name", "Owner ID", "Status"}, 0);
        petTable = new JTable(petTableModel);
        add(new JScrollPane(petTable), BorderLayout.EAST);
        
        loginButton.addActionListener(e -> loginUser());
        registerUserButton.addActionListener(e -> registerUser());
        registerPetButton.addActionListener(e -> registerPet());
        generateInvoiceButton.addActionListener(e -> generateInvoice());
        viewInvoiceHistoryButton.addActionListener(e -> viewInvoiceHistory());
        viewPetHistoryButton.addActionListener(e -> viewPetHistory());
        updatePetStatusButton.addActionListener(e -> updatePetStatus());
        refreshButton.addActionListener(e -> refreshUserTable());
        exitButton.addActionListener(e -> System.exit(0));
        
        refreshUserTable();
        refreshPetTable();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new AppVeterinary();
    }
    
    private void ensureAdminExists() {
        if (users.isEmpty() || users.stream().noneMatch(user -> user.getRole().equals("Administrator"))) {
            String adminId = JOptionPane.showInputDialog("Enter Administrator ID:");
            String adminName = JOptionPane.showInputDialog("Enter Administrator Name:");
            int adminAge = Integer.parseInt(JOptionPane.showInputDialog("Enter Administrator Age:"));
            String adminPassword = JOptionPane.showInputDialog("Enter Administrator Password:");
            
            users.add(new User(adminId, adminName, adminAge, "Administrator", adminPassword));
            JOptionPane.showMessageDialog(this, "Administrator Created Successfully. You can now login.");
        }
    }
    
    private void refreshUserTable() {
        tableModel.setRowCount(0);
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getAge(), user.getRole()});
        }
    }
    
    private void refreshPetTable() {
        petTableModel.setRowCount(0);
        for (Pet pet : pets) {
            petTableModel.addRow(new Object[]{pet.getId(), pet.getName(), pet.getOwnerId(), pet.getStatus()});
        }
    }
    
    private void loginUser() {
        String id = JOptionPane.showInputDialog("Enter ID:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful as " + user.getRole());
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void registerUser() {
        String id = JOptionPane.showInputDialog("Enter ID:");
        String name = JOptionPane.showInputDialog("Enter Name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
        String password = JOptionPane.showInputDialog("Enter Password:");
        String[] roles = {"Veterinarian", "Seller", "Owner"};
        String role = (String) JOptionPane.showInputDialog(this, "Select Role:", "Role Selection", JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        
        users.add(new User(id, name, age, role, password));
        refreshUserTable();
        JOptionPane.showMessageDialog(this, "User Registered Successfully");
    }

    private void registerPet() {
        String ownerId = JOptionPane.showInputDialog("Enter Owner ID:");

        // se va erificar si el dueño existe y tiene el rol de "Owner"
        User owner = null;
        for (User user : users) {
            if (user.getId().equals(ownerId) && user.getRole().equals("Owner")) {
                owner = user;
                break;
            }
        }

        // Si el dueño no existe o no tiene el rol de "Owner"
        if (owner == null) {
            JOptionPane.showMessageDialog(this, "Owner not found or not registered as an Owner. Please register the owner first.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // No registrar el animal si el dueño no existe o no tiene el rol correcto
        }

        // Si el dueño existe y tiene el rol adecuado, permitir el registro del animal
        
        String petId = JOptionPane.showInputDialog("Enter Pet ID:");
        String petName = JOptionPane.showInputDialog("Enter Pet Name:");
        
        // El estado inicial del animal se encuentra  "En espera"
        Pet newPet = new Pet(petId, petName, ownerId, "En espera");
        
        // Se añadirá al listado de mascotas
        pets.add(newPet);
        
        JOptionPane.showMessageDialog(this, "Pet Registered Successfully");
        refreshPetTable();
    }

    private void generateInvoice() {
        String userId = JOptionPane.showInputDialog("Enter User ID (who is buying):");
        double totalAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Total Amount:"));
        String invoiceId = UUID.randomUUID().toString();
        
        // se va crear una nueva factura
        Invoice newInvoice = new Invoice(userId, totalAmount);
        
        // Añadir la factura a la lista de facturas
        invoices.add(newInvoice);
        
        // Mostrar mensaje confirmando la creación
        JOptionPane.showMessageDialog(this, "Invoice Generated Successfully\n" +
                "Invoice ID: " + invoiceId + "\nTotal: $" + totalAmount);
    }

    private void viewInvoiceHistory() {
        DefaultTableModel invoiceTableModel = new DefaultTableModel(new String[]{"Invoice ID", "User ID", "Total Amount"}, 0);
        
        for (Invoice invoice : invoices) {
            invoiceTableModel.addRow(new Object[]{invoice.getId(), invoice.getUserId(), invoice.getTotalAmount()});
        }

        JTable invoiceTable = new JTable(invoiceTableModel);
        JOptionPane.showMessageDialog(this, new JScrollPane(invoiceTable), "Invoice History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewPetHistory() {
        String petId = JOptionPane.showInputDialog("Enter Pet ID:");
        Pet selectedPet = null;

        for (Pet pet : pets) {
            if (pet.getId().equals(petId)) {
                selectedPet = pet;
                break;
            }
        }

        if (selectedPet != null) {
            StringBuilder history = new StringBuilder("Pet History for " + selectedPet.getName() + ":\n");
            // Se va mostrar la historia médica (por ejmplo, registros de atención)
            
            history.append("Status: ").append(selectedPet.getStatus()).append("\n");
            JOptionPane.showMessageDialog(this, history.toString(), "Pet Medical History", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Pet not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePetStatus() {
        String petId = JOptionPane.showInputDialog("Enter Pet ID:");
        Pet selectedPet = null;

        for (Pet pet : pets) {
            if (pet.getId().equals(petId)) {
                selectedPet = pet;
                break;
            }
        }

        if (selectedPet != null) {
            String newStatus = JOptionPane.showInputDialog("Enter new status (En espera / Atendido):");
            selectedPet.setStatus(newStatus);
            refreshPetTable();
            JOptionPane.showMessageDialog(this, "Pet status updated successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Pet not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
