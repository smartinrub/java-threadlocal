package org.smartinrubio.javathreadlocal;

public class BookingSessionThreadLocalManager {

    private static final ThreadLocal<BookingSession> THREAD_LOCAL = new ThreadLocal<>();

    public static BookingSession getBookingSession() {
        return THREAD_LOCAL.get();
    }

    public static void setBookingSession(BookingSession bookingSession) {
        THREAD_LOCAL.set(bookingSession);
    }

    public static void destroyBookingSession() {
        THREAD_LOCAL.remove();
    }

}
