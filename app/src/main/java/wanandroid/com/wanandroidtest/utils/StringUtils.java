package wanandroid.com.wanandroidtest.utils;

import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/16
 *     desc  : 字符串相关工具类
 * </pre>
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        if (object instanceof String) {
            return isEmpty((String) object);
        }
        if (object instanceof Collection) {
            return isListEmpty((Collection) object);
        }
        return false;
    }

    /**
     * 如果有一项为空，则返回false
     * 接着对第一个list进行遍历，如果某一项第二个list里面没有，则返回false
     * 还要再将两个list反过来比较，因为可能一个list是另一个list的子集
     * 如果成功遍历结束，返回true
     *
     * @param list0
     * @param list1
     * @return
     */
    public static boolean isListEqual(List list0, List list1) {
        if (list0 == list1) {
            return true;
        }
        if (list0 == null || list1 == null) {
            return false;
        }
        if (list0.size() != list1.size()) {
            return false;
        }
        for (Object o : list0) {
            if (!list1.contains(o)) {
                return false;
            }
        }
        for (Object o : list1) {
            if (!list0.contains(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isListEmpty(Collection list) {
        return list.isEmpty() || list.size() == 0;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 格式化空字符串
     *
     * @param content
     * @return
     */
    public static String CS(String content) {
        if (null == content || "".equals(content)) {
            return "";
        }
        return content;
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) {
            return s;
        }
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    private static int[] pyValue = new int[]{-20319, -20317, -20304, -20295, -20292, -20283, -20265, -20257, -20242,
            -20230, -20051, -20036, -20032,
            -20026, -20002, -19990, -19986, -19982, -19976, -19805, -19784, -19775, -19774, -19763, -19756, -19751,
            -19746, -19741, -19739, -19728,
            -19725, -19715, -19540, -19531, -19525, -19515, -19500, -19484, -19479, -19467, -19289, -19288, -19281,
            -19275, -19270, -19263, -19261,
            -19249, -19243, -19242, -19238, -19235, -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006,
            -19003, -18996, -18977, -18961,
            -18952, -18783, -18774, -18773, -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697, -18696,
            -18526, -18518, -18501, -18490,
            -18478, -18463, -18448, -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201, -18184, -18183,
            -18181, -18012, -17997, -17988,
            -17970, -17964, -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752, -17733, -17730, -17721,
            -17703, -17701, -17697, -17692,
            -17683, -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427, -17417, -17202, -17185, -16983,
            -16970, -16942, -16915, -16733,
            -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470, -16465, -16459, -16452, -16448, -16433,
            -16429, -16427, -16423, -16419,
            -16412, -16407, -16403, -16401, -16393, -16220, -16216, -16212, -16205, -16202, -16187, -16180, -16171,
            -16169, -16158, -16155, -15959,
            -15958, -15944, -15933, -15920, -15915, -15903, -15889, -15878, -15707, -15701, -15681, -15667, -15661,
            -15659, -15652, -15640, -15631,
            -15625, -15454, -15448, -15436, -15435, -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369,
            -15363, -15362, -15183, -15180,
            -15165, -15158, -15153, -15150, -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121, -15119,
            -15117, -15110, -15109, -14941,
            -14937, -14933, -14930, -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902, -14894, -14889,
            -14882, -14873, -14871, -14857,
            -14678, -14674, -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429, -14407, -14399, -14384,
            -14379, -14368, -14355, -14353,
            -14345, -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135, -14125, -14123, -14122, -14112,
            -14109, -14099, -14097, -14094,
            -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907, -13906, -13905, -13896, -13894, -13878,
            -13870, -13859, -13847, -13831,
            -13658, -13611, -13601, -13406, -13404, -13400, -13398, -13395, -13391, -13387, -13383, -13367, -13359,
            -13356, -13343, -13340, -13329,
            -13326, -13318, -13147, -13138, -13120, -13107, -13096, -13095, -13091, -13076, -13068, -13063, -13060,
            -12888, -12875, -12871, -12860,
            -12858, -12852, -12849, -12838, -12831, -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556,
            -12359, -12346, -12320, -12300,
            -12120, -12099, -12089, -12074, -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798, -11781,
            -11604, -11589, -11536, -11358,
            -11340, -11339, -11324, -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041, -11038, -11024,
            -11020, -11019, -11018, -11014,
            -10838, -10832, -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533, -10519, -10331, -10329,
            -10328, -10322, -10315, -10309,
            -10307, -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254};

    private static String[] pyStr = new String[]{"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao",
            "bei", "ben", "beng", "bi", "bian",
            "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai",
            "chan", "chang", "chao", "che",
            "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci",
            "cong", "cou", "cu", "cuan",
            "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dian", "diao", "die",
            "ding", "diu", "dong", "dou", "du",
            "duan", "dui", "dun", "duo", "i", "en", "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou",
            "fu", "ga", "gai", "gan", "gang",
            "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun",
            "guo", "ha", "hai", "han", "hang",
            "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun",
            "huo", "ji", "jia", "jian",
            "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan",
            "kang", "kao", "ke", "ken",
            "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan",
            "lang", "lao", "le", "lei", "leng",
            "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan",
            "lue", "lun", "luo", "ma", "mai",
            "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu",
            "mo", "mou", "mu", "na", "nai",
            "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning",
            "niu", "nong", "nu", "nv", "nuan",
            "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao",
            "pie", "pin", "ping", "po", "pu",
            "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun",
            "ran", "rang", "rao", "re",
            "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao",
            "se", "sen", "seng", "sha",
            "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan",
            "shuang", "shui", "shun",
            "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te",
            "teng", "ti", "tian", "tiao",
            "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei",
            "wen", "weng", "wo", "wu", "xi",
            "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya",
            "yan", "yang", "yao", "ye", "yi",
            "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze",
            "zei", "zen", "zeng", "zha",
            "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai",
            "zhuan", "zhuang", "zhui",
            "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};

    /**
     * 单个汉字转成ASCII码
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的ascii码，否则返回-1
     */
    private static int oneCn2ASCII(String s) {
        if (s.length() != 1) {
            return -1;
        }
        int ascii = 0;
        try {
            byte[] bytes = s.getBytes("GB2312");
            if (bytes.length == 1) {
                ascii = bytes[0];
            } else if (bytes.length == 2) {
                int highByte = 256 + bytes[0];
                int lowByte = 256 + bytes[1];
                ascii = (256 * highByte + lowByte) - 256 * 256;
            } else {
                throw new IllegalArgumentException("Illegal resource string");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ascii;
    }

    /**
     * 单个汉字转成拼音
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的拼音，否则返回{@code null}
     */
    private static String oneCn2PY(String s) {
        int ascii = oneCn2ASCII(s);
        if (ascii == -1) {
            return null;
        }
        String ret = null;
        if (0 <= ascii && ascii <= 127) {
            ret = String.valueOf((char) ascii);
        } else {
            for (int i = pyValue.length - 1; i >= 0; i--) {
                if (pyValue[i] <= ascii) {
                    ret = pyStr[i];
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 获得第一个汉字首字母
     *
     * @param s 单个汉字字符串
     * @return 拼音
     */
    public static String getPYFirstLetter(String s) {
        if (isSpace(s)) {
            return "";
        }
        String first, py;
        first = s.substring(0, 1);
        py = oneCn2PY(first);
        if (py == null) {
            return null;
        }
        return py.substring(0, 1);
    }

    /**
     * 中文转拼音
     *
     * @param s 汉字字符串
     * @return 拼音
     */
    public static String cn2PY(String s) {
        String hz, py;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            hz = s.substring(i, i + 1);
            py = oneCn2PY(hz);
            if (py == null) {
                py = "?";
            }
            sb.append(py);
        }
        return sb.toString();
    }

    /**
     * 判断TextView的内容宽度是否超出其可用宽度
     *
     * @param tv
     * @return
     */
    public static boolean isOverFlowed(TextView tv) {
        int availableWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight();
        Paint textViewPaint = tv.getPaint();
        float textWidth = textViewPaint.measureText(tv.getText().toString());
        return textWidth > availableWidth;
    }

    /**
     * 将String没四个字符添加空格
     *
     * @param text 目标文字
     * @return String添加空格
     */

    public static String stringAddSpace(String text) {
        if (isNull(text)) {
            return "";
        }
        String out = "";
        String regex = "(.{4})";
        out = text.replaceAll(regex, "$1 ");
        return out;
    }

    /**
     * 字符串截取 换行
     *
     * @param str 目标文本
     * @param len 每隔几个字
     * @return 文本
     */
    public static String toMultiLine(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        for (int index = len; index < str.length(); index += len + 1) {
            sb.insert(index, "\n");
        }
        return sb.toString();
    }

    /**
     * 在第二字后面加空格
     *
     * @param text String
     * @return 返回加空格的字符串
     */
    public static String stringAdd2Space(String text) {
        String result = "";
        result = text.substring(0, 2) + "    " + text.substring(2);
        return result;
    }

//    /**
//     * @param times_remain 秒
//     * @param tv           设置的textView
//     */
//    public static void updateTextView(long times_remain, TextView tv) {
//        long times_remainMS = times_remain * 1000;
//        if (times_remainMS <0) {
//            tv.setText(R.string.str_order_overed);
//            return;
//        }
//        //秒钟
//        long time_second = (times_remainMS / 1000) % 60;
//        String str_second;
//        if (time_second < 10) {
//            str_second = "0" + time_second;
//        } else {
//            str_second = "" + time_second;
//        }
//
//        long time_temp = ((times_remainMS / 1000) - time_second) / 60;
//        //分钟
//        long time_minute = time_temp % 60;
//        String str_minute;
//        if (time_minute < 10) {
//            str_minute = "0" + time_minute;
//        } else {
//            str_minute = "" + time_minute;
//        }
//
//        time_temp = (time_temp - time_minute) / 60;
//        //小时
//        long time_hour = time_temp;
//        String str_hour;
//        if (time_hour < 10) {
//            str_hour = "0" + time_hour;
//        } else {
//            str_hour = "" + time_hour;
//        }
//        tv.setText("支付剩余时间："+str_minute + ":" + str_second);
//    }

    /**
     * 是否是http链接
     *
     * @param input
     * @return
     */
    public static Boolean isHttpPic(String input) {
        if (input != null && !"".equals(input) && input.length() > 3) {
            String content = StringCut(input, 1, 4);

            if ("http".equals(content)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串截取
     *
     * @param input 待截取字符串
     * @param index 截取起始位置 <1为起始位置>
     * @param count 截取位数
     * @return 截取后字符串
     */
    public static String StringCut(String input, int index, int count) {
        if (input.isEmpty()) {
            return "";
        }
        count = (count > input.length() - index + 1) ? input.length() - index + 1 : count;

        return input.substring(index - 1, index + count - 1);
    }

    /**
     * 一个TextView 可以显示不同颜色的字体
     *
     * @param mText      显示的字符串
     * @param startIndex 开始索引
     * @param endIndex   结束索引
     * @param color      显示颜色
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder setDifTextColor(String mText, int startIndex, int endIndex, int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(mText);
        //SpannableStringBuilder实现CharSequence接口
        style.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode[] = new String[]{
                "UTF-8",
                "ISO-8859-1",
                "GB2312",
                "GBK",
                "GB18030",
                "Big5",
                "Unicode",
                "ASCII"
        };
        for (int i = 0; i < encode.length; i++) {
            try {
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
                    return encode[i];
                }
            } catch (Exception ex) {
            }
        }

        return "";
    }

//    public static String ioBufferToString(IoBuffer iobuffer) {
////        System.out.println("message = " + iobuffer + iobuffer.limit());
//        iobuffer.flip();    //调换buffer当前位置，并将当前位置设置成0
//        byte[] b = new byte[iobuffer.limit()];
//        iobuffer.get(b);
//        //此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
//        StringBuffer stringBuffer = new StringBuffer();
//
//        for (int i = 0; i < b.length; i++) {
////            System.out.println("====" + b[i]);
//            stringBuffer.append(String.format("%x", b[i])); //可以根据需要自己改变类型
////            System.out.println(b[i] +"---------" +i);
//        }
//        return stringBuffer.toString();
//    }

    /**
     * String类型的mac地址转为6byte的mac
     *
     * @return
     */
    public static byte[] formatToMac(String mac) {
        if (StringUtils.isEmpty(mac)) {
            return new byte[0];
        }
        if (mac.length() != 12) {
            return new byte[0];
        }
        byte[] macBytes = new byte[6];
        String input = mac.replaceAll("(.{2})", "$1:");
        String[] strMacName = input.split(":");// 确保mBean中的macName中分隔符是英文下的“:”，而不是中文下的“：”
        for (int i = 0; i < 6; i++) {
            macBytes[i] = (byte) (Integer.valueOf(strMacName[i], 16) & 0xFF);
        }
        return macBytes;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @return
     */
    public static int compareGWVer(String GWVer1, String GWVer2) {
        int diff = -1;
        try {
            String[] version1 = GWVer1.split("\\.");
            String[] version2 = GWVer2.split("\\.");
            int idx = 0;
            int minLength = Math.min(version1.length, version2.length);//取最小长度值

            while (idx < minLength) {
                diff = Integer.parseInt(version1[idx]) - Integer.parseInt(version2[idx]);
                if (diff != 0) {
                    break;
                }
                ++idx;
            }
            if (diff == 0) {
                diff = version1.length - version2.length;
            }


            //以下方法需保证version1和version2的相同下标的length相同，否则会比对错误
            //例：002与02比较 返回为1
//            while (idx < minLength
//                    && (diff = version1[idx].length() - version2[idx].length()) == 0//先比较长度
//                    && (diff = version1[idx].compareTo(version2[idx])) == 0) {//再比较字符
//                ++idx;
//            }
//            //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
//            diff = (diff != 0) ? diff : version1.length - version2.length;
        } catch (Exception e) {
            //异常时，按默认值处理，默认IP板不支持Mesh功能
//            LogUtils.i(Constant.TAG, e.getMessage());
            diff = -1;
        }
        return diff;
    }
}