
import java.util.Set;

abstract class Car {
    protected String carID;
    protected String model;
    protected double price;

    public Car(String carID, String model, double price) {
        this.carID = carID;
        this.model = model;
        this.price = price;
    }

    public String getCarID() {
        return carID;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
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

    public abstract double calculateFinalPrice();

    public String toString() {
        return "CarID: " + carID + ", Model: " + model + ", Price: " + price;
    }
}
