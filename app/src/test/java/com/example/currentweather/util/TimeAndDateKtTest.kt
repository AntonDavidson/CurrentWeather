package com.example.currentweather.util

import com.example.currentweather.domain.dummy.Dummy
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeAndDateKtTest {
    @Test
    fun `Check right hour extraction from string`() {
        val timeEpoch = extractHourFromTimeEpochString(
            "2024-08-23 ${
                extractHourFromTimeEpochString(
                    Dummy.imperialDummy.location.localtime
                ) + 2 % 23
            }:00"
        )

        assertEquals(timeEpoch, 16)
    }
}