package DateHelper;

import DateHelper.DateType.dateType;
import Model.Organisations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class DateHelper {
    public String dateConverter(String inputDate, dateType inputformat, dateType outputformat) {
        String formatDate = "00.00.0000";
        Date date = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(inputformat.getDataType());//задаю формат даты на входе

            date = formatter.parse(inputDate); //парсю входный стринг даты

            SimpleDateFormat formatForDateNow = new SimpleDateFormat(outputformat.getDataType()); //задаю формат на выходе

            formatDate = formatForDateNow.format(date);

            // System.out.println("Получилась дата " + formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatDate;
    }

    public boolean stillValid(String inputDate, dateType dataType) {

        Boolean b = false;
        Date date;
        LocalDate localDate = LocalDate.now();
        Date localdate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        DateFormat dateFormat = new SimpleDateFormat(dataType.getDataType());


        try {

            date = dateFormat.parse(inputDate);
            //   System.out.println(localdate.toString() + "* " + date.toString());

            b = date.after(localdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //    System.out.println(" result " + b);
        return b;

    }

    public boolean foundationAfterDate(String inputDate, dateType inputType, Organisations organisations) {

        Boolean b = false;

        SimpleDateFormat formatter = new SimpleDateFormat(inputType.getDataType());

        try {
            Date date = formatter.parse(inputDate);

            Date dateFoundation = formatter.parse(organisations.getFoundation());

            b = date.before(dateFoundation);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //    System.out.println(" result " + b);
        return b;

    }
}
