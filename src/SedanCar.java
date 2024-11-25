class SedanCar extends Car {
    private static final double INSURANCE_COST = 80;

    public SedanCar(String carID, String model, double price) {
        super(carID, model, price);
    }
    
    public double calculateFinalPrice() {
        return price + INSURANCE_COST;
    }

    @Override
    public String getCarID() {
        return super.getCarID(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public static double getINSURANCE_COST() {
        return INSURANCE_COST;
    }

    @Override
    public String getModel() {
        return super.getModel(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public double getPrice() {
        return super.getPrice(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    

    public String toString() {
        return super.toString() + ", Final Price: " + calculateFinalPrice();
    }
}
