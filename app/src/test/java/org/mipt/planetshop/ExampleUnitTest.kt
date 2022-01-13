package org.mipt.planetshop

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.absoluteValue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun dateLength(str1:String, str2:String):Boolean{
        val date1 = LocalDate.parse(str1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        val date2 = LocalDate.parse(str2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        val period = Period.between(date1, date2)

        if (period.years != 0)
            return false
        if (period.months > 3)
            return false

        return true
    }

    @Test
    fun TestDateLength() {
        assertEquals(dateLength("2022-01-01", "2022-03-01"), true)
        assertEquals(dateLength("2022-01-01", "2022-04-01"), true)
        assertEquals(dateLength("2022-01-01", "2022-05-01"), false)
        assertEquals(dateLength("2021-01-01", "2022-05-01"), false)

    }

}