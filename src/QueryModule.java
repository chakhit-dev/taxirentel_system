
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jonathan_Dev
 */
public class QueryModule {

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    PreparedStatement ps;
    
    public QueryModule() {
        try {
            // connect to database
            con = DriverManager.getConnection("jdbc:mysql://localhost/taxiandbooking_db", "root", "zz112233");
            System.out.println("Database is Connecting !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
// Generate CarID (UtilityCar) and (SedanCar)
    public static String generateUTL() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000;
        return "UTL" + number;
    }
    
    public static String generateSED() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000;
        return "SED" + number;
    }
    
// Generate Driver ID
    public static String generateDRV() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000;
        return "DRV" + number;
    }
    
// Generate Booking
    public static String generateBookingID() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000;
        return "BK" + number;
    }
    
    
// TimeSlot Query
    
    //Default Loading
    public List<TimeSlot> fetchTimeSlot() {
        String query = "SELECT * FROM time_active";
        List<TimeSlot> timeSlots = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                TimeSlot ts = new TimeSlot(rs.getString("start_time"), rs.getString("end_time"));
                timeSlots.add(ts);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return timeSlots;
    }
    
    // Add TimeSlot
    public int insertTimeSlot(TimeSlot ts) {
        
        try {
            String query = "INSERT INTO time_active (start_time, end_time) VALUES (?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, ts.getStartTime());
            ps.setString(2, ts.getEndTime());
            
            int status = ps.executeUpdate();
            
            if(status == 1){
                return 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    // Update TimeSlot
    public int updateTimeSlot(TimeSlot ts, String old_ts_start, String old_ts_end) {

        String sql = "UPDATE time_active SET start_time = ?, end_time = ? WHERE start_time = ? AND end_time = ?";
            
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ts.getStartTime());
            ps.setString(2, ts.getEndTime());
            ps.setString(3, old_ts_start);
            ps.setString(4, old_ts_end);
            int rowsUpdated = ps.executeUpdate();
            
            return rowsUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    // Delete TimeSlot
    public int deleteTimeSlot(String start_time, String end_time) {
        String sql = "DELETE FROM time_active WHERE start_time = ? AND end_time = ?";
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, start_time);
            ps.setString(2, end_time);
            
            int status = ps.executeUpdate();
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
// Car Query
    
    //Default Loading
    public List<UtilityCar> fetchUtility() {
        String query = "SELECT * FROM vehicle_model WHERE vehicle_type = 'Utility'";
        List<UtilityCar> data = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                UtilityCar uc = new UtilityCar(rs.getString("vehicle_id"), rs.getString("model"), rs.getInt("price"));
                data.add(uc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    public List<SedanCar> fetchSedan() {
        String query = "SELECT * FROM vehicle_model WHERE vehicle_type = 'Sedan'";
        List<SedanCar> data = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                SedanCar sc = new SedanCar(rs.getString("vehicle_id"), rs.getString("model"), rs.getInt("price"));
                data.add(sc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
// Select UtilirtyCar by id;
    public UtilityCar getUtilirtyCarById(String id) {
        UtilityCar car = null;
        String sql = "SELECT * FROM vehicle_model WHERE vehicle_id = ?";  // SQL ที่ใช้ค้นหาตาม id

        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            // Execute query
            rs = ps.executeQuery();

            if (rs.next()) {
                car = new UtilityCar(rs.getString("vehicle_id"), rs.getString("model"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }
    
    // Add UtilirtyCar
    public int insertUtilirtyCar(UtilityCar uc) {
        
        try {
            String query = "INSERT INTO vehicle_model (vehicle_id, vehicle_type, model, price) VALUES (?, ?, ?, ?)";
            
            String grt = generateUTL(); // Generate CarID
            uc.setCarID(grt); // Setter to carID
            
            ps = con.prepareStatement(query);
            ps.setString(1, uc.getCarID());
            ps.setString(2, "Utility");
            ps.setString(3, uc.getModel());
            ps.setInt(4, (int) uc.getPrice());
            
            int status = ps.executeUpdate();
            
            if(status == 1){
                return 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    // Update UtilityCar
    public int updateUtilityCar(UtilityCar uc, String old_car_id) {

        String sql = "UPDATE vehicle_model SET vehicle_id = ?, model = ?, price = ? WHERE vehicle_id = ?";
            
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, uc.getCarID());
            ps.setString(2, uc.getModel());
            ps.setDouble(3, uc.getPrice());
            ps.setString(4, old_car_id);

            int rowsUpdated = ps.executeUpdate();
            
            return rowsUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    // Delete UtilityCar
    public int deleteUtilityCar(String carid) {
        String sql = "DELETE FROM vehicle_model WHERE vehicle_id = ?";
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, carid);
            
            int status = ps.executeUpdate();
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Select SedanCar by id;
    public SedanCar getSedanCarById(String id) {
        SedanCar car = null;
        String sql = "SELECT * FROM vehicle_model WHERE vehicle_id = ?";  // SQL ที่ใช้ค้นหาตาม id

        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            // Execute query
            rs = ps.executeQuery();

            if (rs.next()) {
                car = new SedanCar(rs.getString("vehicle_id"), rs.getString("model"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }
    
    // Insert SedanCar    
    public int insertSedanCar(SedanCar sc) {
        
        try {
            String query = "INSERT INTO vehicle_model (vehicle_id, vehicle_type, model, price) VALUES (?, ?, ?, ?)";
            
            String grt = generateSED(); // Generate CarID
            sc.setCarID(grt); // Setter to carID
            
            ps = con.prepareStatement(query);
            ps.setString(1, sc.getCarID());
            ps.setString(2, "Sedan");
            ps.setString(3, sc.getModel());
            ps.setInt(4, (int) sc.getPrice());
            
            int status = ps.executeUpdate();
            
            if(status == 1){
                return 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    // Update SedanCar
    public int updateSedanCar(SedanCar sc, String old_car_id) {
        
        System.out.println(old_car_id);

        String sql = "UPDATE vehicle_model SET vehicle_id = ?, model = ?, price = ? WHERE vehicle_id = ?";
            
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sc.getCarID());
            ps.setString(2, sc.getModel());
            ps.setDouble(3, sc.getPrice());
            ps.setString(4, old_car_id);

            int rowsUpdated = ps.executeUpdate();
            
            return rowsUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    
    // Delete SedanCar
    public int deleteSedanCar(String carid) {
        String sql = "DELETE FROM vehicle_model WHERE vehicle_id = ?";
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, carid);
            
            int status = ps.executeUpdate();
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
// Driver
    
    //loadDriver information
    public List<Driver> fetchDriver() {
        String query = "SELECT * FROM Driver";
        List<Driver> data = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                Driver dv = new Driver(rs.getString("id"), rs.getString("name"), rs.getString("address"), rs.getInt("phone"));
                data.add(dv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    
    
    // Select Driver by id;
    public Driver getDriverById(String id) {
        Driver dv = null;
        String sql = "SELECT * FROM driver WHERE id = ?";

        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                dv = new Driver(rs.getString("id"), rs.getString("name"), rs.getString("address"), rs.getInt("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dv;
    }
    
    
    // Insert Driver    
    public int insertDriver(Driver dv) {
        
        try {
            String query = "INSERT INTO driver (id, name, address, phone) VALUES (?, ?, ?, ?)";
            
            String grt = generateDRV(); // Generate CarID
            dv.setDriverID(grt); // Setter to carID
            
            ps = con.prepareStatement(query);
            ps.setString(1, dv.getDriverID());
            ps.setString(2, dv.getName());
            ps.setString(3, dv.getAddress());
            ps.setInt(4, (int) dv.getPhone());
            
            int status = ps.executeUpdate();
            
            if(status == 1){
                return 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    // Update Driver
    public int updateDriver(Driver dv, String old_dv_id) {
        
        String sql = "UPDATE driver SET id = ?, name = ?, address = ?, phone = ? WHERE id = ?";
            
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dv.getDriverID());
            ps.setString(2, dv.getName());
            ps.setString(3, dv.getAddress());
            ps.setInt(4, dv.getPhone());
            ps.setString(5, old_dv_id);
           
            int rowsUpdated = ps.executeUpdate();
            
            return rowsUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    
    // Delete Driver
    public int deleteDriver(String dvid) {
        String sql = "DELETE FROM driver WHERE id = ?";
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, dvid);
            
            int status = ps.executeUpdate();
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
// Booking
    
    public List<Car> fetchSedan1() {
        String query = "SELECT * FROM vehicle_model WHERE vehicle_type = 'Sedan'";
        List<Car> data = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                SedanCar sc = new SedanCar(rs.getString("vehicle_id"), rs.getString("model"), rs.getInt("price"));
                data.add(sc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    //loadBooking information
    public List<Booking> fetchBookingSedan(Car car, Driver dv, TimeSlot ts) {
        List<Booking> bk_data = new ArrayList<>();

        String bk_query = "SELECT * FROM booking WHERE vehicle_type = 'Sedan'";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(bk_query);
            while (rs.next()) {
                
                String modelQ = rs.getString("model");
                
                Booking bk = new Booking(rs.getString("booking_id"), car, dv, rs.getString("date"), ts);
                bk.getCar().setModel(modelQ);
                bk_data.add(bk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return bk_data;
    }
    
    public List<Booking> fetchBookingUtilityCar(Car car, Driver dv, TimeSlot ts) {
        List<Booking> bk_data = new ArrayList<>();

        String bk_query = "SELECT * FROM booking WHERE vehicle_type = 'Utility'";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(bk_query);
            while (rs.next()) {
                
                Booking bk = new Booking(rs.getString("booking_id"), car, dv, rs.getString("date"), ts);
                bk_data.add(bk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return bk_data;
    }
    
    
    public Booking fetchBookingSedanByID(Car car, Driver dv, TimeSlot ts, String id) {
        Booking bk = null;
        String bk_query = "SELECT * FROM booking WHERE vehicle_type = 'Sedan' and booking_id = ?";

        try {
            ps = con.prepareStatement(bk_query);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                // ใช้ dv แทน driver และ ts แทน timeSlot
                bk = new Booking(id, car, dv, rs.getString("date"), ts); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bk;
    }

    public int insertBooking(Booking bk) {
        
        try {
            String query = "INSERT INTO booking (booking_id, vehicle_type, driver_id, model, date) VALUES (?, ?, ?, ?, ?)";
            
            String gbid = generateBookingID(); // Generate CarID
            bk.setBookingID(gbid);
            
            LocalDateTime timenow = LocalDateTime.now();
            DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = timenow.format(timeformat);
            
            ps = con.prepareStatement(query);
            ps.setString(1, bk.getBookingID());
            ps.setString(2, "Sedan");
            ps.setString(3, bk.getDriver().getDriverID());
            ps.setString(4, bk.getCar().getModel());
            ps.setString(5, formattedDate);
            
            int status = ps.executeUpdate();
            
            if(status == 1){
                return 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
