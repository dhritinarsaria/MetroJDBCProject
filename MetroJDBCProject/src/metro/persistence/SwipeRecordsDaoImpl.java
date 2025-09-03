package metro.persistence;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;
import metro.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SwipeRecordsDaoImpl implements SwipeRecordsDao {

    @Override
    public void swipeIn(int cardNo, int startStationId) throws DatabaseConnectionException {
        String sql = "INSERT INTO SwipeRecords (cardNo, start, end, startTime, date) VALUES (?, ?, NULL, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cardNo);
            ps.setInt(2, startStationId);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error during Swipe In: " + e.getMessage());
        }
    }

    @Override
    public void swipeOut(int cardNo, int endStationId, double fare) throws DatabaseConnectionException {
        String sql = "UPDATE SwipeRecords SET end = ?, endTime = ?, fareDeducted = ? " +
                     "WHERE cardNo = ? AND end IS NULL";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, endStationId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setDouble(3, fare);
            ps.setInt(4, cardNo);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new DatabaseConnectionException("No open journey found for card " + cardNo);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error during Swipe Out: " + e.getMessage());
        }
    }

    @Override
    public SwipeRecord getOpenJourneyByCard(int cardNo) throws DatabaseConnectionException {
        String sql = "SELECT * FROM SwipeRecords WHERE cardNo = ? AND end IS NULL";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SwipeRecord record = new SwipeRecord();
                record.setRecordId(rs.getInt("recordId"));
                record.setCardNo(rs.getInt("cardNo"));
                record.setStart(rs.getInt("start"));
                record.setStartTime(rs.getTimestamp("startTime"));
                record.setDate(rs.getDate("date"));
                return record;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching open journey: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<SwipeRecord> getRecordsByCard(int cardNo) throws DatabaseConnectionException {
        List<SwipeRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM SwipeRecords WHERE cardNo = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cardNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SwipeRecord record = new SwipeRecord();
                record.setRecordId(rs.getInt("recordId"));
                record.setCardNo(rs.getInt("cardNo"));
                record.setStart(rs.getInt("start"));
                record.setEnd(rs.getInt("end"));
                record.setFareDeducted(rs.getDouble("fareDeducted"));
                record.setStartTime(rs.getTimestamp("startTime"));
                record.setEndTime(rs.getTimestamp("endTime"));
                record.setDate(rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching swipe history: " + e.getMessage());
        }
        return records;
    }
}
