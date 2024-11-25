import java.util.ArrayList;
import java.util.List;

class Invoice {
    private List<Booking> bookings;

    public Invoice() {
        this.bookings = new ArrayList<>();
    }
    public void addBooking(Booking booking) {
        bookings.add(booking); 
    }
    public List<Booking> getBookings() {
        return bookings;
    }
    public void printInvoice() {
        System.out.println("Invoice Details:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking booking : bookings) { 
                System.out.println(booking); 
            }
        }
    }
}
