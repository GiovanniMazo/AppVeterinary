package AppVeterinary;

import java.awt.GridLayout;
import javax.swing.*;

class RegisterPetScreen extends JFrame {
    public RegisterPetScreen() {
        setTitle("Register Pet");
        setSize(100, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        JLabel nameLabel = new JLabel("Pet Name:");
        JTextField nameField = new JTextField();
        JLabel ownerLabel = new JLabel("Owner ID:");
        JTextField ownerField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel speciesLabel = new JLabel("Species:");
        JTextField speciesField = new JTextField();
        JButton registerButton = new JButton("Register");
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ownerLabel);
        panel.add(ownerField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(speciesLabel);
        panel.add(speciesField);
        panel.add(new JLabel()); 
        panel.add(registerButton);
        
        add(panel);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new RegisterPetScreen();
    }
}
