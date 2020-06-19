/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
package data;

import sun.management.Sensor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EKGDAOSQLImpl implements EKGDAO {
    @Override
    public void saveEkg(EKGMeasure ekgMeasure) {
        Connection conn = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgData(cpr, data, time) VALUES (?, ?, ?)");
            preparedStatement.setString(1, ekgMeasure.getCpr());
            preparedStatement.setDouble(2, ekgMeasure.getMeasurement());
            preparedStatement.setTimestamp(3, ekgMeasure.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EKGMeasure> loadEKG(String id) {
        List<EKGMeasure> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ekgData WHERE cpr = ? ");
            preparedStatement.setString(1, "");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EKGMeasure ekgMeasure = new EKGMeasure();
                ekgMeasure.setId(Integer.parseInt(resultSet.getString("id")));
                ekgMeasure.setCpr(id);
                ekgMeasure.setMeasurement(resultSet.getDouble("data"));
                ekgMeasure.setTime(resultSet.getTimestamp("time"));
                data.add(ekgMeasure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}