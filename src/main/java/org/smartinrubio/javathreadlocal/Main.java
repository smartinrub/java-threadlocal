package org.smartinrubio.javathreadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getSimpleName());

    private static BookingSession bookingSession = new BookingSession();

    public static void main(String[] args) {

        new Thread("thread-1") {
            @Override
            public void run() {
                BookingSessionThreadLocalManager.setBookingSession(bookingSession);

                // Data stored in a ThreadLocal that belongs to a thread is accessible from the same thread
                LOGGER.info(Thread.currentThread().getName() + " -> " + BookingSessionThreadLocalManager.getBookingSession().getUuid());

                new Thread("thread-child") {
                    @Override
                    public void run() {
                        // If we are in a child thread, we cannot access the parent ThreadLocal
                        LOGGER.info(Thread.currentThread().getName() + " -> " + (BookingSessionThreadLocalManager.getBookingSession() != null ? BookingSessionThreadLocalManager.getBookingSession().getUuid() : "null thread local"));

                        // By wrapping a ThreadLocal we have access to it from different threads
                        NameHandler nameHandler = new NameHandler(bookingSession);
                        nameHandler.saveName("Jose");
                        LOGGER.info(Thread.currentThread().getName() + " -> " + BookingSessionThreadLocalManager.getBookingSession().getUuid() + " - " + BookingSessionThreadLocalManager.getBookingSession().getName());

                    }
                }.start();

                NameHandler nameHandler = new NameHandler(bookingSession);
                nameHandler.saveName("Sergio");
                LOGGER.info(Thread.currentThread().getName() + " -> " + BookingSessionThreadLocalManager.getBookingSession().getUuid() + " - " + BookingSessionThreadLocalManager.getBookingSession().getName());

                // ThreadLocal is removed
                BookingSessionThreadLocalManager.destroyBookingSession();
                LOGGER.info(Thread.currentThread().getName() + " -> " + (BookingSessionThreadLocalManager.getBookingSession() != null ? BookingSessionThreadLocalManager.getBookingSession().getUuid() : "null thread local"));

            }
        }.start();


        new Thread("thread-2") {
            @Override
            public void run() {
                NameHandler nameHandler = new NameHandler(bookingSession);
                nameHandler.saveName("Antonio");
                LOGGER.info(Thread.currentThread().getName() + " -> " + BookingSessionThreadLocalManager.getBookingSession().getUuid() + " - " + BookingSessionThreadLocalManager.getBookingSession().getName());
            }
        }.start();


    }
}
