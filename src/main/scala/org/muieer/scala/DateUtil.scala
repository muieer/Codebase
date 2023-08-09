package org.muieer.scala

import java.text.SimpleDateFormat
import java.time.temporal.{ChronoUnit, TemporalUnit}
import java.util.{Calendar, Date}

object DateUtil {

  /*
  * @return _._1:yyyyMMddHH _._2:yyyyMMdd _._3:HH
  * */
  def calculateDate(inputDate: String, amount: Int, unit: TemporalUnit = ChronoUnit.DAYS,
                    simpleDateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")): (String, String, String) = {
    val parseDate = simpleDateFormat.parse(inputDate)
    // output format
    val simpleDateFormatNew = new SimpleDateFormat("yyyyMMddHH")
    val dateAndHour = simpleDateFormatNew.format(new Date(parseDate.toInstant.plus(amount, unit).toEpochMilli))
    val date = dateAndHour.substring(0, 8)
    val hour = dateAndHour.substring(8)
    (dateAndHour, date, hour)
  }

  // 将System.currentTimeMillis()的值转化为 Calendar.DAY_OF_WEEK
  def millisToDayOfWeek(millis: Long): Int = {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(millis)
    calendar.get(Calendar.DAY_OF_WEEK)
  }

  // 将System.currentTimeMillis()的值转化为Calendar.HOUR_OF_DAY
  def millisToHourOfDay(millis: Long): Int = {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(millis)
    calendar.get(Calendar.HOUR_OF_DAY)
  }

}
