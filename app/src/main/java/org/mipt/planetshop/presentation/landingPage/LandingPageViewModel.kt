package org.mipt.planetshop.presentation.landingPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor() : ViewModel() {

    private val _startDateState = MutableLiveData(DateState.VALID)
    val startDateState: LiveData<DateState> = _startDateState

    private val _endDateState = MutableLiveData(DateState.VALID)
    val endDateState: LiveData<DateState> = _endDateState

    fun searchValidation(startDate: String, endDate: String):Boolean {

        val dateStateType = isValid(startDate, endDate)
        return dateStateType == DateState.VALID

    }

    private fun isValid(startDate: String, endDate: String): DateState {

        if (startDate.isBlank()) {
            _startDateState.value = DateState.EMPTY
            return DateState.EMPTY
        }
        if (endDate.isBlank()) {
            _endDateState.value = DateState.EMPTY
            return DateState.EMPTY
        }
        val dateStart: LocalDate
        val dateEnd: LocalDate

        try {
            dateStart = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            _startDateState.value = DateState.VALID
        }
        catch (exc:DateTimeParseException)
        {
            _startDateState.value = DateState.ERROR_FORMAT
            return DateState.ERROR_FORMAT
        }

        try {
            dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            _endDateState.value = DateState.VALID

            if (!isDateChronologyValid(dateStart, dateEnd)) {
                _startDateState.value = DateState.ERROR_CHRONOLOGY
                _endDateState.value = DateState.ERROR_CHRONOLOGY
                return DateState.ERROR_CHRONOLOGY
            }

            if (!isDateBeforeNowValid(dateStart)) {
                _startDateState.value = DateState.ERROR_FUTURE_DATE
                return DateState.ERROR_FUTURE_DATE
            }
            if (!isDateBeforeNowValid(dateEnd)) {
                _endDateState.value = DateState.ERROR_FUTURE_DATE
                return DateState.ERROR_FUTURE_DATE
            }

            if (!isDateAfter2010Valid(dateStart)){
                _startDateState.value = DateState.ERROR_TOOEARLY
                return DateState.ERROR_TOOEARLY
            }

            if (!isDateBetweenValid(dateStart, dateEnd)) {
                _startDateState.value = DateState.ERROR_BETWEEN
                _endDateState.value = DateState.ERROR_BETWEEN
                return DateState.ERROR_BETWEEN
            }
        } catch (exc:DateTimeParseException)
        {
            _endDateState.value = DateState.ERROR_FORMAT
            return DateState.ERROR_FORMAT
        }


        _startDateState.value = DateState.VALID
        _endDateState.value = DateState.VALID

        return  DateState.VALID

    }



}


enum class DateState {
    EMPTY, VALID, ERROR_BETWEEN, ERROR_CHRONOLOGY, ERROR_FORMAT, ERROR_FUTURE_DATE,  ERROR_TOOEARLY
}

//*
//  isDateBetweenValid()
//  Input date1: LocalDate, date2: LocalDate
//  Используется как вычисление разницы между месяцами, введенными пользователем
//  Функция возвращает true, если разность меньше 3 месяцев(учитывая API NASA planet)
//  return Boolean
//  аналогично используем прочие функции валидации.
// *
fun isDateBetweenValid(date1: LocalDate, date2: LocalDate):Boolean{
    val period = Period.between(date1, date2)

    if (period.years != 0)
        return false
    if (period.months > 3)
        return false

    return true
}

private fun isDateChronologyValid(dateStart: LocalDate, dateEnd: LocalDate): Boolean {
    return (dateEnd >= dateStart)
}

private fun isDateBeforeNowValid(dateStart: LocalDate): Boolean {
    return !(dateStart > LocalDate.now())
}

//*
//  isDateAfter2000Valid()
//  Input dateStart: LocalDate
//  Функция возвращает true, если начальная дата больше 2010 года(учитывая API NASA planet)
//  return Boolean
// *
private fun isDateAfter2010Valid(dateStart: LocalDate): Boolean {
    return (dateStart.year >= 2015)
}

