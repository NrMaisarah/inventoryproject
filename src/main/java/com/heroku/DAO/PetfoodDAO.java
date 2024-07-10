package com.heroku.DAO;


    import com.heroku.java.models.*;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    //import java.net.URI;
    
    
    public class PetfoodDAO {
    
        // Create a new Petfood record in the database
        public void createPetFood(Petfood petfood) {
           
            Connection con = null;
            PreparedStatement inventoryPs = null;
            PreparedStatement petfoodPs = null;
    
            try {
                con = ConnectionManager.getConnection();
                String inventorySql = "INSERT INTO inventory (inventoryID, inventoryName, inventoryBrand, inventoryCategory, inventoryPrice, inventoryQuantityExisting, inventoryReorderPoint, staffid) " + 
                        "VALUES (INVENTORY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?,? )";
                
                inventoryPs = con.prepareStatement(inventorySql, new String[] { "inventoryID" });
                
                inventoryPs.setString(1, petfood.getInventoryName());
                inventoryPs.setString(2, petfood.getInventoryBrand());
                inventoryPs.setString(3, petfood.getInventoryCategory());
                inventoryPs.setDouble(4, petfood.getInventoryPrice());
                inventoryPs.setInt(5, petfood.getInventoryQuantityExisting());
                inventoryPs.setInt(6, petfood.getInventoryReorderPoint());
                inventoryPs.setInt(7, petfood.getStaffID());
                inventoryPs.executeUpdate();
                
                // Get the generated inventoryID
                ResultSet generatedKeys = inventoryPs.getGeneratedKeys();
                if (generatedKeys.next()) {
                    petfood.setInventoryID(generatedKeys.getInt(1));
                }
                
                String petfoodSql = "INSERT INTO petfood (inventoryID,foodWeight, quantityIn, foodType) VALUES (?,?, ?, ?)";
                petfoodPs = con.prepareStatement(petfoodSql);
    
                petfoodPs.setInt(1, petfood.getInventoryID());
                petfoodPs.setDouble(2, petfood.getFoodWeight());
                petfoodPs.setInt(3, petfood.getQuantityIn());
                petfoodPs.setString(4, petfood.getFoodType());
                petfoodPs.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inventoryPs != null) inventoryPs.close();
                    if (petfoodPs != null) petfoodPs.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        // Retrieve a Petfood record by its inventoryID for view
        public Petfood getPetFoodById(int inventoryID) {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Petfood petfood = null;
    
            try {
                con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM inventory INNER JOIN petfood ON inventory.inventoryID = petfood.inventoryID WHERE inventory.inventoryID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, inventoryID);
    
                rs = ps.executeQuery();
                if (rs.next()) {
                    petfood = new Petfood();
                    petfood.setInventoryID(rs.getInt("inventoryID"));
                    petfood.setInventoryName(rs.getString("inventoryName"));
                    petfood.setInventoryBrand(rs.getString("inventoryBrand"));
                    petfood.setInventoryCategory(rs.getString("inventoryCategory"));
                    petfood.setInventoryPrice(rs.getDouble("inventoryPrice"));
                    petfood.setFoodWeight(rs.getDouble("foodWeight"));
                    petfood.setFoodType(rs.getString("foodType"));
                    petfood.setQuantityIn(rs.getInt("quantityIn"));
                    petfood.setInventoryQuantityExisting(rs.getInt("inventoryQuantityExisting"));
                    petfood.setInventoryReorderPoint(rs.getInt("inventoryReorderPoint"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    
            return petfood;
        }
    
    
    // Retrieve all Petfood records from the database for list
        public List<Petfood> getAllPetFoods() {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            List<Petfood> petfoods = new ArrayList<>();
    
            try {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                String sql = "SELECT * FROM inventory INNER JOIN petfood ON inventory.inventoryID = petfood.inventoryID ORDER BY inventory.inventoryID";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    Petfood petfood = new Petfood();
                    petfood.setInventoryID(rs.getInt("inventoryID"));
                    petfood.setInventoryName(rs.getString("inventoryName"));
                    petfood.setInventoryBrand(rs.getString("inventoryBrand"));
                    petfood.setInventoryCategory(rs.getString("inventoryCategory"));
                    petfood.setInventoryPrice(rs.getDouble("inventoryPrice"));
                    petfood.setFoodWeight(rs.getDouble("foodWeight"));
                    petfood.setQuantityIn(rs.getInt("quantityIn"));
                    petfood.setFoodType(rs.getString("foodType"));
                    petfood.setInventoryQuantityExisting(rs.getInt("inventoryQuantityExisting"));
                    petfood.setInventoryReorderPoint(rs.getInt("inventoryReorderPoint"));
                    petfoods.add(petfood);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    
            return petfoods;
        }
        
       
    
        // Update a Petfood record in the database
        public void updatePetFood(Petfood petfood) {
            Connection con = null;
            PreparedStatement inventoryPs = null;
            PreparedStatement petfoodPs = null;
    
            try {
                con = ConnectionManager.getConnection();
                String inventorySql = "UPDATE inventory SET inventoryName = ?, inventoryBrand = ?, inventoryCategory = ?, inventoryPrice = ?, inventoryQuantityExisting = ?, inventoryReorderPoint = ? WHERE inventoryID = ?";
                inventoryPs = con.prepareStatement(inventorySql);
                
                inventoryPs.setString(1, petfood.getInventoryName());
                inventoryPs.setString(2, petfood.getInventoryBrand());
                inventoryPs.setString(3, petfood.getInventoryCategory());
                inventoryPs.setDouble(4, petfood.getInventoryPrice());
                inventoryPs.setInt(5, petfood.getInventoryQuantityExisting());
                inventoryPs.setInt(6, petfood.getInventoryReorderPoint());
                inventoryPs.setInt(7, petfood.getInventoryID());
    
                inventoryPs.executeUpdate();
    
                String petfoodSql = "UPDATE petfood SET foodWeight = ?, quantityIn = ?, foodType = ? WHERE inventoryID = ?";
                petfoodPs = con.prepareStatement(petfoodSql);
    
                petfoodPs.setDouble(1, petfood.getFoodWeight());
                petfoodPs.setInt(2, petfood.getQuantityIn());
                petfoodPs.setString(3, petfood.getFoodType());
                petfoodPs.setInt(4, petfood.getInventoryID());
             
    
                petfoodPs.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inventoryPs != null) inventoryPs.close();
                    if (petfoodPs != null) petfoodPs.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        // Delete a Petfood record by its inventoryID
        public void deletePetFood(int inventoryID) {
            Connection con = null;
            PreparedStatement ps = null;
    
            try {
                con = ConnectionManager.getConnection();
                String sql = "DELETE FROM inventory WHERE inventoryID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, inventoryID);
    
    
    ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    


    