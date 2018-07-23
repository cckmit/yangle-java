package com.ater.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 */
public class DateUtils {

 /*   public static String getFormatADate(long time) {
        SimpleDateFormat formatA = new SimpleDateFormat(Constant.DATE_FORMAT_A);
        return formatA.format(time);
    }*/
   //根据开始时间和结束时间 周期类型算出周期
   public static int selectcycle(Date startTime,Date endTime,Integer cycleType) {
       if(cycleType==1){
           /* 周巡查 1000*60*24 1440000 */
           Long aa =endTime.getTime()-startTime.getTime();
          int cycleCount= (int) (aa/(1000*60*60*24*7));
          return cycleCount;
       }else if(cycleType==2){
           Calendar cal1 = new GregorianCalendar();
           cal1.setTime(endTime);
           Calendar cal2 = new GregorianCalendar();
           cal2.setTime(startTime);
           int cycleCount = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                           - cal2.get(Calendar.MONTH);
           return cycleCount;
             //月巡查
       }else {
           //季巡查
           Calendar cal1 = new GregorianCalendar();
           cal1.setTime(endTime);
           Calendar cal2 = new GregorianCalendar();
           cal2.setTime(startTime);
           int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                   - cal2.get(Calendar.MONTH);
           int cycleCount=c/3;
           return cycleCount;
       }

   }
   //根据开始时间算出下一个的开始时间
    public static Date selectStartTime(Date startTime,Integer cycleType){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");  //字符串转换
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startTime.getTime());
       if(cycleType==1){
              // 周巡查
            c.add(Calendar.DATE, 7);//天后的日期
            Date date= new Date(c.getTimeInMillis()); //将c转换成Date
           return date;
           }else if(cycleType==2){
           //月巡查
           c.add(Calendar.MONTH,1); //将当前日期加一个月
            Date date= new Date(c.getTimeInMillis()); //将c转换成Date
           return date;
           }else{
           //季循环
           c.add(Calendar.MONTH,3); //将当前日期加三个月
           Date date= new Date(c.getTimeInMillis()); //将c转换成Date
           return date;
           }
    }

    /**
     * 获取距当前时间指定时间后的日期
     */
    public static Date getDate(){
        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();
        now += Constant.EXPIRE_TIME;
        cal.setTimeInMillis(now);
//        cal.set(Calendar.DATE,Constant.EXPIRE_TIME);
        return cal.getTime();
    }

    public static void main(String[] args) {
        Date date1=new Date("2018-01-01");
        Date date2=new Date("2018-05-01");
        selectcycle(date1,date2,1);
        selectStartTime(date1,1);
        System.out.println(" selectcycle(date1,date2,1)"+ selectcycle(date1,date2,1));
        System.out.println("selectStartTime(date1,1)"+ selectStartTime(date1,1));

    }
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }
}
