package com.ater.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**有关预产期计算的算法
 * @author 大师♂罗莊
 *
 */
public class PregnancyHelp {

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        // 请输入您的最后一次来月经的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2018-6-18");
        Calendar d = Calendar.getInstance();
        d.setTime(d1);
        setValue(d);

        getbornmanual(d);
        getBorn("2018-6-21");
        getCurrent("2018-6-21");


    }

    /**
     * @param localCalendar1
     *            末次月经时间
     * @throws ParseException
     */
    private static void setValue(Calendar localCalendar1) throws ParseException {

        // 取今天时间
        Calendar Calendartoday = Calendar.getInstance();
        // 克隆一个保存下
        Calendar Calendarborn = (Calendar) localCalendar1.clone();

        // 取今天和末次月经时间相差多少天
        long daysBetweennumber = daysBetween(localCalendar1.getTime(),
                Calendartoday.getTime());

        // 算出多少周
        int WeekBetweennumber = (int) daysBetweennumber / 7;
        // 零多少天
        long i1 = (daysBetweennumber + 1) % 7;

        // 电脑计算预产期，只需在末次月经第一天加上9个月零1周（280天）即可
        Calendarborn.add(Calendar.DAY_OF_MONTH, 280);

        // 计算预产期距离今天还有几天
        long borndayBetween = -1
                + daysBetween(Calendartoday.getTime(), Calendarborn.getTime());

        // 为输出年月日做准备
        int bornyear = Calendarborn.get(Calendar.YEAR);
        int bornmonth = 1 + Calendarborn.get(Calendar.MONTH);
        // MONTH加1的原因： public static final int MONTH 指示月份的 get 和 set
        // 的字段数字。这是一个特定于日历的值。在格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0；
        // 最后一个月取决于一年中的月份数。 简单来说，因为这个值的初始值是0，因此我们要用它来表示正确的月份时就需要加1。
        int bornday = Calendarborn.get(Calendar.DAY_OF_MONTH);

        System.out.println("您已经怀孕" + daysBetweennumber + "天");
        String current = (WeekBetweennumber + "周" + i1 + "天");
        System.out.println("怀孕周数" + current);
        getMediMonthSpace(WeekBetweennumber);
        System.out.println("怀孕月数(直接减去法)"
                + getMonthSpace(localCalendar1, Calendartoday) + "个月");
        String born = (borndayBetween + "天");
        System.out.println("距离宝宝出生还有" + born);
        String borndayString = (String.valueOf(bornyear) + "年"
                + String.valueOf(bornmonth) + "月" + String.valueOf(bornday) + "日");
        System.out.println("预产期为" + borndayString);
        Calendarbornmanual(localCalendar1);
    }

    /**
     * 计算方法：从末次月经第一天算起，月份减3，如不够时则加9，日数加7。
     *
     * @param cal
     *            从末次月经
     */
    static void Calendarbornmanual(Calendar cal) {
        Calendar temp = (Calendar) cal.clone();
        int month1 = temp.get(Calendar.MONTH) + 1;
        if (month1 < 3) {
            temp.add(Calendar.MONTH, 9);
        } else {
            temp.add(Calendar.MONTH, -3);
            temp.add(Calendar.YEAR, 1);
        }
        temp.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("人手工计算的预产期为");
        outprint(temp);
    }
    //计算方法计算预产期
   public static Date getbornmanual(Calendar cal) {
        Calendar temp = (Calendar) cal.clone();
        int month1 = temp.get(Calendar.MONTH) + 1;
        if (month1 < 3) {
            temp.add(Calendar.MONTH, 9);
        } else {
            temp.add(Calendar.MONTH, -3);
            temp.add(Calendar.YEAR, 1);
        }
        temp.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("人手工计算的预产期为");
        outprint(temp);
        return temp.getTime();
    }
    //获取怀孕周数
    public static String getCurrent(String pregnancyDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(pregnancyDate));
        calendar.add(Calendar.MONTH, -9);// 月份减9
        calendar.add(Calendar.DATE, -7);// 日期减15
        calendar.getTime();
        // 取今天时间
        Calendar Calendartoday = Calendar.getInstance();
        // 克隆一个保存下
        Calendar Calendarborn = (Calendar) calendar.clone();
        long daysBetweennumber = daysBetween( calendar.getTime(),Calendartoday.getTime());
        // 算出多少周
        int weekBetweennumber = (int) daysBetweennumber / 7;
        int i1=(int) daysBetweennumber % 7;
        System.out.println("您已经怀孕" + daysBetweennumber + "天");
        String current = ("孕"+weekBetweennumber + "周" + i1 + "天");
        System.out.println("怀孕周数" + current);
        return current;
    }

    //获取预产期距离今天还有几天
    public static long getBorn(String pregnancyDate  ) throws ParseException {
        // 取今天时间
        Calendar Calendartoday = Calendar.getInstance();
        // 克隆一个保存下

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(pregnancyDate));
        Calendar Calendarborn = (Calendar) calendar.clone();

      // 计算预产期距离今天还有几天
        long borndayBetween =
                daysBetween(Calendartoday.getTime(), calendar.getTime());

        return borndayBetween;
    }

    /**
     * @param  pregnancyDate
     *            怀孕多少周
     *      预产期减9个月15天 得到怀孕期
    和当前日期相减
    得到怀孕天数
    除7 得到周数
     * @throws ParseException
     */
    public static int getWeek(String pregnancyDate ) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(pregnancyDate));
        calendar.add(Calendar.MONTH, -9);// 月份减1
        calendar.add(Calendar.DATE, -7);// 日期减1
        calendar.getTime();
        // 取今天时间
        Calendar Calendartoday = Calendar.getInstance();
        // 克隆一个保存下
        Calendar Calendarborn = (Calendar) calendar.clone();
        long daysBetweennumber = daysBetween( calendar.getTime(),Calendartoday.getTime());
        // 算出多少周
        int weekBetweennumber = (int) daysBetweennumber / 7;
        System.out.println("周" + weekBetweennumber);
      return weekBetweennumber;
    }
    /**
     * 计算两个日期之间相差的天数 需要用calendar，因为Date返回的永远是UTC时间，也就是时区永远比我们晚8个小时。
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static long daysBetween(Date smdate, Date bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return between_days;
    }

    static void outprint(Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 打印
        System.out.println(dateFormat.format(cal.getTime()));

    }

    /**
     * 有人问我32w+（32周）怀孕几个月
     *
     * @param week
     *            输入周数
     */
    public static void getMediMonthSpace(int week) {
        // 此算法同原昆明某三甲医院产科主任算法一致，所以为什么我的算法和她医生算出来的一致
        int month = week * 7 / 30;// 周数算出月数，可以按28天或者30天算
        int weekleave = week * 7 % 30;// 周数算出剩下的天数
        // 也可以通过/7算出周数，大家自己去补充了
        System.out.print("怀孕月数(计算法)" + month + "个月");
        System.out.println(weekleave + "天");
    }

    /**
     * 算两个日期差几个月，不能用Calendar.MONTH直接相减，否则2012年11月和2013年11月就是0
     *
     * @param scalendar
     *            日期1
     * @param calendar
     *            日期2
     * @return 两个日期差几个月
     */
    public static int getMonthSpace(Calendar scalendar, Calendar calendar) {

        int year1 = calendar.get(Calendar.YEAR);
        int year2 = scalendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int month2 = scalendar.get(Calendar.MONTH);

        int count = 0;
        if (year1 == year2 && month1 > month2) {
            count = month1 - month2;
        } else if (year1 == year2 && month1 < month2) {
            count = month2 - month1;
        } else if (year1 > year2 && month1 > month2) {
            count = (year1 - year2) * 12 + (month1 - month2);
        } else if (year1 > year2 && month1 < month2) {
            count = (year1 - year1) * 12 + (month2 - month1);
        } else if (year1 < year2 && month1 > month2) {
            count = (year2 - year1) * 12 + month1 - month2;
        } else if (year1 < year2 && month1 < month2) {
            count = (year2 - year1) * 12 + month2 - month1;
        }
        if (count < 0) {

            count = Math.abs(count);
        }
        return count;
    }


}
