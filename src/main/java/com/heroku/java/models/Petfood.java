package com.heroku.java.models;
public class Petfood extends Inventory {

    private double foodWeight;
    private int quantityIn;
    private String foodType;

 

    public Petfood() {
        super();
    }


    public double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(double foodWeight) {
        this.foodWeight = foodWeight;
    }
    
    public int getQuantityIn() {
        return quantityIn ;
    }
    
    public void setQuantityIn(int quantityIn) {
        this.quantityIn = quantityIn;
    }
    
    public String getFoodType() {
        return foodType ;
    }
    
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
    
   

  
    
    @Override
    public String toString() {
        return "Petfood{" +
                "foodWeight=" + foodWeight +
                ", quantityIn=" + quantityIn +
                ", inventoryID=" + inventoryID +
                ", inventoryName='" + inventoryName + '\'' +
                ", inventoryCategory='" + inventoryCategory + '\'' +
                ", inventoryBrand='" + inventoryBrand + '\'' +
                ", inventoryPrice=" + inventoryPrice +
                ", inventoryQuantityExisting=" + inventoryQuantityExisting +
                ", inventoryReorderPoint=" + inventoryReorderPoint +
                ", foodType=" + foodType +
                '}';
    } 
}

