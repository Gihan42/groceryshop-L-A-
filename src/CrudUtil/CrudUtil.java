package CrudUtil;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String sql,Object...parms) throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm= connection.prepareStatement(sql);
        for(int i=0;i<parms.length;i++){
            pstm.setObject(i+1,parms[i]);
        }
        return pstm;
    }
    public static boolean executeUpdate(String sql, Object... parms) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, parms).executeUpdate() > 0;
    }
    public static ResultSet executeQuery(String sql, Object... parms) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, parms).executeQuery();
    }

}
