package team3.database.DBSearch;

import team3.database.SQLite;
import team3.database.Utilities;
import team3.utils.Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SmiSearch implements DBSearch {
    private final Utilities dbUtil = new Utilities();

    @Override
    public void dbSearch() {
        //sources
        Common.SMI_SOURCE.clear();
        Common.SMI_LINK.clear();
        try {
            Statement st = SQLite.connection.createStatement();
            ResultSet rs = st.executeQuery(dbUtil.getSQLQueryFromProp("smiQuery"));
            while (rs.next()) {
                //int id = rs.getInt("id");
                String source = rs.getString("source");
                String link = rs.getString("link");
                Common.SMI_SOURCE.add(source);
                Common.SMI_LINK.add(link);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
