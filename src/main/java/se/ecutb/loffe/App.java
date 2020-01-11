package se.ecutb.loffe;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;

public class App
{
    public static void main( String[] args )
    {

        // 1
        LocalDate currentDay = LocalDate.now();
        System.out.println(currentDay);

        // 2
        System.out.println(currentDay.format(DateTimeFormatter.ofPattern("eeee DD MMMM")));

        // 3
        for (LocalDate temp = LocalDate.parse("2020-01-06");
             temp.isBefore(LocalDate.parse("2020-01-13"));
             temp = temp.plusDays(1)){
            System.out.println(temp);
        }

        // 4 - see 3.

        // 5
        LocalDate longAgo = LocalDate.parse("1945-05-08");
        DayOfWeek aWeekdayLongAgo = longAgo.getDayOfWeek();
        System.out.println(longAgo + " was a " + aWeekdayLongAgo);

        // 6
        LocalDate inTheFuture = currentDay.plusYears(10).minusMonths(10);
        Month aMonthInTheFuture = inTheFuture.getMonth();
        System.out.println(inTheFuture + " the month is " + aMonthInTheFuture);

        // 7
        LocalDate birthday = LocalDate.parse("1977-06-23");
        Period aPeriod = Period.between(longAgo, birthday);
        System.out.println("Perioden mellan " +
                longAgo +
                " och " +
                birthday +
                " är: " +
                aPeriod.getYears() +
                " år, " + aPeriod.getMonths() +
                " månader, " +
                aPeriod.getDays() +
                " dagar.");

        // 8
        Period anotherPeriod = Period.of(4, 7, 29);
        LocalDate aDateInTheFuture = currentDay.plus(anotherPeriod);
        System.out.println(aDateInTheFuture);

        // 9
        LocalTime now = LocalTime.now();
        System.out.println(now);

        // 10
        System.out.println(now.getNano());

        // 11
        String timeInAString = "23:58";
        LocalTime time1 = LocalTime.parse(timeInAString);
        System.out.println(time1);

        // 12
        System.out.println(now.truncatedTo(SECONDS));

        // 13
        LocalDateTime dateAndTime = LocalDateTime.parse("2018-04-05T10:00");
        System.out.println(dateAndTime);

        // 14
        System.out.println(dateAndTime.format(DateTimeFormatter.ofPattern("eeee d MMMM HH:mm")));

        // 15
        LocalDateTime dateAndTimeCombined = LocalDateTime.of(currentDay, now);
        System.out.println(dateAndTimeCombined.format(DateTimeFormatter.ofPattern("eeee dd MMMM HH:mm")));

        // 16
        LocalDate extractedDate = dateAndTimeCombined.toLocalDate();
        LocalTime extractedTime = dateAndTimeCombined.toLocalTime();
        System.out.println(extractedDate + " " + extractedTime.truncatedTo(SECONDS));

        // EXTRA
        List<LocalDate> newCalendar = calendar2018();
        for (LocalDate date : newCalendar) {
            System.out.println(date.format(DateTimeFormatter.ofPattern("eeee dd MMMM yyyy G")));

        }

        // EXTRA EXTRA
        // skapa en metod där ett år skickas in, returnerar treeset, hel kalender
        Map<Integer, Collection<LocalDate>> tenYearCalendar = new TreeMap<>();
        for (int year = 2020; year <=2100; year++){
            tenYearCalendar.put(year, calendar(year));
        }

        System.out.println("\nBirthdays!");
        for (Integer year : tenYearCalendar.keySet()) {
            System.out.print(year + ": ");
            for (LocalDate date : tenYearCalendar.get(year)){
                if (date.getMonth().getValue() == 2 && date.getDayOfMonth() == 8){
                    System.out.println(date.format(DateTimeFormatter.ofPattern("eeee dd MMMM")));
                }
            }
        }
    }

    public static List<LocalDate> calendar2018(){
        LocalDate startOf2018 = LocalDate.parse("2018-01-01");
        LocalDate endOf2018 = LocalDate.parse("2018-12-31").plusDays(1);
        ArrayList<LocalDate> calendar2018 = new ArrayList<>();
        for (LocalDate temp = startOf2018; temp.isBefore(endOf2018); temp = temp.plusDays(1)){
            calendar2018.add(temp);
        }
        return calendar2018;
    }

    public static Collection<LocalDate> calendar(int year){
        Collection<LocalDate> calendar = new TreeSet<>();
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year + 1, 1, 1);
        for (LocalDate temp = start; temp.isBefore(end); temp = temp.plusDays(1)){
            calendar.add(temp);
        }
        return calendar;
    }
}
