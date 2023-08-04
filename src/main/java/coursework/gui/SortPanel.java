package coursework.gui;

import coursework.controller.Controller;
import coursework.database.BOOK;
import coursework.sorting.Bubble;
import coursework.sorting.MergeSort;
import coursework.sorting.QuickSort;
import kotlin.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SortPanel extends JPanel {
    private final JButton sortButton;
    private final JRadioButton bubbleSortRadioButton;
    private final JRadioButton quickSortRadioButton;
    private final JRadioButton mergeSortRadioButon;
    private final JRadioButton nameSortRadioButton;
    private final JRadioButton ageSortRadioButton;
    private final BookTableModel bookTableModel;
    private final JTextField ticksTextField;

    private void createUILayout() {
        {
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();

            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.BOTH;

            JPanel panelSort = new JPanel();
            panelSort.setBorder(BorderFactory.createTitledBorder("Select Algorithm "));
            panelSort.setLayout(new BoxLayout(panelSort, BoxLayout.Y_AXIS));
            panelSort.add(quickSortRadioButton);
            panelSort.add(mergeSortRadioButon);
            panelSort.add(bubbleSortRadioButton);


            JPanel panelField = new JPanel();
            panelField.setBorder(BorderFactory.createTitledBorder("Select Field:"));
            panelField.setLayout(new BoxLayout(panelField, BoxLayout.Y_AXIS));

            panelField.add(nameSortRadioButton);
            panelField.add(ageSortRadioButton);

            JPanel performancePanel = new JPanel();
            performancePanel.setBorder(BorderFactory.createTitledBorder("Performance :"));
//            performancePanel.setLayout(new BoxLayout(performancePanel, BoxLayout.X_AXIS));
//            performancePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            performancePanel.setLayout(new GridBagLayout());
            GridBagConstraints gc1 = new GridBagConstraints();

            gc1.weighty = 1;
            gc1.weightx = 1;
            gc1.fill = GridBagConstraints.HORIZONTAL;
            gc1.insets = new Insets(0, 4, 0, 5);
            gc1.anchor = GridBagConstraints.LINE_END;


            gc1.gridx = 0;
            gc1.gridy = 1;
            performancePanel.add(new JLabel("Ticks: ",SwingConstants.RIGHT),gc1);

            gc1.gridx = 1;
            gc1.gridy = 1;
            gc1.anchor = GridBagConstraints.LINE_END;
            performancePanel.add(ticksTextField,gc1);


            gc.gridx = 0;
            gc.gridy = 0;
            gc.gridheight = GridBagConstraints.BOTH;
            add(panelSort, gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.gridheight = GridBagConstraints.REMAINDER;
            add(new JScrollPane(new JTable(bookTableModel)), gc);

            gc.gridx = 0;
            gc.gridy = 1;
            gc.gridheight = GridBagConstraints.BOTH;
            add(panelField, gc);

            gc.gridx = 0;
            gc.gridy = 2;
            gc.weighty = 0.5;
            gc.gridheight = GridBagConstraints.BOTH;
            add(performancePanel, gc);

            gc.gridx = 0;
            gc.gridy = 3;
            gc.fill = GridBagConstraints.NONE;
            add(sortButton,gc);



        }

    }

    public SortPanel() {

        sortButton = new JButton("Sort");
        sortButton.setPreferredSize(sortButton.getMinimumSize());
        bubbleSortRadioButton = new JRadioButton("BubbleSort");
        quickSortRadioButton = new JRadioButton("QuickSort");
        mergeSortRadioButon = new JRadioButton("MergeSort");
        mergeSortRadioButon.setSelected(true);

        ButtonGroup sortRadioGroup = new ButtonGroup();
        sortRadioGroup.add(bubbleSortRadioButton);
        sortRadioGroup.add(quickSortRadioButton);
        sortRadioGroup.add(mergeSortRadioButon);


        nameSortRadioButton = new JRadioButton("Name : ");
        ageSortRadioButton = new JRadioButton("Age : ");
        nameSortRadioButton.setSelected(true);

        ButtonGroup sortFieldGroup = new ButtonGroup();
        sortFieldGroup.add(ageSortRadioButton);
        sortFieldGroup.add(nameSortRadioButton);

        bookTableModel = new BookTableModel(Controller.INSTANCE.getBookList());

        ticksTextField = new JTextField();
//        ticksTextField.setEditable(false);
        ticksTextField.setEnabled(false);
        ticksTextField.setHorizontalAlignment(JTextField.RIGHT);

        createUILayout();

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<BOOK> bookList = Controller.INSTANCE.getBookList();
                int ticks = 0;
                if (bubbleSortRadioButton.isSelected())
                    ticks = Bubble.INSTANCE.sort((ArrayList<BOOK>) bookList);
                else if (quickSortRadioButton.isSelected())
                    ticks = QuickSort.INSTANCE.sort((ArrayList<BOOK>) bookList);
                else if (mergeSortRadioButon.isSelected()) {
                    // MergeSort is not in-memory result.
                    // so we have to update explictly the TableModel
                    Pair<ArrayList<BOOK>, Integer> pair = MergeSort.INSTANCE.sort((ArrayList<BOOK>) bookList);
                    bookList = pair.component1();
                    ticks = pair.component2();
                }

                // Refreshing explictly the Table Model. (not from controller).
                bookTableModel.setbookList(bookList);
                ticksTextField.setText(Integer.toString(ticks));
                System.out.println("ticks " + ticks);

            }
        });


    }
}
