package org.mediacat.utils;

final public class Utils {
    /**
     * Returns the byte equivalent of <code>sizeStr</code> as a long
     *
     * <p>Format must be "<code>size  unit</code>" where <code>unit</code>
     * can be one of [kb|kib|kilobyte|kilo byte|mb|mib|megabyte|mega byte|
     * gb|gib|gigabyte|giga byte]</p>
     *
     * @param sizeStr size as a String in the format "<code>size unit</code>"
     * @return byte equivalent of <code>sizeStr</code> as a long
     */
    public static long parseMediaSize(String sizeStr) {
        String[] pieces = sizeStr.split("\\s");
        double size = Double.parseDouble(pieces[0]);
        switch (pieces[1].toUpperCase()) {
            case "KB":
            case "KIB":
            case "KILOBYTE":
            case "KILO BYTE":
                size *= 1000L;
                break;
            case "MB":
            case "MIB":
            case "MEGABYTE":
            case "MEGA BYTE":
                size *= 1000_000L;
                break;
            case "GB":
            case "GIB":
            case "GIGABYTE":
            case "GIGA BYTE":
                size *= 1000_000_000L;
                break;
            case "TB":
            case "TIB":
            case "TERABYTE":
            case "TERA BYTE":
                size *= 1000_000_000_000L;
                break;
        }

        return (long) size;
    }

    /**
     * Returns the number of days since <code>ageStr</code>
     *
     * <p><code>ageStr</code> must be in the format "<code>age unit</code>"
     * where <code>unit</code> can be one of [hour|hours|minutes|min.|sec.|
     * seconds|day|days|month|months|year|years]</p>
     *
     * @param ageStr age as a String in the format "<code>age unit</code>"
     * @return number of days since <code>ageStr</code> as an integer
     */
    public static int parseMediaAge(String ageStr) {
        String[] pieces = ageStr.split("\\s");
        int age = Integer.parseInt(pieces[0]);
        switch (pieces[1].toLowerCase()) {
            case "hour":
            case "hours":
            case "minutes":
            case "min.":
            case "seconds":
            case "sec.":
                age = 0;
                break;
            case "month":
            case "months":
                age *= 30;
                break;
            case "year":
            case "years":
                age *= 365;
                break;
        }

        return age;
    }
}
