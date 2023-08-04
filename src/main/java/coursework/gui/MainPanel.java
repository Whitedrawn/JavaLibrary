package coursework.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel {

    private JTabbedPane appTabbedPane;


    public MainPanel() {
        super(new GridLayout(1,1));
        this.appTabbedPane = new JTabbedPane();

        appTabbedPane.addTab("BOOKS", new BookPanel());
        appTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        appTabbedPane.addTab("AUTHORS", new AuthorPanel());
        appTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        appTabbedPane.addTab("SORTING",new SortPanel());
        appTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        add(appTabbedPane);

        appTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }
}
