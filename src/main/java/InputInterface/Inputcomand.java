package InputInterface;


import Currencies.Currency;
import DateHelper.DateType;
import JSONParser.JSONReader;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Inputcomand {
    private JSONReader jsonReader = null;



    public void appStart() {

        jsonReader = new JSONReader("src/Test Files/myjson.json");
        System.out.println("В базе имеются компании: ");
        jsonReader.organisationsList();
        System.out.println();
        System.out.println("Данные по актуальным бумагам: ");
        jsonReader.securitiesValidListPrint();
        inputDataReader();
        inputCurrencies();
    }



    private void inputDataReader() {
        System.out.println("введите дату, чтобы узнать какие компании основаны с тех пор:");


        Scanner in = new Scanner(System.in);
        String inputDate = in.nextLine();

        String format = null;
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(DateType.TYPE1.getDateType());

//dateType.TYPE1.getDataType()
        if (inputDate.length() == DateType.TYPE1.getDateType().length()) {
            if (inputDate.charAt(2) == DateType.TYPE1.getDateType().charAt(2)) {
                format = DateType.TYPE1.getDateType();
            } else if (inputDate.charAt(2) == DateType.TYPE2.getDateType().charAt(2))
                format = DateType.TYPE2.getDateType();
        } else if (inputDate.length() == DateType.TYPE3.getDateType().length()) {
            if (inputDate.charAt(2) == DateType.TYPE3.getDateType().charAt(2)) {
                format = DateType.TYPE3.getDateType();
            } else if (inputDate.charAt(2) == "/".charAt(0))
                format = DateType.TYPE4.getDateType();
        }
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = formatter.format(date);


        jsonReader.organisationListAfterDate(formatDate, DateType.TYPE1);



    }

    private void inputCurrencies() {
        System.out.println("ведите валюту (USD, EU, RUB) : ");
        Scanner in = new Scanner(System.in);
        String inputCurrency = in.nextLine();
        Currency currency = null;

        if (!inputCurrency.equals("RUB") &
                !inputCurrency.equals("EU") &
                !inputCurrency.equals("USD")
        ) {
            System.out.println("Не верный формат, поробуйте ещё раз");
            this.inputCurrencies();
        }

        switch (inputCurrency) {
            case "RUB":
                currency = currency.RUB;
                break;

            case "EU":
                currency = currency.EU;
                break;
            case "USD":
                currency = currency.USD;
                break;
            default: currency=currency.USD;
            break;
        }

        jsonReader.getSecuritiesFromCurrency(currency);


    }
}
