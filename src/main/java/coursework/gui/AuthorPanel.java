package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorPanel extends JPanel {

    public AuthorPanel() {
        super(new GridLayout(1,2));

        add(new AuthorFormPanel());

        AuthorTableModel AuthorTableModel = new AuthorTableModel(Controller.INSTANCE.getAuthorList());
        Controller.INSTANCE.addPropertyChangeListener(AuthorTableModel);
        add(new JScrollPane(new JTable(AuthorTableModel)));
    }

    public static class AuthorFormPanel extends JPanel {
        private final JTextField firstNameTextField;
        private final JTextField lastNameTextField;
        private final JButton addButton;


        private void createGUI() {

            setBorder(BorderFactory.createTitledBorder("Author"));
            setLayout(new GridBagLayout());


            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(4, 4, 4, 5);
            gc.anchor = GridBagConstraints.LINE_END;

            gc.gridx = 0;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.SOUTHEAST;
            add(new JLabel("First Name : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.SOUTHEAST;
            add(firstNameTextField,gc);

            add(new JLabel("Last Name :",SwingConstants.RIGHT),gc);
            gc.gridx = 2;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.SOUTHEAST;
            add(lastNameTextField,gc);





            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String First_Name = firstNameTextField.getText();
                    String Last_Name = lastNameTextField.getText();
                    Controller.INSTANCE.addAuthor("", "");
                }
            });

            gc.gridx = 1;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.NORTHEAST;

            add(addButton,gc);

        }

        public AuthorFormPanel() {
            firstNameTextField = new JTextField();
            lastNameTextField= new JTextField();
            addButton = new JButton("ADD");

            createGUI();
        }
    }

}
