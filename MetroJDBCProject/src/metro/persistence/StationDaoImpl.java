package metro.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metro.entity.Station;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationAlreadyExistsException;
import metro.exceptions.StationNotFoundException;
import metro.util.DBConnectionUtil;

public class StationDaoImpl implements StationDao{

	 @Override
	    public List<Station> getAllStations() throws DatabaseConnectionException, Exception {
	        List<Station> stations = new ArrayList<>();
	        String query = "SELECT stationId, stationName FROM station";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                stations.add(new Station(rs.getInt("stationId"), rs.getString("stationName")));
	            }
	        }
	        return stations;
	    }

	    @Override
	    public void addStation(Station station) throws DatabaseConnectionException, Exception, StationAlreadyExistsException {
	    	String query = "INSERT INTO Station (stationName) VALUES (?)";
	        try (Connection conn = DBConnectionUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, station.getStationName());
	            ps.executeUpdate();
	        }
	    }

	    @Override
	    public int countStations() throws DatabaseConnectionException, Exception {
	        String query = "SELECT COUNT(*) FROM station";
	        int count = 0;

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	        }
	        return count;
	    }

	    @Override
	    public Station getStationById(int stationId) throws StationNotFoundException, DatabaseConnectionException, Exception {
	        String query = "SELECT stationId, stationName FROM station WHERE stationId = ?";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(query)) {

	            ps.setInt(1, stationId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return new Station(rs.getInt("stationId"), rs.getString("stationName"));
	                } else {
	                    throw new StationNotFoundException("Station with ID " + stationId + " not found");
	                }
	            }
	        }
	    }

	    @Override
	    public Station getStationByName(String stationName) throws StationNotFoundException, DatabaseConnectionException, Exception {
	        String query = "SELECT stationId, stationName FROM station WHERE stationName = ?";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(query)) {

	            ps.setString(1, stationName);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return new Station(rs.getInt("stationId"), rs.getString("stationName"));
	                } else {
	                    throw new StationNotFoundException("Station with name '" + stationName + "' not found");
	                }
	            }
	        }
	    }
}
