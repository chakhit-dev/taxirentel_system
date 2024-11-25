class Driver {
    private String driverID;
    private String name;
    private String address;
    private int phone;

    public Driver(String driverID, String name, String address, int phone) {
        this.driverID = driverID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    

    public String toString() {
        return "DriverID: " + driverID + ", Name: " + name + ", Address: " + address + ", Phone: " + phone;
    }
}