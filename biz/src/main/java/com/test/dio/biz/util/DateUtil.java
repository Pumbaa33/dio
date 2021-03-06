package com.test.dio.biz.util;

import com.test.dio.biz.domain.DateParamDO;
import com.test.dio.biz.domain.DateType;
import org.joda.time.*;

import java.util.Date;

public class DateUtil {

    public static String genStrWithPattern(Date date, String pattern) {
        return LocalDate.fromDateFields(date).toString(pattern);
    }

    public static LocalDate getFirstDayOfYear() {
        return LocalDate.now().dayOfYear().withMinimumValue();
    }

    /**
     * 获取指定时间范围的开始时间 结束时间
     *
     * @param interval     开始结束时间间隔
     * @param intervalType 时间间隔类型
     * @param endType      截止时间类型
     * @return
     */
    public static DateParamDO getDateInterval(Integer interval, DateType intervalType, DateType endType) {

        DateParamDO result = new DateParamDO();
        LocalDate startDate;
        LocalDate endDate;

        if (DateType.DAY == endType) {
            endDate = LocalDate.now();
        } else if (DateType.WEEK == endType) {
            endDate = LocalDate.now();
        } else if (DateType.MONTH == endType) {
            endDate = LocalDate.now().dayOfMonth().withMinimumValue();
        } else {
            endDate = LocalDate.now().dayOfYear().withMinimumValue();
        }

        if (DateType.DAY == intervalType) {
            startDate = endDate.minusDays(interval);
        } else if (DateType.WEEK == intervalType) {
            startDate = endDate.minusWeeks(interval);
        } else if (DateType.MONTH == intervalType) {
            startDate = endDate.minusMonths(interval);
        } else {
            startDate = endDate.minusYears(interval);
        }

        result.setStartDate(startDate.toDate());
        result.setEndDate(endDate.toDate());

        return result;
    }

    public static String getSpanWithMon(Date startDate, Date endDate) {
        LocalDate start = LocalDate.fromDateFields(startDate);
        LocalDate end = LocalDate.fromDateFields(endDate);

        return String.valueOf(Months.monthsBetween(start, end).getMonths());
    }

    public static String getSpanWithDay(Date startDate, Date endDate) {
        LocalDate start = LocalDate.fromDateFields(startDate);
        LocalDate end = LocalDate.fromDateFields(endDate);

        return String.valueOf(Days.daysBetween(start, end).getDays());
    }

    public static String getSpanWithHour(Date startDate, Date endDate) {
        LocalDateTime start = LocalDateTime.fromDateFields(startDate);
        LocalDateTime end = LocalDateTime.fromDateFields(endDate);

        return String.valueOf(Hours.hoursBetween(start, end).getHours());
    }
}
