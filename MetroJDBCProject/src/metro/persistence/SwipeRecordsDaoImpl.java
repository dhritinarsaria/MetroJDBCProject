package metro.persistence;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;
import metro.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SwipeRecordsDaoImpl implements SwipeRecordsDao {

    @Override
    public void addSwipeRecord(SwipeRecord record) throws DatabaseConnectionException {
        String sql = "INSERT INTO SwipeRecords (cardNo, fareDeducted, start, end, startTime, endTime, date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, record.getCardNo());
            ps.setDouble(2, record.getFareDeducted());
            ps.setInt(3, record.getStart());
            ps.setInt(4, record.getEnd());
            ps.setTimestamp(5, record.getStartTime());  // assuming getStartTime() returns java.sql.Timestamp
            ps.setTimestamp(6, record.getEndTime());    // can be null if journey ongoing
            ps.setDate(7, record.getDate());            // java.sql.Date

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while inserting swipe record: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<SwipeRecord> getRecordsByCard(int cardNo) throws DatabaseConnectionException {
        String sql = "SELECT recordId, cardNo, fareDeducted, start, end, startTime, endTime, date " +
                     "FROM SwipeRecords WHERE cardNo = ? ORDER BY startTime DESC";

        List<SwipeRecord> records = new ArrayList<>();

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cardNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SwipeRecord record = new SwipeRecord();
                    record.setRecordId(rs.getInt("recordId"));
                    record.setCardNo(rs.getInt("cardNo"));
                    record.setFareDeducted(rs.getDouble("fareDeducted"));
                    record.setStart(rs.getInt("start"));
                    record.setEnd(rs.getInt("end"));
                    record.setStartTime(rs.getTimestamp("startTime"));
                    record.setEndTime(rs.getTimestamp("endTime"));
                    record.setDate(rs.getDate("date"));

                    records.add(record);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching swipe records: " + e.getMessage(), e);
        }

        return records;
    }


    // Other methods (not yet implemented)...
}
