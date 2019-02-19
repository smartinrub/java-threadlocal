package org.smartinrubio.javathreadlocal;

public class NameHandler {

    private BookingSession bookingSession;

    public NameHandler(BookingSession bookingSession) {
        this.bookingSession = bookingSession;
    }

    public void saveName(String name) {
        BookingSessionThreadLocalManager.setBookingSession(bookingSession);
        bookingSession.setName(name);
    }
}
