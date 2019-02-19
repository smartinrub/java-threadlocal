package org.smartinrubio.javathreadlocal;

import java.util.UUID;

public class BookingSession {
    private final UUID uuid =  UUID.randomUUID();
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
