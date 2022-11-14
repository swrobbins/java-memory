package me.swrobbins;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.DecimalFormat;

public class OutOfMemoryExamples {
    private static DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");

    private static String formatSize(long size, long divider, String unitName) {
        return DEC_FORMAT.format((double) size / divider) + " " + unitName;
    }

    public static String sizeToHumanReadable(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("Invalid file size: " + size);
        }
        if (size < 1024) return size + " Bytes";
        int unitIdx = (63 - Long.numberOfLeadingZeros(size)) / 10;
        return formatSize(size, 1L << (unitIdx * 10), " KMGTPE".charAt(unitIdx) + "iB");
    }

    public void createLargeArray(int arraySize) {
        System.out.println("Creating array of size " + sizeToHumanReadable(arraySize) + " without catch.");
        Integer[] myArray = new Integer[arraySize];
    }

    public void createLargeArrayWithCatch(int arraySize) {
        System.out.println("Creating array of size " + sizeToHumanReadable(arraySize) + " with catch.");
        try {
            Integer[] myArray = new Integer[arraySize];
        } catch (OutOfMemoryError error) {
            System.err.println("Array of size " + sizeToHumanReadable(arraySize) + " is too large (> " +
                    sizeToHumanReadable(Runtime.getRuntime().maxMemory()) + ")!");
        }
    }

    public void showMemory() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        System.out.println("Initial memory (xms): " + sizeToHumanReadable(memoryBean.getHeapMemoryUsage().getInit()));
        System.out.println("Max memory (xmx): " + sizeToHumanReadable(memoryBean.getHeapMemoryUsage().getMax()));
    }

    public static void main(String[] args) {
        OutOfMemoryExamples oome = new OutOfMemoryExamples();
        oome.showMemory();
        int arraySize = (1024 * 1024 * 1024);
        oome.createLargeArrayWithCatch(arraySize);
        oome.createLargeArray( arraySize);
    }
}