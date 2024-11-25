class Booking {
    private String bookingID;
    private Car car;
    private Driver driver;
    private String date;
    private TimeSlot timeSlot;

    public Booking(String bookingID, Car car, Driver driver, String date, TimeSlot timeSlot) {
        this.bookingID = bookingID;
        this.car = car;
        this.driver = driver;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public String getBookingID() {
        return bookingID;
    }

    public Car getCar() {
        return car;
    }

    public String getDate() {
        return date;
    }

    public Driver getDriver() {
        return driver;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
    
    
    

    public String toString() {
        return "BookingID: " + bookingID + ", Car: [" + car + "], Driver: [" + driver + "], Date: " + date + ", TimeSlot: [" + timeSlot + "]";
    }
}