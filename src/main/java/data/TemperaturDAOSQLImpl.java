package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperaturDAOSQLImpl implements TemperaturDAO {
    @Override
    public void save(TempMeasure tempMeasure) {
        Connection conn = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO temperaturData(cpr, temperatur, tid) VALUES (?, ?, ?)");
            preparedStatement.setString(1, tempMeasure.getCpr());
            preparedStatement.setDouble(2,tempMeasure.getMeasurement());
            preparedStatement.setTimestamp(3,tempMeasure.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<TempMeasure> load(String id) {
        List<TempMeasure> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM temperaturData WHERE id = ? ");
            preparedStatement.setString(1, "");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TempMeasure tempMeasure = new TempMeasure();
                tempMeasure.setId(Integer.parseInt(resultSet.getString("")));
                tempMeasure.setCpr(resultSet.getString("cpr"));
                tempMeasure.setMeasurement(resultSet.getDouble("temperatur"));
                tempMeasure.setTime(resultSet.getTimestamp("tid"));
                data.add(tempMeasure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


}




