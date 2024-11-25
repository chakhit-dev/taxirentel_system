class UtilityCar extends Car {
    private static final double DISCOUNT = 50;

    public UtilityCar(String carID, String model, double price) {
        super(carID, model, price);
    }

    public double calculateFinalPrice() {
        return price - DISCOUNT;
    }

    @Override
    public String getCarID() {
        return super.getCarID(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getModel() {
        return super.getModel(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public static double getDISCOUNT() {
        return DISCOUNT;
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