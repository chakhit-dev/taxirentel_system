public class TaxiBookingSystem {
    public static void main(String[] args) {

        TimeSlot morningSlot = new TimeSlot("6:00", "14:00");
        TimeSlot afternoonSlot = new TimeSlot("14:00", "22:00");
        TimeSlot nightSlot = new TimeSlot("22:00", "6:00");

        Car sedanCar = new SedanCar("SED001", "Toyota AE86", 1000);
        Car utilityCar = new UtilityCar("UTL001", "Nissan GTR R34", 1200);

        Driver driver1 = new Driver("DRV001", "Vinicius jr", "123 Main St", 987654321);
        Driver driver2 = new Driver("DRV002", "Jude Bellingham", "456 Elm St", 876543210);

        Booking booking1 = new Booking("BK001", sedanCar, driver1, "2024-11-25", morningSlot);
        Booking booking2 = new Booking("BK002", utilityCar, driver2, "2024-11-26", afternoonSlot);

        Invoice invoice = new Invoice();
        invoice.addBooking(booking1);
        invoice.addBooking(booking2);

        //invoice.printInvoice();

        System.out.println(driver1.getDriverID());
        

    }
}