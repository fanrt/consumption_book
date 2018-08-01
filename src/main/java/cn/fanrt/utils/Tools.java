package cn.fanrt.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-01 15:03
 **/
public class Tools {

    /**
     * 数字大写的中文字符
     */
    private static final String[] CHINESECODE = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖",
            "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿",
            "十亿", "百亿", "千亿"};

    /**
     * 数字大写的中文字符
     */
    private static final String[] CHINESECOUNTCODE = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖",
            "点", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "十亿", "百亿", "千亿"};

    /** 唯一实例 */
    private static final Tools INSTANCE = new Tools();

    /** 打印行数 */
    public static final String PRINT_ROW = "5";

    /**
     * Constructor for Tools.
     */
    public Tools() {
        //
    }

    /**
     * 取得实例
     * @return Tools
     */
    public static Tools getInstance() {
        return INSTANCE;
    }

    /**
     * 格式化BigDecimal为字符串
     * @param value 输入的BigDecimal
     * @return 格式化的字符串
     */
    public static String bigDecimalToString(BigDecimal value) {
        String returnVal = value.toString();
        if (returnVal.length() - returnVal.indexOf(".") > 4) {
            returnVal = returnVal.substring(0, returnVal.indexOf(".") + 5);
        } else if (returnVal.indexOf(".") == -1) {
            returnVal = new StringBuffer(returnVal).append(".00").toString();
        }
        return returnVal;
    }

    /** add by pch
     * @param charge 数字金额
     * @return String 大写中文金额
     */
    public static String numericToChinese(String charge) {
        String tempCharge;
        String chargeChinese = "";

        if (charge.indexOf(".") > 0) {
            tempCharge = charge.substring(0, charge.indexOf("."));
            String tempStr = charge.substring(charge.indexOf(".") + 1, charge.length());
            if (tempStr.length() < 2) {
                tempCharge += "0" + tempStr;
            } else {
                tempCharge += tempStr;
            }
        } else {
            tempCharge = charge + "00";
        }
        for (int i = 0; i < tempCharge.length(); i++) {
            chargeChinese += CHINESECODE[Integer.parseInt(tempCharge.substring(i, i + 1))]
                    + CHINESECODE[(10 + tempCharge.length() - i) - 1];
        }
        return chargeChinese + "整";
    }

    /** add by pch
     * @param charge 数字
     * @param unit 单位
     * @return String 大写中文数字
     */
    public static String numericCountToChinese(String charge, String unit) {
        try {
            Double.parseDouble(charge);
        } catch (NumberFormatException ex) {
            return null; //非法的数字字符
        }

        String tempCharge = null;
        String tempCharge2 = null;
        StringBuffer numericChinese = new StringBuffer("");
        if (charge.indexOf(".") > 0) {
            tempCharge = charge.substring(0, charge.indexOf("."));
            tempCharge2 = charge.substring(charge.indexOf(".") + 1, charge.length());
        } else {
            tempCharge = charge;
        }
        for (int i = 0; i < tempCharge.length(); i++) {
            if (!tempCharge.substring(i, i + 1).equals("0")) {
                numericChinese.append(CHINESECOUNTCODE[Integer.parseInt(tempCharge.substring(i, i + 1))])
                        .append(CHINESECOUNTCODE[10 + tempCharge.length() - i - 1]);
            } else {
                if  (!numericChinese.substring(numericChinese.length()-1, numericChinese.length()).equals("零")) {
                    numericChinese.append("零");
                }
            }
        }
        if (null != tempCharge2 ) {
            if (numericChinese.substring(numericChinese.length()-1, numericChinese.length()).equals("零")) {
                numericChinese.delete(numericChinese.length()-1, numericChinese.length());
            }
            numericChinese.append("点");
            for (int i = 0; i < tempCharge2.length(); i++) {
                numericChinese.append(CHINESECOUNTCODE[Integer.parseInt(tempCharge2.substring(i, i + 1))]);
            }
        }
        filterZeor(numericChinese);
        numericChinese.append(unit);

        return numericChinese.toString();
    }

    private static void filterZeor(StringBuffer numericChinese) {
        String entStr = numericChinese.substring(numericChinese.length()-1, numericChinese.length());
        if (entStr.equals("点") || entStr.equals("零")) {
            numericChinese.delete(numericChinese.length()-1, numericChinese.length());
        }
    }

    /** 用于对numericToChinese()方法的测试
     * @param args 输入参数
     */
    public static void main(String[] args) {
//        String ttt = Tools.numericToChinese("200213100.1");
//        String tt = Tools.numericCountToChinese("210013100.10", "千克");
//        String china = "zZ,./?';:*&^%$#@!~`似的⒈⒉-=+_|\"()<>[]{}";
//        System.out.println(findEscapeChar(china));
//        System.out.println("213213100 : " + ttt);
//        System.out.println("4444 : " + tt);
        String tt = Tools.filterDefString("450102001000", "2@2@3@3@3", "0");
        System.out.println("4444 : " + tt);
    }

    /**
     * 将字符串数组转换成List
     * @param strAry 字符串数组
     * @return List strAry中的元素
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getListFromArray(Object[] strAry) {
        List tmpList = new ArrayList();

        for (int i = 0; i < strAry.length; i++) {
            tmpList.add(strAry[i]);
        }
        return tmpList;
    }

    /**
     * 生成序列号
     * @param area 区域标识
     * @param assetId 固定资产编号
     * @param serialNum 流水号
     * @return 新序列号
     */
    public static String getNewSerialNumber(String area, String assetId, String serialNum) {
        StringBuffer newSerial = new StringBuffer(area).append("-").append(assetId).append("-").append(serialNum);
        return newSerial.toString();
    }
    /**
     * 过滤字符串，例如 oriStr="450102001000"    需要过滤最后几位 0  filterStr="0"  ruleStr="2@2@2@3@3"
     * 过滤字符串，例如 oriStr="450102001ttt"    需要过滤最后几位t filterStr="t"  ruleStr="2@2@2@3@3"
     * @param oriStr 源字符串
     * @param ruleStr 过滤规则用  @ 分隔
     * @param filterChar 过滤字符
     * @return
     */
    public static String filterDefString(String oriStr, String ruleStr, String filterChar) {
        if (Tools.isEmptyString(oriStr) || Tools.isEmptyString(ruleStr) || Tools.isEmptyString(filterChar)) {
            return null;
        }
        String[] StrArray = ruleStr.split("@");
        int len = 0;
        for (int i = 0; i < StrArray.length; i++) {
            len = len + (new Integer(StrArray[i])).intValue();
            String tempStr = oriStr.substring(len, oriStr.length());
            for (int j = 0; j < tempStr.length(); j++) {
                if (tempStr.charAt(j) != filterChar.charAt(0)) {
                    break;
                }
                if (j == tempStr.length() -1) {
                    return oriStr.substring(0, len);
                }
            }
        }
        return null;
    }
    /**
     * 根据对象名从源列表中找到对应对象返回列表
     * @param res 源列表
     * @param objectName 指定对象名
     * @return 结果列表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List searchObjectFromList(List res, String objectName) {
        List dest = new ArrayList();
        Object[] object;
        if (null == res ) {
            return dest;
        }
        for (int i = 0; i < res.size(); i++) {
            object = (Object[]) res.get(i);
            for (int j = 0; j < object.length; j++) {
                if (null != object[j] && object[j].getClass().getName().equals(objectName)) {
                    dest.add(object[j]);
                }
            }
        }
        return dest;
    }
    /**
     * 将字符数组拼凑成字符串
     * @param src 源数组
     * @param splitChr 元素之间的分隔符
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    @SuppressWarnings("rawtypes")
    public static String changeArrayToString(Object[] src, String splitChr, boolean quote) {
        List tmpList = getListFromArray(src);
        return changeArrayToString(tmpList, splitChr, quote);
    }
    /**
     * 将字符数组拼凑成字符串,要去空格
     * @param src 源数组
     * @param splitChr 元素之间的分隔符
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    @SuppressWarnings("rawtypes")
    public static String changeArrayToStringTrim(Object[] src, String splitChr, boolean quote) {
        List tmpList = getListFromArray(src);
        return changeArrayToStringTrim(tmpList, splitChr, quote);
    }
    /**
     * 将列表拼凑成字符串,要去空格
     * @param src 源数组
     * @param splitChr 元素之间的分隔符
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    @SuppressWarnings("rawtypes")
    public static String changeArrayToStringTrim(List src, String splitChr, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        Object obj;
        for (int i = 0; i < src.size(); ++i) {
            obj = (null == src.get(i) ) ? src.get(i) : src.get(i).toString().trim();
            buffer.append(quote ? "'" : "").append(obj)
                    .append(quote ? "'" : "").append((null != splitChr  && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }
    /**
     * 将字符数组拼凑成字符串
     * @param src 源数组
     * @param splitChr 元素之间的分隔符
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(List src, String splitChr, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < src.size(); ++i) {
            buffer.append(quote ? "'" : "").append(src.get(i))
                    .append(quote ? "'" : "").append((null != splitChr  && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }
    /**
     * 将对象列表指定的属性拼凑成字符串
     * @param src 对象数组
     * @param property 要组装的对象属性
     * @param splitChr 元素之间的分隔符
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(List src, String property, String splitChr, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        BeanWrapper infoWrapper;
        for (int i = 0; i < src.size(); ++i) {
            infoWrapper = new BeanWrapperImpl(src.get(i));
            buffer.append(quote ? "'" : "").append(infoWrapper.getPropertyValue(property))
                    .append(quote ? "'" : "").append((null != splitChr  && i < src.size() - 1) ? splitChr : "");
        }
        return buffer.toString();
    }
    /**
     * 将Map指定的属性拼凑成字符串
     * @param src Map
     * @param splitChr 元素之间的分隔符
     * @param key 是否取Map的key来组装
     * @param quote 是否用单引号括起元素
     * @return 拼凑成的字符串
     */
    public static String changeArrayToString(Map src, String splitChr, boolean key, boolean quote) {
        StringBuffer buffer = new StringBuffer("");
        for (Iterator iter = src.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            if (key) {
                buffer.append(quote ? "'" : "").append(element.getKey())
                        .append(quote ? "'" : "").append((null != splitChr  && iter.hasNext()) ? splitChr : "");
            } else {
                buffer.append(quote ? "'" : "").append(element.getValue())
                        .append(quote ? "'" : "").append((null != splitChr && iter.hasNext()) ? splitChr : "");
            }
        }
        return buffer.toString();
    }
    /**
     * 将对象列表指定的属性组成MAP
     * @param src 对象列表
     * @param key 要组装的对象的键
     * @param value 要组装的对象的值
     * @return MAP
     */
    public static Map changeArrayToMap(List src, String key, String value) {
        BeanWrapper infoWrapper;
        Map valueMap = new HashMap();
        for (int i = 0; i < src.size(); ++i) {
            infoWrapper = new BeanWrapperImpl(src.get(i));
            if (null != value ) {
                valueMap.put(infoWrapper.getPropertyValue(key), infoWrapper.getPropertyValue(value));
            } else {
                valueMap.put(infoWrapper.getPropertyValue(key), src.get(i));
            }
        }
        return valueMap;
    }
    /**
     * 将src转换成字符数组
     * @param src 源List
     * @return 字符数组
     */
    public static String[] changeListToStringArray(List src) {
        String[] dest = new String[src.size()];
        for (int i = 0; i < src.size(); i++) {
            dest[i] = (null != src.get(i)) ? src.get(i).toString() : "";
        }
        return dest;
    }
    /**
     * 根据查找内容在数组中找到对应的位置
     * @param searchId 查找内容
     * @param searchArray 数组
     * @return searchId在数组中的位置
     */
    public static int findPosition(Object searchId, Object[] searchArray) {
        Object obj;
        if (null == searchId ) {
            return -1;
        }
        for (int i = 0; i < searchArray.length; ++i) {
            obj = searchArray[i];
            if (null == obj ) {
                continue;
            }
            if (String.class.isAssignableFrom(obj.getClass())) {
                obj = obj.toString().trim();
            }
            if (obj.equals(searchId)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 检查字符串sourceString中有无中文字符
     * @param sourceString 源字符串
     * @return 若有中文返回true; 否则false
     */
    public static boolean findEscapeChar(String sourceString) {
        boolean result = false;
        char ch;
        if (null == sourceString  ||0 == sourceString.length() ) {
            return result;
        }
        for (int i = 0; i < sourceString.length(); i++) {
            ch = sourceString.charAt(i);
            if (ch >= 127) {
                return true;
            }
        }
        return result;
    }

    /**
     * 产生年月日+6位随机字母
     * 产生规则: 年月日+6位随机字母
     * @return
     */
    public static String generateLoginName() {
        final String CHAR_SEQ = "ABCDEFGHIJKLMNPQRSTUVW"; // 排除字母O
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        return String.format("%4d%02d%02d%c%c%c%c%c%c", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())),
                CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())),CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())));
    }

    /**
     * 日+6位随机字母
     * 产生规则: 年月日+6位随机字母
     * @return
     */
    public static String generateLoginPassword() {
        final String CHAR_SEQ = "ABCDEFGHJKMNPQRSTUVW"; // 排除字母O、I、L
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        return String.format("%02d%c%c%c%c%c%c", calendar.get(Calendar.DAY_OF_MONTH),
                CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())),
                CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())),CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())), CHAR_SEQ.charAt(random.nextInt(CHAR_SEQ.length())));
    }

    /**
     * 判断字符串是否为非空字符串（null和零长度的字符串都是空）
     * @param str 源字符串
     * @return true：非空字符串 false：空字符串
     */
    public static boolean isNotEmptyString(String str) {
        boolean result = true;

        if (null != str && 0 != str.trim().length()) {
            return result;
        }
        return false;
    }
    /**
     * 判断字符串是否为空（null和零长度的字符串都是空）
     * @param str 源字符串
     * @return true：空字符串 false：非空
     */
    public static boolean isEmptyString(String str) {
        boolean result = true;

        if (null == str  || 0 == str.trim().length() ) {
            return result;
        }
        return false;
    }
    /**
     * 判断Long是否为非空
     * @param num 源字符串
     * @return true：非空字符串 false：空字符串
     */
    public static boolean isNotEmptyLong(Long num) {
        boolean result = true;

        if (null != num) {
            return result;
        }
        return false;
    }

    /**
     * 判断字符串是否为空（null和零长度的字符串都是空）并且要等于equ指定的字符串
     * @param str 源字符串
     * @param equ 要相等的字符串
     * @return true：等于equ字符串 false：空或不等于equ
     */
    public static boolean isNotEmptyString(String str, String equ) {
        if (isEmptyString(str) || isEmptyString(equ)) {
            return false;
        }
        return str.equals(equ);
    }

    /**
     * 判断list是否为非空列表（null和零长度的字符串都是空）
     * @param list 源列表
     * @return true：非空列表 false：空列表
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmptyList(List list) {
        boolean result = true;

        if (null != list && !list.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 判断Set是否为非空列表（null和零长度的字符串都是空）
     * @param set 源Set
     * @return true：非空Set false：空Set
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmptySet(Set set) {
        boolean result = true;

        if (null != set && !set.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 判断collection 是否为非空数据集（null和数据集都是空）
     * @param collection 源collection
     * @return true：非空collection false：空collection
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmptyCollection(Collection collection) {
        boolean result = true;

        if (null != collection && !collection.isEmpty()) {
            return result;
        }
        return false;
    }

    /**
     * 判断数组是否非空
     * @param array
     * @return true：非空array false：空array
     */
    public static boolean isNotEmptyArray(Object[] array){
        return null != array && array.length != 0;
    }

    /**
     * 判断字符串是否为非空Map（null和零长度的字符串都是空）
     * @param map 源map
     * @return true：非空map false：空map
     */
    public static boolean isNotEmptyMap(Map map) {
        boolean result = true;

        if (null != map && !map.isEmpty()) {
            return result;
        }
        return false;
    }
    /**
     * 获取数组中指定下标的内容
     * @param array 数组
     * @param index 下标
     * @return 返回值
     */
    public static <T> T getArrayValue(T[] array, int index) {
        if (null == array || array.length == 0 || array.length - 1 < index) {
            return null;
        }
        return array[index];
    }
    /**
     * 用于检查excel导入信息项目必填项
     * @param checkValue 要检查的值
     * @param title 错误时显示的标题
     * @param lineMsg 出错信息
     * @return 是否为空,不为空返回真
     * @author PCH
     * @create Feb 5, 2017
     */
    public static boolean checkNotNull(String checkValue, String title, StringBuffer lineMsg, Integer rowNumber) {
        if (null == checkValue || "".equals(checkValue)) {
            lineMsg.append("\t\t\n\n第" + rowNumber.intValue() + "行中<font style='font-size:16px; color:red; text-decoration:none'>" + title + "</font>不能为空。");
            return false;
        }
        return true;
    }

    /**
     * 用于检查excel导入信息长度
     * @param checkValue 要检查的值
     * @param title 错误时显示的标题
     * @param length 指定长度
     * @param lineMsg 出错信息
     * @return 是否符合指定长度, 小于或等于指定长度返回真
     * @author PCH
     * @create Feb 5, 2017
     */
    public static boolean checkLength(String checkValue, String title, int length, StringBuffer lineMsg, Integer rowNumber) {
        if (null != checkValue && checkValue.length() > length) {
            lineMsg.append("\t\t\n\n第" + rowNumber.intValue() + "行中<font style='font-size:16px; color:red; text-decoration:none'>" + title + "</font>长度不能大于" + length + "。");
            return false;
        }
        return true;
    }

    /**
     * 将bean的属性及值转换成Map键值对应
     * @param bean
     * @param suffix 后缀
     * @param keyToKey 是否将大写转换成小写并在前面加上 _
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Map beanPropertyToMap(Object bean, String suffix, Map keyToKey) {
        Map result = new HashMap();
        if (null == bean ) return result;

        if (null == suffix ) suffix = "";

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
            if (null != manyToMany && FetchType.LAZY.equals(manyToMany.fetch())) {
                continue;
            }
            OneToMany oneToMany = field.getAnnotation(OneToMany.class);
            if (null != oneToMany && FetchType.LAZY.equals(oneToMany.fetch())) {
                continue;
            }
            ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            if (null != manyToOne && FetchType.LAZY.equals(manyToOne.fetch())) {
                continue;
            }
            field.setAccessible(true);
            try {
                String key = keyToKey==null||null == keyToKey.get(field.getName())?field.getName():keyToKey.get(field.getName()).toString();
                result.put(key + suffix, field.get(bean));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将list 中 bean的属性及值转换成Map键值对应
     * @param list
     * @param keyToKey 是否将大写转换成小写并在前面加上 _
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List beanPropertyToMap(List list, Map keyToKey) {
        List resultList = new ArrayList();
        if (null == list || list.isEmpty()) return resultList;

        for (Iterator iter = list.iterator();iter.hasNext();) {
            resultList.add(Tools.beanPropertyToMap(iter.next(), null, keyToKey));
        }
        return resultList;
    }

    /**
     * 是否为基本类型
     * @param obj
     * @return
     */
    public static boolean isBasicType(Object obj) {
        if (null == obj) {
            return true;
        }
        if (int.class.isInstance(obj) ||
                Integer.class.isInstance(obj) ||
                long.class.isInstance(obj) ||
                Long.class.isInstance(obj) ||
                double.class.isInstance(obj) ||
                Double.class.isInstance(obj) ||
                char.class.isInstance(obj) ||
                Character.class.isInstance(obj) ||
                boolean.class.isInstance(obj) ||
                Boolean.class.isInstance(obj) ||
                java.util.Date.class.isInstance(obj) ||
                java.sql.Date.class.isInstance(obj) ||
                String.class.isInstance(obj) ||
                BigDecimal.class.isInstance(obj) ||
                StringBuffer.class.isInstance(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 是否为基本类型
     * @param clazz
     * @return
     */
    public static boolean isBasicType(Class clazz) {
        if (null == clazz) {
            return true;
        }
        if (int.class.equals(clazz) ||
                Integer.class.equals(clazz) ||
                long.class.equals(clazz) ||
                Long.class.equals(clazz) ||
                double.class.equals(clazz) ||
                Double.class.equals(clazz) ||
                char.class.equals(clazz) ||
                Character.class.equals(clazz) ||
                boolean.class.equals(clazz) ||
                Boolean.class.equals(clazz) ||
                java.util.Date.class.equals(clazz) ||
                java.sql.Date.class.equals(clazz) ||
                String.class.equals(clazz) ||
                BigDecimal.class.equals(clazz) ||
                StringBuffer.class.equals(clazz) ||
                Class.class.equals(clazz)) {
            return true;
        }
        return false;
    }

    /**
     * 根据属性名获取对象属性
     * @param propertyName
     * @param obj
     * @return
     */
    public static Serializable obtainPropertyValue(String propertyName, Object obj) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(obj);
        return (Serializable) wrapper.getPropertyValue(propertyName);
    }

    /**
     * 从集合中获取此主键关联的对象
     * @param primaryKey
     * @param id
     * @param collection
     * @return
     */
    public static Object obtainObjectByPrimaryKey(String primaryKey, Serializable id, Collection collection) {
        for (Object obj : collection) {
            Serializable value = obtainPropertyValue(primaryKey, obj);
            if (null != value&& value.equals(id)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 判断日期对象是否为空
     * @param obj
     * @return 有值返回true
     */
    public static boolean hasValue(Object obj) {
        if (null == obj) return false;
        if (obj.toString().equals("")) return false;
        return true;
    }

    /**
     * 判断日期对象是否为空
     * @param date
     * @return 有值返回true
     */
    public static boolean hasValue(Date date) {
        if (null == date) return false;
        return true;
    }

    /**
     * 判断字符串对象是否为空
     * @param str
     * @return 有值返回true
     */
    public static boolean hasValue(String str) {
        return StringUtils.hasText(str);
    }

    /**
     * 判断字符串对象是否为空
     * @param l
     * @return 有值返回true
     */
    public static boolean hasValue(Long l) {
        if (l == null) return false;
        return true;
    }

    /**
     * 获取字符串src按splitChr分割后得到的Long型数组
     * @param src 要分割的字符串
     * @param splitChr 分割标识符
     * @return Long[] 分割后的Long型数组
     */
    public static Long[] splitStringToLongArray(String src, String splitChr) {
        if (isEmptyString(src) || isEmptyString(splitChr)) {
            return null;
        }
        String[] srcArray = src.split(splitChr);
        Long[] longArray = new Long[srcArray.length];
        for (int i = 0; i < srcArray.length; i++) {
            longArray[i] = Long.parseLong(srcArray[i]);
        }
        return longArray;
    }

    /**
     * 获取按指定字符串补充好位数的字符串
     * @param sourceStr 原字符串
     * @param strLen 字符串长度
     * @param fillString 补充前缀的字符串
     * @return 原字符串 =“1” 字符串长度=6 补充前缀的字符串 =“0”  返回： 000001
     */
    public static String getFillPreString(String sourceStr, int strLen, String fillString) {
        if (isEmptyString(sourceStr) || strLen < 1 || isEmptyString(fillString)) {
            return null;
        }
        String returnStr = sourceStr;
        while (strLen - returnStr.length() > 0) {
            returnStr = fillString + returnStr;
        }
        return returnStr;
    }

    /**
     * 扩展数字字符的长度
     * @param currentNum String 当前数字字符
     * @param len int 需要补 “0” 的长度
     * @param fillString 补充前缀的字符串
     * @return String 例如 currentNum = "0013",  len = 6 , fillString= "0" 返回的串为 ： "000013"
     */
    public static String getLenNextId(String currentNum, int len, String fillString) {
        int currentIdNum = (new Integer(currentNum)).intValue();
        String nextIdNumStr = new Integer(currentIdNum + 1).toString();
        while (nextIdNumStr.length() < len) {
            nextIdNumStr = fillString + nextIdNumStr;
        }
        return nextIdNumStr;
    }

    /**
     * 将字节转出 显示文件单位的的大小
     * @param size
     * @return
     */
    public static String formatSize(long size) {
        DecimalFormat df = new DecimalFormat("###.#");
        String size_s;
        if (size < 1024) {
            size_s = size + "B";
        } else if (size < (1024 * 1024)) {
            size_s = df.format((float) size / 1024) + "KB";
        } else {
            size_s = df.format((float) size / (1024 * 1024)) + "MB";
        }
        return size_s;
    }
}
