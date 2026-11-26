package dsa;

import javax.swing.table.DefaultTableModel;

public class SearchAlgorithm {

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
