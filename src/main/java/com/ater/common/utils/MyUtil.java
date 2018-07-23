package com.ater.common.utils;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyUtil {

	/**
	 * 判断是否是double类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean IsDouble(String str) {
		try {
			Double.valueOf(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	/**
	 * 取出一个指定长度大小的随机正整数.
	 *
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}



	/**
	 * 金额验证
	 * 
	 * @param str
	 * @return true:是金额;false:不是金额
	 */
	public static boolean IsMoney(String str) {
		// 判断小数点后2位的数字的正则表达式
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		java.util.regex.Matcher match = pattern.matcher(str);
		return match.matches();
	}

	/**
	 * 两数相减
	 * 

	 * @return
	 */
	public static double SubDouble(double db1, double db2) {
		BigDecimal value1 = new BigDecimal(String.valueOf(db1));
		BigDecimal value2 = new BigDecimal(String.valueOf(db2));
		return value1.subtract(value2).doubleValue();
	}

	/**
	 * 两数相加
	 * 
 	 * @return
	 */
	public static double AddDouble(double db1, double db2) {
		BigDecimal value1 = new BigDecimal(String.valueOf(db1));
		BigDecimal value2 = new BigDecimal(String.valueOf(db2));
		return value1.add(value2).doubleValue();
	}

	/**
	 * 两数相加(四舍五入保留2位)
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static double AddDouble2(double db1, double db2) {
		BigDecimal value1 = new BigDecimal(String.valueOf(db1));
		BigDecimal value2 = new BigDecimal(String.valueOf(db2));
		BigDecimal total = value1.add(value2);
		return total.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 取得double值
	 * 
	 * @param value
	 * @return
	 */
	public static double GetDouble(Double value) {
		if (value == null) {
			return 0;
		} else {
			return value;
		}
	}

	/**
	 * 结束时间
	 * 
	 * @param date
	 * @return yyyy-MM-dd 23:59:59日期
	 */
	public static Date GetEndTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);    
        int minute = cal.get(Calendar.MINUTE);    
        int second = cal.get(Calendar.SECOND);  
        
        // 时分秒（毫秒数）    
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;   
        
        // 凌晨00:00:00    
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);    

		// 凌晨23:59:59
		cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59
				* 60 * 1000 + 59 * 1000);

		return cal.getTime();
	}

	/**
	 * 文件名称
	 * 
	 * @return
	 */
	public static String GetFileName() {
		int temp = (int) (Math.random() * 9000 + 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		return sdf.format(new Date()) + "_" + new Integer(temp).toString()
				+ ".png";
	}
	
	/**
	 * 文件名称
	 * 
	 * @return
	 */
	public static String GetFileName(String end) {
		int temp = (int) (Math.random() * 9000 + 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		return sdf.format(new Date()) + "_" + new Integer(temp).toString()
				+ end;
	}

	/**
	 * 文件名称(时间+随机数)
	 * 
	 * @return
	 */
	public static String GetFileNameRandom() {
		return GetCurrentTime() + GetRandom4();
	}

	/**
	 * 得到当前时间
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public static String GetCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		return sdf.format(new Date());
	}

	/**
	 * 得到当前时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String FormatCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 时间转年月日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String DateTimeToString(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}

	/**
	 * 时间转年月日
	 * 
	 * @return yyyy年MM月dd日
	 */
	public static String DateTimeToDateStr(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(time);
	}

	/**
	 * 取得4位随机数
	 * 
	 * @return 4位随机数
	 */
	public static String GetRandom4() {
		Random random = new Random();
		int x = random.nextInt(8999);
		int y = x + 1000;
		return String.valueOf(y);
	}


	
	/**
	 * 日期格式化
	 * 
	 * @return
	 */
	public static String formatDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static boolean isEmpty(String value) {
		if (value == null || value.equals("")) {
			return true;
		} else
			return false;
	}
	
	/**
	 * 判断文件类型（是否是excel）
	 * 
	 * @param myFileName
	 * @return
	 */
	public static boolean checkFileType(String myFileName) {
		boolean flag = false;
		String postfix = myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
		if (postfix.equals(".xls") || postfix.equals(".xlsx")) {
			flag = true;
		}
		return flag;
	}
	// 验证密码
	public static boolean isPassword(String password) {
		String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,14}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(password);
		return m.find();
	}

	// 验证手机号码^1[3|4|5|7|8][0-9]\d{4,8}$
	public static boolean isMobileNO(String mobiles) {
		String regExp ="^1[3|4|5|7|8][0-9]{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.find();
	}
	//随机生成token
	public static String generateToken(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	//随机生成主键Id
	public static String generateId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	//计算验证码是否过期
	public static double diffMin(Date time){
		long diff = System.currentTimeMillis() -time.getTime();//这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
		double minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
		return minutes;
	}

}
