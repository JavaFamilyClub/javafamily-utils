/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 *
 * The software and information contained herein are copyrighted and
 * proprietary to JavaFamily Technology Corp. This software is furnished
 * pursuant to a written license agreement and may be used, copied,
 * transmitted, and stored only in accordance with the terms of such
 * license and with the inclusion of the above copyright notice. Please
 * refer to the file "COPYRIGHT" for further copyright and licensing
 * information. This software and information or any other copies
 * thereof may not be provided or otherwise made available to any other
 * person.
 */

package club.javafamily.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 核心工具类
 */
public class Tool {
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_ZONE_STR = "GMT+08:00";
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(DEFAULT_TIME_ZONE_STR);

    private static final String NOCLONE = "noclone";
    private static Map<String, Object> cloneFns = new ConcurrentHashMap<>();

    private static final SecureRandom secureRandom;

    private static final Logger LOG = LoggerFactory.getLogger(Tool.class);

    static {
        SecureRandom random = null;

        if(!SystemUtils.IS_OS_WINDOWS) {
            try {
                random = SecureRandom.getInstance("NativePRNGNonBlocking");
            }
            catch(NoSuchAlgorithmException ignore) {
            }
        }

        if(random == null) {
            try {
                random = SecureRandom.getInstance("SHA1PRNG");
            }
            catch(NoSuchAlgorithmException ignore) {
            }
        }

        if(random == null) {
            Random seedRandom = new Random(System.currentTimeMillis());
            byte[] seed = new byte[16];
            seedRandom.nextBytes(seed);
            random = new SecureRandom(seed);
        }

        secureRandom = random;
    }

    /**
     * Get the operator to use when concatenating an op parameter to a servlet
     * name.
     */
    public static String getConcat(String servletName) {
        return servletName.indexOf('?') >= 0 ? "&" : "?";
    }

    /**
     * Deep clone a collection.
     * <p>
     * For any value in the collection, if it's an instance of Cloneable, and
     * contains a public clone method, the value will be cloned, otherwise the
     * cloned collection will use the value directly.
     *
     * @param from the to be cloned collection.
     * @return the cloned collection, null means the to be cloned list is null,
     * or exception occurs.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Collection> T deepCloneCollection(T from) {
        if(from == null) {
            return null;
        }

        try {
            T to = (T) from.getClass().newInstance();
            Iterator values = from.iterator();

            while(values.hasNext()) {
                Object val = values.next();

                if(val == null) {
                    to.add(null);
                }

                else if(val instanceof Cloneable) {
                    Method m = getClone(val.getClass());

                    if(m == null) {
                        to.add(val);
                    }
                    else {
                        to.add(m.invoke(val));
                    }
                }
                else {
                    to.add(val);
                }
            }

            return to;
        }
        catch(Exception ex) {
            LOG.error("Failed to deepClone object: " + from, ex);
            return null;
        }
    }

    /**
     * Deep clone a map.
     * <p>
     * keys of the map will not be cloned. For any value, if it's an instance of
     * Cloneable, and contains a public clone method, the value will be cloned,
     * otherwise the cloned map will use the value directly.
     *
     * @param from the to be cloned map.
     * @return the cloned map, null means the to be cloned map is null,
     * or exception occurs.
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> deepCloneMap(Map<K, V> from) {
        if(from == null) {
            return null;
        }

        try {
            Map<K, V> to = (Map<K, V>) from.getClass().newInstance();

            for(Map.Entry<K, V> entry : from.entrySet()) {
                K key = entry.getKey();
                V val = entry.getValue();

                if(val == null) {
                    to.put(key, null);
                }
                else if(val instanceof Cloneable) {
                    Method m = getClone(val.getClass());

                    if(m == null) {
                        to.put(key, val);
                    }
                    else {
                        to.put(key, (V) m.invoke(val));
                    }
                }
                else {
                    to.put(key, val);
                }
            }

            return to;
        }
        catch(Exception ex) {
            LOG.error("Failed to deep clone map: " + from, ex);
            return null;
        }
    }

    /**
     * Util clone method.
     */
    public static Object clone(Object v) {
        if(v instanceof Collection) {
            return deepCloneCollection((Collection) v);
        }
        else if(v instanceof Map) {
            return deepCloneMap((Map) v);
        }
        else if(v instanceof Object[][]) {
            Object[][] src = (Object[][]) v;

            if(src.length <= 0 || src[0].length <= 0) {
                return src;
            }

            Object[][] target = (Object[][]) Array.newInstance(
                    getItemComponentType(src), new int[] {src.length,
                            src.length > 0 ? src[0].length : 0});

            for(int i = 0; i < target.length; i++) {
                target[i] = (Object[]) clone(src[i]);
            }
        }
        else if(v instanceof Object[]) {
            Object[] src = (Object[]) v;

            if(src.length <= 0) {
                return src;
            }

            Object[] target = (Object[]) Array.newInstance(
                    getItemComponentType(src), src.length);

            for(int i = 0; i < src.length; i++) {
                target[i] = clone(src[i]);
            }

            return target;
        }
        else if(v instanceof Cloneable) {
            Method cm = getClone(v.getClass());

            try {
                if(cm != null) {
                    return cm.invoke(v);
                }
            }
            catch(Exception ex) {
                // ignore it
            }
        }

        return v;
    }

