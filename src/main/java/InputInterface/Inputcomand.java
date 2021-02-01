package InputInterface;


import DateHelper.DateType.dateType;
import JSONParser.JSONReader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Inputcomand {
    private JSONReader jsonReader = null;
    private String path;


    private void run(String path) {

        jsonReader = new JSONReader(path);
        System.out.println("В базе имеются компании: ");
        jsonReader.organisationsList();
        System.out.println();
        System.out.println("Данные по актуальным бумагам: ");
        jsonReader.securitiesValidListPrint();
        inputDataReader();
    }

    public void inputPath() {
        System.out.println("Введите полный путь или имя файлa в папке по адресу "
                + System.getProperty("user.home") + "\\Documents\\Learn_IBS\\HW_J8_Kondratov\\Test Files\\" + ": ");
    //    C:\Users\User\Documents\Learn_IBS\HW_J8_Kondratov\Test Files\myjson.json
        Scanner in = new Scanner(System.in);
        String inputPath = in.nextLine();
        if (!inputPath.isEmpty()) {
            String firstSymbol = inputPath.substring(0, 2);
            if (firstSymbol.equals("C:")) {
                if (new File(inputPath).exists()) {
                    run(inputPath);
                } else {
                    System.out.println("Адрес не верен");
                    this.inputPath();
                }
            } else if (!inputPath.isEmpty()) {
                path = System.getProperty("user.home") + "\\Documents\\Learn_IBS\\HW_J8_Kondratov\\Test Files\\" + inputPath;
                System.out.println(path);
                if (new File(path).exists()) {
                    run(path);
                } else {
                    System.out.println("Имя файла не верно");
                    this.inputPath();
                }
            }
        } else {
            this.inputPath();
        }


    }

    private void inputDataReader() {
        System.out.println("введите дату, чтобы узнать какие компании основаны с тех пор:");
        if (jsonReader == null) {
            inputPath();
        }

        Scanner in = new Scanner(System.in);
        String inputDate = in.nextLine();

        String format = null;
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(dateType.TYPE1.getDataType());


        if (inputDate.length() == dateType.TYPE1.getDataType().length()) {
            if (inputDate.charAt(2) == dateType.TYPE1.getDataType().charAt(2)) {
                format = dateType.TYPE1.getDataType();
            } else if (inputDate.charAt(2) == dateType.TYPE2.getDataType().charAt(2))
                format = dateType.TYPE2.getDataType();
        } else if (inputDate.length() == dateType.TYPE3.getDataType().length()) {
            if (inputDate.charAt(2) == dateType.TYPE3.getDataType().charAt(2)) {
                format = dateType.TYPE3.getDataType();
            } else if (inputDate.charAt(2) == "/".charAt(0))
                format = dateType.TYPE4.getDataType();
        }
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = formatter.format(date);


        jsonReader.organisationListAfterDate(formatDate, dateType.TYPE1);

        inputCurrencies();

    }

    private void inputCurrencies() {
        System.out.println("ведите валюту (USD, EU, RUB) : ");
        Scanner in = new Scanner(System.in);
        String inputCurrency = in.nextLine();
        Currency.currency currency = null;

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
                currency = currency.RUB;
                break;
            case "USD":
                currency = currency.RUB;
                break;
        }

        jsonReader.getSecuritiesFromCurrency(currency);


    }
}
