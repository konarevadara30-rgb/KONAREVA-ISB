package ru.edu.pr02;

public class TimesheetAnalyzer {
    // Вариант 4: норма часов в день увеличена на 25 %: 8 + 8 * 25 / 100 = 10 часов
    public static final int VARIANT_DAILY_NORM = 8 + 8 * 25 / 100;

    public static int[] parseHours(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Пустая строка табеля");
        }
        String[] parts = line.split(";", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Должно быть ровно 5 значений, а получено " + parts.length);
        }
        int[] hours = new int[5];
        for (int i = 0; i < parts.length; i++) {
            int value;
            try {
                value = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Позиция " + (i + 1) + ": значение не является целым числом");
            }
            if (value < 0 || value > 16) {
                throw new IllegalArgumentException("Позиция " + (i + 1) + ": значение должно быть от 0 до 16");
            }
            hours[i] = value;
        }
        return hours;
    }
    public static int total(int[] hours) {
        int sum = 0;
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i];
        }
        return sum;
    }
    public static double average(int[] hours) {
        if (hours.length == 0) {
            return 0.0;
        }
        return (double) total(hours) / hours.length;
    }
    public static int overtimeDays(int[] hours) {
        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                count++;
            }
        }
        return count;
    }
    public static int weeklyOvertime(int[] hours) {
        return Math.max(0, total(hours) - 40);
    }
    public static int overtimeDaysByVariant(int[] hours) {
        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > VARIANT_DAILY_NORM) {
                count++;
            }
        }
        return count;
    }
}
