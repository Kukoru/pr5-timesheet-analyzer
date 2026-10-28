package ru.edu.pr02;

public class TimesheetAnalyzer {
    
    // Вариант 7: норма часов в день = 40
    public static final int NORMAL_HOURS_PER_DAY = 40;
    public static final int MAX_HOURS_PER_DAY = 16; // Из TODO 1 в исходнике
    public static final int WEEKLY_NORM = 40; // Согласно правилу из методички

    public static int[] parseHours(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: строка пустая.");
        }
        
        String[] parts = line.split(";", -1); 
        
        if (parts.length != 5) {
            throw new IllegalArgumentException("Ошибка: ожидалось ровно 5 значений, получено " + parts.length);
        }

        int[] hours = new int[5];
        for (int i = 0; i < 5; i++) {
            String part = parts[i].trim();
            if (part.isEmpty()) {
                throw new IllegalArgumentException("Ошибка в позиции " + (i + 1) + ": значение отсутствует.");
            }
            
            try {
                int val = Integer.parseInt(part);
                if (val < 0 || val > MAX_HOURS_PER_DAY) {
                    throw new IllegalArgumentException("Ошибка в позиции " + (i + 1) + ": значение " + val + " вне диапазона 0.." + MAX_HOURS_PER_DAY);
                }
                hours[i] = val;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ошибка в позиции " + (i + 1) + ": '" + part + "' не является целым числом.");
            }
        }
        return hours;
    }

    public static int total(int[] hours) {
        int sum = 0;
        for (int h : hours) {
            sum += h;
        }
        return sum;
    }

    public static double average(int[] hours) {
        return (double) total(hours) / hours.length;
    }

    public static int overtimeDays(int[] hours) {
        int count = 0;
        for (int h : hours) {
            if (h > NORMAL_HOURS_PER_DAY) {
                count++;
            }
        }
        return count;
    }

    // ИСПРАВЛЕНО: Теперь считается по формуле max(0, сумма - 40)
    public static int weeklyOvertime(int[] hours) {
        return Math.max(0, total(hours) - WEEKLY_NORM);
    }
}