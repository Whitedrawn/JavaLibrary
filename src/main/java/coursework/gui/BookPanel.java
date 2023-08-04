package coursework.gui;


import coursework.controller.Controller;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BookPanel extends JPanel {

    public BookPanel() {
        setLayout(new GridLayout(1,2));
        add(new BookFormPanel());
        final BookTableModel bookTableModel = new BookTableModel(Controller.INSTANCE.getBookList());
        Controller.INSTANCE.addPropertyChangeListener(bookTableModel);
        add(new JScrollPane(new JTable(bookTableModel)));

    }

    public static class BookFormPanel extends JPanel {
        private final JTextField titleTextField;
        private final JTextField publisherTextField;
        private final JTextField subjectTextField;
        private final JTextField yopTextField;
        private final JButton addButton;
        private final JComboBox authorComboBox;

        public BookFormPanel() {

            titleTextField = new JTextField();
            publisherTextField = new JTextField();
            yopTextField = new JTextField();
            subjectTextField = new JTextField();
            addButton = new JButton("ADD");
            final AuthorComboBoxModel authorComboBoxModel = new AuthorComboBoxModel(Controller.INSTANCE.getAuthorList());
            authorComboBox = new JComboBox(authorComboBoxModel);
            authorComboBox.setEditable(false);
            Controller.INSTANCE.addPropertyChangeListener(authorComboBoxModel);

            createUILayout();

            //
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String Title = titleTextField.getText();
                    String Year_Of_Publication = yopTextField.getText();
                    String Publisher = publisherTextField.getText();
                    String Subject = subjectTextField.getText();
                    Controller.INSTANCE.add_Book(Title,Publisher, Long.parseLong(Year_Of_Publication) ,Subject, Long.valueOf(1));
                }
            });

        }

        private void createUILayout() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createTitledBorder("Book"));
            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(0, 4, 0, 5);
            gc.anchor = GridBagConstraints.LINE_END;


            gc.gridx = 0;
            gc.gridy = 0 ;
            add(new JLabel("Title :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0 ;
            add(titleTextField,gc);

            gc.gridx = 0;
            gc.gridy = 1 ;
            add(new JLabel("Publisher :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 1 ;
            add(publisherTextField,gc );

            gc.gridx = 0;
            gc.gridy = 2 ;
            add(new JLabel("Year Of Publication : ",SwingConstants.RIGHT),gc);


            gc.gridx = 1;
            gc.gridy = 2 ;
            add(yopTextField,gc);

            gc.gridx = 0;
            gc.gridy = 3 ;
            add(new JLabel("Subject :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 3 ;
            add(subjectTextField,gc);

            gc.gridx = 0;
            gc.gridy = 4 ;
            add(new JLabel("Author ID :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 4 ;
            add(authorComboBox,gc);

            gc.gridx = 1;
            gc.gridy = 5 ;
            gc.fill = GridBagConstraints.NONE;
            add(addButton,gc);

        }
    }

}
