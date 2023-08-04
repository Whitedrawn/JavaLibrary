package coursework.gui;

import coursework.database.BOOK;


import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class BookTableModel extends AbstractTableModel implements PropertyChangeListener {

    // explictly adding data.
    public void setbookList(List<BOOK> bookList) {
        this.bookList = bookList;
        fireTableDataChanged();
    }

    private List<BOOK> bookList;

    public BookTableModel(List<BOOK> bookList) {
        this.bookList = bookList;
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Title";
            case 2:
                return "Publisher";
            case 3:
                return "Year Of publicaton";
            case 4:
                return "Subject";
            case 5:
                return "Author_ID";
            default:
                return "ERROR";
        }
    }

    @Override
    public int getRowCount() {
        return bookList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final BOOK book = bookList.get(row);
        switch (column) {
            case 0:
                return book.getId();
            case 1:
                return book.getTITLE();
            case 2:
                return book.getPUBLISHER_NAME();
            case 3:
                return book.getYEAR_OF_PUBLICATION();
            case 4:
                return book.getSUBJECT();
            case 5:
                return book.getAUTHOR_ID();
            default:
                return "ERROR";
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "bookList") {
            setbookList((List<BOOK>) evt.getNewValue());
        }
    }
}