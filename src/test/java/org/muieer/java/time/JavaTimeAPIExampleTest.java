package org.muieer.java.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class JavaTimeAPIExampleTest {

    @Test
    void testEpochMilliToDayOfWeek() {
        assertEquals(
                LocalDate.now().getDayOfWeek().getValue(),
                JavaTimeAPIExample.epochMilliToDayOfWeek(System.currentTimeMillis()));
    }
}
