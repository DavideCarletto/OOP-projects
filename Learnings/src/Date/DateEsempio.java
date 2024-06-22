package Date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DateEsempio {

    public static void main(String[] args) {
        
        Instant i = Instant.now();
        System.out.println(i);

        LocalTime t = LocalTime.now();
        System.out.println(t);

        LocalDate d = LocalDate.now();
        System.out.println(d);

        LocalDate natale = LocalDate.of(2023, 12, 25);
        // LocalDate vigilia = natale.minus(Period.ofDays(1));
        LocalDate vigilia = natale.minus(1, ChronoUnit.DAYS);

        System.out.println(natale.getDayOfWeek());

        DayOfWeek giornoNatale = natale.getDayOfWeek();
        System.out.println(giornoNatale.getDisplayName(TextStyle.SHORT, Locale.ITALIAN));

        LocalDate prossimoNatale = LocalDate.of(2024, 12, 25);
        System.out.println(Period.between(d, prossimoNatale));

        System.out.println(orariLezioni(LocalTime.of(8,30, 0, 0), LocalTime.of(19, 0, 0)));

        System.out.println(giorniFeriali(LocalDate.now(), prossimoNatale));
    }
    

    public static List<LocalTime> orariLezioni(LocalTime da, LocalTime a){
        LocalTime current = da;
        List<LocalTime> res = new ArrayList<>();

        while(current.isBefore(a)){
            res.add(current);
            current = current.plusMinutes(90);
        }

        return res;
    }

    public static int giorniFeriali(LocalDate da, LocalDate a){

        return (int)da.datesUntil(a.plusDays(1)) //questo mi restituisce uno stream
        .filter(d -> d.getDayOfWeek() != DayOfWeek.SUNDAY && d.getDayOfWeek() != DayOfWeek.SATURDAY && !festivita().contains(d))
        .count();

    }

    public static Set<LocalDate> festivita(){
        HolidayManager manager = HolidayManager.getInstance(HolidayCalendar.ITALY);
        int anno = 2024; // Anno di esempio
        Set<Holiday> holidays = manager.getHolidays(anno);
    }

}
