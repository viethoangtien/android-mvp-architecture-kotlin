package com.soict.hoangviet.baseproject.utils

import android.text.TextUtils
import android.util.Log

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateUtil {

    val DATE_FORMAT_1 = "yyyy-MM-dd"
    val DATE_FORMAT_2 = "HH:mm:ss"
    val DATE_FORMAT_3 = "hh:mm a"
    val DATE_FORMAT_4 = "dd/MM/yyyy"
    val DATE_FORMAT_5 = "hh:mmaa dd/MM/yyyy"
    val DATE_FORMAT_6 = "MM/dd/yyyy"
    val DATE_FORMAT_7 = "hh:mm a"
    val DATE_FORMAT_8 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"
    val DATE_FORMAT_9 = "$DATE_FORMAT_1 $DATE_FORMAT_2"
    val DATE_FORMAT_10 = "hh:mmaa"
    val DATE_FORMAT_11 = "HH:mm dd/MM/yyyy"
    val DATE_FORMAT_12 = "ddHHmmss"
    val DATE_FORMAT_13 = "yyyy-MM-dd HH:mm:ss"
    val DATE_FORMAT_14 = "EEEE"
    val DATE_FORMAT_15 = "HH:mm"
    val DATE_FORMAT_16 = "dd"
    val DATE_FORMAT_17 = "MM"
    val DATE_FORMAT_18 = "yyyy"
    val DATE_FORMAT_19 = "HH:mm dd/MM/yyyy"
    val DATE_FORMAT_20 = "dd-MM-yyyy HH:mm"
    val DATE_FORMAT_22 = "yyyy-MM-dd HH:mm"
    val DATE_FORMAT_21 = "dd-MM-yyyy"
    val DATE_FORMAT_23 = "dd/MM/yyyy"
    val DATE_FORMAT_24 = "dd/MM/yyyy HH:mm"
    val DATE_FORMAT_25 = "HH"
    val DATE_FORMAT_26 = "hh:mm a - dd/MM/yyyy"
    val DATE_FORMAT_27 = "MMM dd"
    val DATE_FORMAT_28 = "yyyy-MM-dd HH:mm:ss.SSSSSS"

    val TIME_0H = "00:00:00"
    val TIME_7H = "07:00:00"
    val TIME_START = 0
    val TIME_END_MINUTE = 59
    val TIME_END_HOUR = 23

    val MINUTE_MILLIS = TimeUnit.MINUTES.toMillis(1)
    val HOUR_MILLIS = TimeUnit.HOURS.toMillis(1)
    val DAY_MILLIS = TimeUnit.DAYS.toMillis(1)

    val currentDayName: String
        get() {
            val cal = Calendar.getInstance()
            when (cal.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> return "Thứ 2"
                Calendar.TUESDAY -> return "Thứ 3"
                Calendar.WEDNESDAY -> return "Thứ 4"
                Calendar.THURSDAY -> return "Thứ 5"
                Calendar.FRIDAY -> return "Thứ 6"
                Calendar.SATURDAY -> return "Thứ 7"
                else -> return "Chủ Nhật"
            }
        }

    // current time
    val currentDateName: String
        get() {
            val time = StringBuilder("Ngày ")
            val cal = Calendar.getInstance()
            if (cal.get(Calendar.DAY_OF_MONTH) < 10)
                time.append("0")
            time.append(cal.get(Calendar.DAY_OF_MONTH))
            time.append(" tháng ")
            if (cal.get(Calendar.MONTH) + 1 < 10)
                time.append("0")
            time.append(cal.get(Calendar.MONTH) + 1)
            time.append(" năm ")
            time.append(cal.get(Calendar.YEAR))
            return time.toString()
        }

    fun getDateValue(value: String): String {
        if (value.length == 1) {
            return "0${value}"
        }
        return value
    }

    fun getDayName(day: String): String {
        when (day) {
            "Monday" -> return "Thứ Hai"
            "Tuesday" -> return "Thứ Ba"
            "Wednesday" -> return "Thứ Tư"
            "Thursday" -> return "Thứ Năm"
            "Friday" -> return "Thứ Sáu"
            "Saturday" -> return "Thứ Bảy"
            "Sunday" -> return "Chủ Nhật"
            else -> return day
        }
    }

    @Throws(ParseException::class)
    fun fortmatDateFromTimeString(time: String?): String? {
        var result = ""
        if (time == null || time.isEmpty()) {
            return result
        }
        val publishDate = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).parse(time)
        val currentDate = Date()
        var hour = 0
        var min = 0
        var day = 0
        val totalSec = currentDate.time - publishDate.time
        if (totalSec > 0) {
            day = (totalSec / (60 * 60 * 24 * 1000)).toInt()
            if (day >= 7) {
                return formatDate(time, DATE_FORMAT_11, DATE_FORMAT_4)
            } else if (day >= 2) {
                result = "$day ngày trước"
            } else if (day > 0) {
                result = "Hôm qua"
            } else {
                hour = (totalSec / (60 * 60 * 1000)).toInt()
                if (hour > 0) {
                    result = "$hour giờ trước"
                } else {
                    min = totalSec.toInt() / (60 * 1000)
                    if (min == 0) {
                        result = "Vừa xong"
                    } else {
                        result = "$min phút trước"
                    }
                }
            }
        }
        return result
    }

    //    @Throws(ParseException::class)
    fun formatDate(date: String, initDateFormat: String, resultDateFormat: String): String? {
        val initFormater = SimpleDateFormat(initDateFormat, Locale.US)
        val resultFormatter = SimpleDateFormat(resultDateFormat, Locale.US)
        try {
            return resultFormatter.format(initFormater.parse(date))
        } catch (e: ParseException) {
            Log.d(DateUtil::class.java.simpleName, "formatDate: ${e.message}")
        }
        return null
    }

    fun convertStringToCalendar(time: String, format: String): Calendar {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            cal.time = sdf.parse(time)
        } catch (e: ParseException) {
            Log.d(DateUtil::class.java.simpleName, "convertStringToCalendar: ${e.message}")
        }

        return cal
    }

    fun getMonthLunarName(month: Int): String? {
        when (month) {
            1 -> return "Giêng"
            2 -> return "Hai"
            3 -> return "Ba"
            4 -> return "Tư"
            5 -> return "Năm"
            6 -> return "Sáu"
            7 -> return "Bảy"
            8 -> return "Tám"
            9 -> return "Chín"
            10 -> return "Mười"
            11 -> return "Một"
            12 -> return "Chạp"
            else -> return null
        }
    }

    fun getMonthSolarName(month: Int): String? {
        when (month) {
            1 -> return "Tháng Một"
            2 -> return "Tháng Hai"
            3 -> return "Tháng Ba"
            4 -> return "Tháng Tư"
            5 -> return "Tháng Năm"
            6 -> return "Tháng Sáu"
            7 -> return "Tháng Bảy"
            8 -> return "Tháng Tám"
            9 -> return "Tháng Chín"
            10 -> return "Tháng Mười"
            11 -> return "Tháng Mười Một"
            12 -> return "Tháng Mười Hai"
            else -> return null
        }
    }

    fun getMonthSolarShortName(month: Int): String? {
        when (month) {
            1 -> return "Một"
            2 -> return "Hai"
            3 -> return "Ba"
            4 -> return "Tư"
            5 -> return "Năm"
            6 -> return "Sáu"
            7 -> return "Bảy"
            8 -> return "Tám"
            9 -> return "Chín"
            10 -> return "Mười"
            11 -> return "11"
            12 -> return "12"
            else -> return null
        }
    }

    fun convertTimeStampToDateFormat(pattern: String, timestamp: Long): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date(timestamp * 1000)
        return dateFormat.format(date)
    }

    fun getTimeStampBuCurrentDate() = Date().time / 1000

    fun getStampCurrentDay(pattern: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getStampByDate(date: Date, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getStampByCalendar(calendar: Calendar, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getStampByCalendar(calendar: Calendar, pattern: String, locale: Locale): String {
        val dateFormat = SimpleDateFormat(pattern, locale)
        return dateFormat.format(calendar.time)
    }

    fun getDateByStamp(stamp: String, pattern: String): Date {
        try {
            return SimpleDateFormat(pattern, Locale.getDefault()).parse(stamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Date()
    }

    fun getCalendarByStamp(stamp: String, pattern: String): Calendar {
        try {
            val c = Calendar.getInstance()
            c.time = SimpleDateFormat(pattern, Locale.getDefault()).parse(stamp)
            return c
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Calendar.getInstance()
    }

    fun getCalendarByStamp(stamp: String, pattern: String, locale: Locale): Calendar {
        try {
            val c = Calendar.getInstance()
            c.time = SimpleDateFormat(pattern, locale).parse(stamp)
            return c
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Calendar.getInstance()
    }

    /**
     * Compare two Calendar by Years, Months and Days without time in day
     *
     * @param c1
     * @param c2
     * @return -1 if c1 before c2, 0 if c1 and c2 are same day, 1 if c1 after c2
     */
    fun compareTwoCalendarOnlyDay(c1: Calendar, c2: Calendar): Int {
        return if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            Integer.compare(c1.get(Calendar.DAY_OF_YEAR), c2.get(Calendar.DAY_OF_YEAR))
        } else if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            1
        } else {
            -1
        }
    }

    fun getTimeFormateFromSecond(second: Long): String {
        val hour: Double = second.toDouble() / 3600
//        var result = ""
//        if (hour > 0) {
//            var minute = second - hour * 3600
//            result = if (minute > 0) {
//                minute /= 60
//                "$hour giờ $minute phút"
//            }else{
//                "$hour"
//            }
//        }else{
//            val minute = second/60
//            result = "$minute phút"
//        }

        return String.format("%.1f", hour)
    }

    fun getTimeDisplay(cal: Calendar): String {
        // current time
        val time = StringBuilder("")
        if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            time.append("0")
        time.append(cal.get(Calendar.DAY_OF_MONTH))
        time.append("/")
        if (cal.get(Calendar.MONTH) + 1 < 10)
            time.append("0")
        time.append(cal.get(Calendar.MONTH) + 1)
        time.append("/")
        time.append(cal.get(Calendar.YEAR))
        return time.toString()
    }

    fun getTimeAgo(calendar: Calendar?): String? {
        if (calendar == null)
            return null
        val now = Calendar.getInstance().timeInMillis
        val time = calendar.timeInMillis
        val diff = now - time
        return if (diff < TimeUnit.MINUTES.toMillis(1)) {
            "Vừa xong"
        } else if (diff < 60 * MINUTE_MILLIS) {
            (diff / MINUTE_MILLIS).toString() + " phút trước"
        } else if (diff < 24 * HOUR_MILLIS) {
            (diff / HOUR_MILLIS).toString() + " giờ trước"
        } else if (diff < 2 * 24 * HOUR_MILLIS) {
            "Hôm qua"
        } else if (diff < 7 * 24 * HOUR_MILLIS) {
            (diff / DAY_MILLIS).toString() + " ngày trước"
        } else {
            getTimeDisplay(calendar)
        }
    }

    fun getTimeStill(dateTimeStart: Calendar?, timeStart: String): String? {
        if (dateTimeStart == null)
            return null
        if (TextUtils.isEmpty(timeStart)) {
            setTimeDefault(dateTimeStart, true)
        }
        //        if (TextUtils.isEmpty(timeEnd)) {
        //            setTimeDefault(dateTimeEnd, false);
        //        }
        val current = Calendar.getInstance()
        // on going
        //        if (current.getTimeInMillis() >= dateTimeStart.getTimeInMillis() && current.getTimeInMillis() <= dateTimeEnd.getTimeInMillis()) {
        //            return "Đang diễn ra";
        //        } else
        if (current.timeInMillis < dateTimeStart.timeInMillis) {
            if (TextUtils.isEmpty(timeStart) && current.get(Calendar.YEAR) == dateTimeStart.get(
                    Calendar.YEAR
                )
            ) {
                return "Còn " + (dateTimeStart.get(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)) + " ngày nữa"
            }
            val now = current.timeInMillis
            val time = dateTimeStart.timeInMillis
            val diff = time - now
            return if (diff < 60 * MINUTE_MILLIS) {
                "Còn " + diff / MINUTE_MILLIS + " phút nữa"
            } else if (diff < 24 * HOUR_MILLIS) {
                "Còn " + diff / HOUR_MILLIS + " giờ nữa"
            } else if (diff < 365 * 24 * HOUR_MILLIS) {
                "Còn " + diff / DAY_MILLIS + " ngày nữa"
            } else {
                getTimeDisplay(dateTimeStart)
            }
        } else {
            return "Đã diễn ra"
        }
    }

    fun getDayMonthDisplay(calendar: Calendar?): String? {
        if (calendar == null)
            return null
        val sb = StringBuilder()
        sb.append(calendar.get(Calendar.DAY_OF_MONTH)).append(" ")
            .append(getMonthSolarName(calendar.get(Calendar.MONTH) + 1))
        return sb.toString()
    }

    fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(
            Calendar.DAY_OF_YEAR
        )
    }

    fun getDateTimeDisplayByFormat(format: String, calendar: Calendar): String? {
        try {
            val dateFormat = SimpleDateFormat(format)
            return dateFormat.format(calendar.timeInMillis)
        } catch (e: Exception) {

        }

        return null
    }

    fun getDayDuring(cal1: Calendar, cal2: Calendar): Long {
        return (cal1.timeInMillis - cal2.timeInMillis) / DAY_MILLIS + 1
    }

    fun checkBetweenCalendar(timeStart: String, timeEnd: String, timeCurrent: String): Boolean {
        try {

            val time1 = SimpleDateFormat(DATE_FORMAT_2).parse(timeStart)
            val calendar1 = Calendar.getInstance()
            calendar1.time = time1

            val time2 = SimpleDateFormat(DATE_FORMAT_2).parse(timeEnd)
            val calendar2 = Calendar.getInstance()
            calendar2.time = time2
            calendar2.add(Calendar.DATE, 1)

            val d = SimpleDateFormat(DATE_FORMAT_2).parse(timeCurrent)
            val calendar3 = Calendar.getInstance()
            calendar3.time = d
            calendar3.add(Calendar.DATE, 1)

            val x = calendar3.time
            if (x.after(calendar1.time) && x.before(calendar2.time)) {
                return true
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return false
    }

    fun getAge(dob: Calendar, now: Calendar): Int {
        val year1 = now.get(Calendar.YEAR)
        val year2 = dob.get(Calendar.YEAR)
        var age = year1 - year2
        val month1 = now.get(Calendar.MONTH)
        val month2 = dob.get(Calendar.MONTH)
        if (month2 > month1) {
            age--
        } else if (month1 == month2) {
            val day1 = now.get(Calendar.DAY_OF_MONTH)
            val day2 = dob.get(Calendar.DAY_OF_MONTH)
            if (day2 > day1) {
                age--
            }
        }
        return age
    }

    fun isOver30Day(date: String): Boolean {
        val day30 = 30L * 24 * 60 * 60 * 1000
        val giveTime = getDateByStamp(date, DATE_FORMAT_11).time
        return Date().time - giveTime > day30
    }

    fun setTimeDefault(calendar: Calendar, isStart: Boolean): Calendar {
        if (isStart) {
            calendar.set(Calendar.HOUR_OF_DAY, TIME_START)
            calendar.set(Calendar.MINUTE, TIME_START)
            calendar.set(Calendar.SECOND, TIME_START)
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, TIME_END_HOUR)
            calendar.set(Calendar.MINUTE, TIME_END_MINUTE)
        }
        return calendar
    }
}
