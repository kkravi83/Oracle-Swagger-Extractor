package com.infinity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TryOut {

    class Constants {
        public static final String SQL_MASTER_PRODUCT_ID = "MASTER_PRODUCT_ID";
        public static final String TIME_ZONE_EST = "EST";
        public static final String TIME_ZONE_CST = "CST";
        public static final String TIME_ZONE_MST = "MST";
        public static final String TIME_ZONE_PST = "PST";

        public static final int CST_DIFF_100 = 100;
        public static final int MST_DIFF_200 = 200;
        public static final int PST_DIFF_300 = 300;
        public static final int TOTAL_TIME_2400 = 2400;
        public static final int ORDER_PRT_TIME_1500 = 1500;
        public static final int ORDER_PRT_TIME_LENGTH_4 = 4;
        public static final int TOTAL_TIME_1200 = 1200;
        public static final String DEFAULT_EARLY_DATE = "00:01:00";
        public static final String DEFAULT_LATE_DATE = "23:59:59";

    }

    public static String getDateSub(final String dateStr, final int days) throws ParseException {

        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // final Date date = formatter.parse(dateStr);

        final Calendar c = Calendar.getInstance();

        // Setting the date to the given date
        c.setTime(formatter.parse(dateStr));

        // Number of Days to sub
        c.add(Calendar.DAY_OF_MONTH, days);
        // Date after adding the days to the given date

        return formatter.format(c.getTime());

    }

    public static void main(final String[] args) throws Exception {

        /*
         * System.out.println(isPrintDateInThePast("2020-02-17", "EST"));
         *
         * final DateFormat displayFormatYYYYMMDD = new
         * SimpleDateFormat("yyyy-MM-dd");
         *
         * displayFormatYYYYMMDD.parse("2020-02-18");
         *
         * final Date todayInEST = new
         * SimpleDateFormat("yyyy-MM-dd HH:mm:ssXXX").
         * parse("2020-02-18 02:00:00-05:00");
         *
         * final SimpleDateFormat dfCST = new SimpleDateFormat();
         * dfCST.setTimeZone(TimeZone.getTimeZone("PST"));
         * System.out.println("TODAY in CST " + dfCST.format(todayInEST));
         */
        /*
         * final DateFormat shipDTF = new
         * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); final Date dt =
         * shipDTF.parse("2020-01-08T05:25:12.123+00:00");
         * System.out.println(dt);
         */

        /*
         *
         * final SimpleDateFormat sdfUTC = new
         * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
         *
         * final Date dt = sdfUTC.parse("2020-02-18T02:00:00-05:00");
         *
         * final SimpleDateFormat outTz = new
         * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
         * outTz.setTimeZone(TimeZone.getTimeZone("PST"));
         * System.out.println(outTz.format(dt));
         *
         * ZoneId.getAvailableZoneIds().stream().forEach(tz -> {
         * System.out.println(tz); });
         *
         * System.out.println("Zone ID for EST -> " +
         * ZoneId.of(TimeZone.getTimeZone("US/Pacific").getID()));
         *
         * final String orderPrtTimeStr = "16:00:11PM"; final String
         * whseTimeZone = "EST";
         *
         * final long curTime = System.currentTimeMillis(); final Date yesterday
         * = new Date(curTime - 24 * 60 * 60 * 1000);
         *
         * final Date date = yesterday; isPrintDateInThePast(date, "EST");
         *
         * String orderPrtTime = orderPrtTimeStr.replaceAll(":", "").trim();
         *
         * if (orderPrtTime.length() > Constants.ORDER_PRT_TIME_LENGTH_4) {
         * orderPrtTime = orderPrtTime.substring(0,
         * Constants.ORDER_PRT_TIME_LENGTH_4); }
         *
         * int orderPrtTimeInt = Integer.parseInt(orderPrtTime);
         *
         * if (orderPrtTimeInt < Constants.TOTAL_TIME_1200) { if
         * (orderPrtTimeStr.contains("PM")) { orderPrtTimeInt = orderPrtTimeInt
         * + Constants.TOTAL_TIME_1200; } else if
         * (orderPrtTimeStr.contains("AM")) { orderPrtTimeInt = orderPrtTimeInt
         * + 0; } else { throw new Exception(); } }
         *
         * final SimpleDateFormat format = new
         * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
         * format.setTimeZone(TimeZone.getTimeZone("UTC")); final String dateStr
         * = format.format(date);
         *
         * String orderPrtDate = dateStr.split("T")[0];
         *
         * int orderPrtTimeIntNew = 0; if
         * (whseTimeZone.equals(Constants.TIME_ZONE_EST)) { orderPrtTimeIntNew =
         * orderPrtTimeInt; } else if
         * (whseTimeZone.equals(Constants.TIME_ZONE_PST)) { orderPrtTimeInt =
         * orderPrtTimeInt - Constants.PST_DIFF_300; if (orderPrtTimeInt < 0) {
         * orderPrtTimeIntNew = orderPrtTimeInt + Constants.TOTAL_TIME_2400;
         * orderPrtDate = getDateSub(orderPrtDate, -1); } else {
         * orderPrtTimeIntNew = orderPrtTimeInt; }
         *
         * } else if (whseTimeZone.equals(Constants.TIME_ZONE_CST)) {
         * orderPrtTimeInt = orderPrtTimeInt - Constants.CST_DIFF_100; if
         * (orderPrtTimeInt < 0) { orderPrtTimeIntNew = orderPrtTimeInt +
         * Constants.TOTAL_TIME_2400; orderPrtDate = getDateSub(orderPrtDate,
         * -1); } else { orderPrtTimeIntNew = orderPrtTimeInt; }
         *
         * } else if (whseTimeZone.equals(Constants.TIME_ZONE_MST)) {
         * orderPrtTimeInt = orderPrtTimeInt - Constants.MST_DIFF_200; if
         * (orderPrtTimeInt < 0) { orderPrtTimeIntNew = orderPrtTimeInt +
         * Constants.TOTAL_TIME_2400; orderPrtDate = getDateSub(orderPrtDate,
         * -1); } else { orderPrtTimeIntNew = orderPrtTimeInt; } }
         *
         * System.out.println("orderPrintTimeStr= " + orderPrtTimeStr +
         * "orderPrintTime new " + orderPrtTimeIntNew);
         *
         * if (isPrintDateInThePast(date, whseTimeZone)) {
         * adjustPickupDateForPastPrntDate(orderPrtTimeIntNew, whseTimeZone); }
         * else { adjustPickupDateForCurrentPrntDate(orderPrtDate,
         * orderPrtTimeIntNew); }
         */
        final String time = Instant.now().toString();
        System.out.println(time);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Date dt = sdf.parse("2020-06-15T09:44:46.444Z");
        System.out.println(dt.getTime());
    }

    public static String getDateStringInTz(final Date date, final String tz) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if (date != null) {
            format.setTimeZone(TimeZone.getTimeZone(tz));
            return format.format(date);
        } else {
            return "";
        }
    }

    public static String getDate(final String dateStr) throws ParseException {

        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = formatter.parse(dateStr);

        final SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");

        return newFormat.format(date);

    }

    public static String getAddedDate(final String dateStr, final int days) throws ParseException {

        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // final Date date = formatter.parse(dateStr);

        final Calendar c = Calendar.getInstance();

        // Setting the date to the given date
        c.setTime(formatter.parse(dateStr));

        // Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, days);

        final SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");

        return newFormat.format(c.getTime());

    }

}
