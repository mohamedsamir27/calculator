import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Display field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        textField.setBackground(new Color(173, 216, 230));
        frame.add(textField, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "x",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "(", ")", "%"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBackground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1));
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        frame.add(panel, BorderLayout.CENTER);

        // Adjusting frame visibility
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            switch (command) {
                case "=":
                    try {
                        secondNumber = Double.parseDouble(textField.getText());
                        switch (operator) {
                            case "+":
                                textField.setText(String.valueOf(firstNumber + secondNumber));
                                break;
                            case "-":
                                textField.setText(String.valueOf(firstNumber - secondNumber));
                                break;
                            case "x":
                                textField.setText(String.valueOf(firstNumber * secondNumber));
                                break;
                            case "/":
                                if (secondNumber != 0) {
                                    textField.setText(String.valueOf(firstNumber / secondNumber));
                                } else {
                                    textField.setText("Error");
                                }
                                break;
                            case "%":
                                textField.setText(String.valueOf(firstNumber % secondNumber));
                                break;
                        }
                    } catch (Exception ex) {
                        textField.setText("Error");
                    }
                    break;

                case "+":
                case "-":
                case "x":
                case "/":
                case "%":
                    try {
                        firstNumber = Double.parseDouble(textField.getText());
                        operator = command;
                        textField.setText("");
                    } catch (Exception ex) {
                        textField.setText("Error");
                    }
                    break;

                case "C":
                    textField.setText("");
                    firstNumber = 0;
                    secondNumber = 0;
                    operator = "";
                    break;

                default: // Number or decimal point
                    textField.setText(textField.getText() + command);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
