package coursework.gui;

import coursework.controller.Controller;
import coursework.database.AUTHOR;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AuthorTableModel extends AbstractTableModel implements PropertyChangeListener {


    // explictly adding data. (non event)
    public void setAuthorList(List<AUTHOR> authorList) {
        this.authorList = authorList;
        fireTableDataChanged();
    }

    // implicitly addomg data (non event)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "facultyList") {
            setAuthorList((List<AUTHOR>) evt.getNewValue());
        }
    }

    private List<AUTHOR> authorList;

    public AuthorTableModel(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "NAME";
            default:
                return "ERROR";
        }
    }

    @Override
    public int getRowCount() {
        return authorList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final AUTHOR author = authorList.get(row);
        switch (column) {
            case 0:
                return author.getId();
            case 1:
                return author.getFIRST_NAME();
            case 2:
                return author.getLAST_NAME();
            default:
                return "ERROR";
        }
    }


}
