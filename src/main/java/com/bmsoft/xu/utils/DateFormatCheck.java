package com.bmsoft.xu.utils;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class DateFormatCheck {
    private static Logger logger = Logger.getLogger("DateFormatCheck.clas");

    @SuppressWarnings("finally")
    public static String FormatDate(String dateStr) {

        HashMap<String, String> dateRegFormat = new HashMap<String, String>();
        dateRegFormat.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D*$",
                "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D*$",
                "yyyy-MM-dd-HH");//2014-03-12 12
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd");//2014-03-12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D*$", "yyyy-MM");//2014-03
        dateRegFormat.put("^\\d{4}\\D*$", "yyyy");//2014
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
        dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$",
                "yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
        dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yy-MM-dd");//14.10.18(年.月.日)
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)
        dateRegFormat.put("^\\d{1,2}\\D+\\s*\\d{4}\\D*$", "MM-yyyy");//4.2017(4月2017年）
        dateRegFormat.put("^\\d{4}\\D+\\s*\\d{1,2}\\D*$", "yyyy-MM");//2014.9(2014年9月）
        dateRegFormat.put("^\\D{7,8}$", "YYYYMM");//2014.9(二零一四年九月）


        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatter3 = new SimpleDateFormat("yyyy-MM");
        DateFormat formatter2;
        String dateReplace;
        String strSuccess = dateStr;
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
                    if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
                            || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//13:05:34 或 13:05 拼接当前日期
                        dateStr = curDate + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\s*\\d{4}\\D*$") || key.equals("^\\d{4}\\D+\\s*\\d{1,2}\\D*$")) {
                        dateReplace = dateStr.replaceAll("\\D+", "-");
                        // System.out.println(dateRegExpArr[i]);
                        strSuccess = formatter3.format(formatter2.parse(dateReplace));
                        break;
                    }
                    dateReplace = dateStr.replaceAll("\\D+", "-");
                    // System.out.println(dateRegExpArr[i]);
                    strSuccess = formatter1.format(formatter2.parse(dateReplace));
                    break;
                }
            }
        } catch (Exception e)

        {
            System.err.println("-----------------日期格式无效:" + dateStr);
            throw new Exception("日期格式无效");
        } finally

        {
            logger.info("日期格式与预设的不匹配，采用默认String");
            return strSuccess;
        }

    }
    public static String KPMGPublishtime(String publishtime){
        String date = "";
        String dd = publishtime.substring(0,2);
        String yyyy = publishtime.substring(7,11);
        String mm = publishtime.substring(3,6);
        if("Jan".equals(mm))
            mm = "01";
        if("Feb".equals(mm))
            mm = "02";
        if("Mar".equals(mm))
            mm = "03";
        if("Apr".equals(mm))
            mm = "04";
        if("May".equals(mm))
            mm = "05";
        if("Jun".equals(mm))
            mm = "06";
        if("Jul".equals(mm))
            mm = "07";
        if("Aug".equals(mm))
            mm = "08";
        if("Sep".equals(mm))
            mm = "09";
        if("Nov".equals(mm))
            mm = "11";
        if("Oct".equals(mm))
            mm = "10";
        if("Dec".equals(mm))
            mm = "12";

        date = yyyy+"-"+mm+"-"+dd;
        return date;
    }
    public static String ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }

    public static void writeFile(String filePath, String sets)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }

    public static String gettime() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:MM:SS");
        String gettime = format.format(new Date());
        return gettime;
    }




    public static void main(String[] args) throws Exception {
       /* String sets = ReadFile("audit_manage.json");
        JSONArray jsonarray = JSONArray.fromObject(sets);
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject jo = jsonarray.getJSONObject(i);
            String publish = jo.getString("publishtime");
            jo.put("publishtime", FormatDate(publish));
        }
        String newfile = jsonarray.toString();
        File file = new File("new2.json");
        FileUtils.writeStringToFile(file,newfile);*/
        //String sets = ReadFile("data.json");
        // System.out.println(sets);
        /*Type type = new TypeToken<ArrayList<BasicJsonEO>>(){}.getType();
        ArrayList<BasicJsonEO> list = new Gson().fromJson(sets,type);
        System.out.println(list.size());*/
      /*  StringBuffer sb = new StringBuffer();
        sb.append(sets);

        String change = sb.toString().replaceAll("\"","\\\\\"");
        File file = new File("test.json");
        FileUtils.writeStringToFile(file,change);*/
        String a = "</tr></table></td>\\t\\t</tr>\\t</table>\"";




       /* for (int i = 0; i < jsonarray;.size(); i++) {
            JSONObject jo = jsonarray.getJSONObject(i);
            String publish = jo.getString("content");
            jo.put("publishtime", FormatDate(publish));
        }*/
        String sets = ReadFile("HKex_公告及通告_2017-11-22.json");
        File file = new File("new121.json");
        String change = sets.replaceAll("\"", "\\\\\"");
        FileUtils.writeStringToFile(file, change);
    }
}

