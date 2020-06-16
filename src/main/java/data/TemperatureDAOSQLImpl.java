/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperatureDAOSQLImpl implements TemperatureDAO {
    @Override
    public void saveTemp(TempMeasure tempMeasure) {
        Connection conn = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO temperatureData(cpr, temperature, time) VALUES (?, ?, ?)");
            preparedStatement.setString(1, tempMeasure.getCpr());
            preparedStatement.setDouble(2, tempMeasure.getMeasurement());
            preparedStatement.setTimestamp(3, tempMeasure.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TempMeasure> loadTemp(String id) {
        List<TempMeasure> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM temperatureData WHERE cpr = ? ");
            preparedStatement.setString(1, "");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TempMeasure tempMeasure = new TempMeasure();
                tempMeasure.setId(Integer.parseInt(resultSet.getString("id")));
                tempMeasure.setCpr(id);
                tempMeasure.setMeasurement(resultSet.getDouble("temperature"));
                tempMeasure.setTime(resultSet.getTimestamp("time"));
                data.add(tempMeasure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}