package coursework.gui;

import coursework.database.AUTHOR;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AuthorComboBoxModel implements ComboBoxModel, PropertyChangeListener {

    private AUTHOR selectedItem;

    public void setAuthorList(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }

    private List<AUTHOR> authorList;

    public AuthorComboBoxModel(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (AUTHOR) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return authorList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return authorList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "authorList") {
            setAuthorList((List<AUTHOR>) evt.getNewValue());
        }

    }
}