    private static Method getClone(Class cls) {
        String key = cls.getName();
        Object cm = cloneFns.get(key);

        if(cm == NOCLONE) {
            return null;
        }

        try {
            if(cm == null) {
                cm = cls.getMethod("clone");
                cloneFns.put(key, cm);
            }
        }
        catch(Exception ex) {
            cloneFns.put(key, NOCLONE);
        }

        return (Method) cm;
    }

    /**
     * Get the item type of an array or multi-dimensional array.
     */
    private static Class getItemComponentType(Object arr) {
        Class cls = arr.getClass();

        while(cls.isArray()) {
            cls = cls.getComponentType();
        }

        return cls;
    }

    public static String getUserHome() {
        String userHome = System.getProperty("user.home");

        return userHome + "/.javafamily";
    }

    public static File getCacheDir() {
        File file = new File(Tool.getUserHome(), CACHE_DIR);

        if(!file.exists() || !file.isDirectory()) {
            if(file.mkdirs()) {
                LOG.info("Auto create cache dir: " + file.getAbsolutePath());
            }
        }

        return file;
    }

    public static int unboxingNumber(Integer number, int defaultNumber) {
        return number != null ? number : defaultNumber;
    }

    public static double getPercent(double total, double part) {
        return 100.0D * part / total;
    }

    public static String toString(Object obj) {
        if(obj instanceof Number) {
            return obj.toString();
        }
        else if(obj instanceof Date) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(Tool.DEFAULT_DATETIME_FORMAT)
                    .withZone(Tool.DEFAULT_TIME_ZONE.toZoneId());
            return df.format(((Date) obj).toInstant());
        }
        else if(obj instanceof Object[]) {
            return Arrays.stream((Object[]) obj)
                    .map(Tool::toString)
                    .collect(Collectors.joining(","));
        }
        else {
            return Objects.toString(obj, "");
        }
    }

    public static SecureRandom getSecureRandom() {
        return secureRandom;
    }

    public static <T> T[][] merge(T[][] arr1, T[][] arr2) {
        List<T[]> list = new ArrayList<>(Arrays.asList(arr1));
        list.addAll(Arrays.asList(arr2));

        return list.toArray(arr1);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T makeInvalidNumberToNull(T num) {
        if(num instanceof Comparable) {
            return ((Comparable) num).compareTo(999.99) >= 0 ? null : num;
        }

        return num;
    }

    public static boolean isInvalidData(String data) {
        return data != null && (data.startsWith("9999")
                || data.startsWith("9996")
                || data.startsWith("9997")
                || data.startsWith("9998")
                || data.startsWith("9990"));
    }

    public static String makeInvalidNumberToNull(String data) {
        if(isInvalidData(data)) {
            return null;
        }

        return data;
    }

    public static <T> boolean largeComparable(Comparable<T> num1, T num2) {
        if(num1 != null && num2 != null) {
            return num1.compareTo(num2) > 0;
        }
        else if(num1 == null && num2 == null) {
            return true;
        }

        return false;
    }

    public static <T extends Number> Double reserveOneDecimalPlace(T num) {
        if(num == null) {
            return null;
        }

        return reserveDecimalPlace(num.doubleValue(), 1);
    }

    public static Double reserveDecimalPlace(Double num, int point) {
        if(num == null) {
            return null;
        }

        return Math.round(num * Math.pow(10, point)) * 1.0 / Math.pow(10, point);
    }

    public static List<Float> floatArrayToList(float[] ds) {
        List<Float> list = new ArrayList<>();

        if(ds == null || ds.length < 1) {
            return list;
        }

        for (float data : ds) {
            list.add(data);
        }

        return list;
    }

    public static List<List<Float>> floatArray2DToList(float[][] ds) {
        List<List<Float>> dsResult = new ArrayList<>();

        for (float[] row : ds) {
            List<Float> rowValue = new ArrayList<>();

            for (float v : row) {
                rowValue.add(v);
            }

            dsResult.add(rowValue);
        }

        return dsResult;
    }

    public static boolean isArray(Object obj) {
        if(obj == null) {
            return false;
        }

        return obj instanceof Array || obj.getClass().isArray();
    }

    public static <T> String toTwoNumberString(T num) {
        String numStr = Tool.toString(num);

        if(numStr.length() == 1) {
            return "0" + numStr;
        }

        return numStr;
    }

    /**
     * call machine command. e.g. python
     * @param exe python
     * @param command test.py
     * @param args args
     * @return process. it's need to destroy when completed.
     * @throws IOException exception
     */
    public static Process callCommand(String exe, String command,
                                      String...args)
            throws IOException
    {
        List<String> cmd = new ArrayList<>();
        cmd.add(exe);
        cmd.add(command);
        cmd.addAll(Arrays.asList(args));

        return Runtime.getRuntime()
                .exec(cmd.toArray(new String[0]));
    }

    public static String appendPath(String base, String suffix) {
        if(StringUtils.isEmpty(base)) {
            return suffix;
        }

        if(StringUtils.isEmpty(suffix)) {
            return base;
        }

        if(base.endsWith("/") && suffix.startsWith("/")) {
            return Tool.appendPath(base, suffix.substring(1));
        }

        return base.endsWith("/") || suffix.startsWith("/")
                ? base + suffix
                : base + "/" + suffix;
    }

    private static final String CACHE_DIR = "/cache";
}
