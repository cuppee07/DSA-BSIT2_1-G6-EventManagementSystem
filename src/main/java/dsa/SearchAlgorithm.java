package dsa;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowFilter;
public class SearchAlgorithm {


    public static List<Integer> searchEvents(
            DefaultTableModel listTable,
            String search,
            String searchBy) {

        List<Integer> matches = new ArrayList<>();
        search = search.trim().toLowerCase();

        if (search.isEmpty()) {
            return matches;
        }

        int column = 2;                              // Date
        if (searchBy.equalsIgnoreCase("Category")) {
            column = 3;
        } else if (searchBy.equalsIgnoreCase("Event ID")) {
            column = 0;
        }

        for (int i = 0; i < listTable.getRowCount(); i++) {
            String value = listTable.getValueAt(i, column).toString().toLowerCase();
            if (value.contains(search)) {
                matches.add(i);
            }
        }
        return matches;
    }
    public static RowFilter<DefaultTableModel, Integer> searchFilter(
            DefaultTableModel listTable,
            String search,
            String searchBy) {

        final List<Integer> matches = searchEvents(listTable, search, searchBy);

        return new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                return matches.contains(entry.getIdentifier());
            }
        };
    }

    public static String searchAttendee(
            DefaultTableModel listTable,
            String search,
            String searchBy) {
       
        search = search.trim();

        if (search.isEmpty()) {
            return null;
        }

        int column = 1;

        if (searchBy.equalsIgnoreCase("ID")) {
            column = 0;
        } else if (searchBy.equalsIgnoreCase("Email")) {
            column = 2;
        }
        
        // Linear Search
        for (int i = 0; i < listTable.getRowCount(); i++) {

            String value = listTable.getValueAt(i, column).toString();

            if (value.equalsIgnoreCase(search)) {

                String name = listTable.getValueAt(i, 1).toString();

                return name;
            }
        }

        return null;
    }
}
