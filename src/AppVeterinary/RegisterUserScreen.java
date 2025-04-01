package AppVeterinary;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class RegisterUserScreen extends JFrame {
    public RegisterUserScreen() {
        setTitle("Register User");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        String[] roles = {"Administrator", "Veterinarian", "Seller", "Owner"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        JButton registerButton = new JButton("Register");
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(roleLabel);
        panel.add(roleBox);
        panel.add(registerButton);
        
        add(panel);
        setVisible(true);
    }
}
