package com.tracking.backend.mapper;

import java.time.format.DateTimeFormatter;

public final class DateTimeFormats {

    public static final String DATETIME_PATTERN = "dd/MM/yyyy HH:mm";
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");

    private DateTimeFormats() {
    }
}
