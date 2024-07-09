package com.heroku.java.models;

public class Inventory {

  int inventoryID;
   String inventoryName;
  String inventoryCategory;
  String inventoryBrand;
   double inventoryPrice;
  int inventoryQuantityExisting;
   int inventoryReorderPoint;
 int staffID;

  public Inventory(){
  }
  public int getInventoryID() {
    return inventoryID;
  }
  public void setInventoryID(int inventoryID) {
    this.inventoryID = inventoryID;
  }
  
  public String getInventoryName() {
    return inventoryName;
  }
  public void setInventoryName(String inventoryName) {
    this.inventoryName = inventoryName;
  }
  
  public String getInventoryBrand() {
    return inventoryBrand;
  }
  
  public void setInventoryBrand(String inventoryBrand) {
    this.inventoryBrand = inventoryBrand;
  }
  
  public String getInventoryCategory() {
    return inventoryCategory;
  }
  
  public void setInventoryCategory(String inventoryCategory) {
    this.inventoryCategory = inventoryCategory;
  }

  public double getInventoryPrice() {
    return inventoryPrice;
  }
  
  public void setInventoryPrice(double inventoryPrice) {
    this.inventoryPrice = inventoryPrice;
  }
  
  
  public int getInventoryQuantityExisting() {
    return inventoryQuantityExisting;
  }
  
  public void setInventoryQuantityExisting(int inventoryQuantityExisting) {
    this.inventoryQuantityExisting = inventoryQuantityExisting;
  }
    
    public int getInventoryReorderPoint() {
        return inventoryReorderPoint;
      }
      public void setInventoryReorderPoint(int inventoryReorderPoint) {
        this.inventoryReorderPoint = inventoryReorderPoint;
      }
      
      public int getStaffID() {
          return staffID;
        }
        public void setStaffID(int staffID) {
          this.staffID = staffID;
        }
      
      
     
}

