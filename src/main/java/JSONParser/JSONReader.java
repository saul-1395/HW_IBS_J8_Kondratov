package JSONParser;


import DateHelper.DateHelper;
import DateHelper.DateType;
import Currencies.Currency;
import Model.Company;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONReader {
    private List<Company> list = null;
    private final DateHelper dateHelper = new DateHelper();
    private final String path;


    public JSONReader(String path) {
        this.path = path;
    }

    public void getJSONlist() {


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = objectMapper.readValue(new File(path), new TypeReference<List<Company>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void organisationListAfterDate(String inputDate, DateType dateType) {
        if (list != null) {
            getJSONlist();
        }
        System.out.println("организации основанные после " + inputDate + " :");
        list.stream()
                .filter(x -> dateHelper.foundationAfterDate(inputDate, dateType, x.getOrganisations()))
                .map(x -> "компания " + x.getOrganisations().getName() + " основана в " + x.getOrganisations().getFoundation())
                .forEach(System.out::println);


    }

    public void getSecuritiesFromCurrency(Currency currency) {
        System.out.println(" Бумаги в которых используется валюта " + currency.getCurrency());
        list.stream()
                .forEach(x -> Arrays.stream(x.getOrganisations().getSecurities())
                        .filter(y -> y.getCurrency().stream().anyMatch(w -> w.equals(currency.getCurrency())))
                        .forEach(w -> System.out.println(w.getName() + " " + w.getId()))

                );

    }


    public void organisationsList() {
        if (list == null) {
            getJSONlist();
        }


        list.stream().map(x ->
                "Организация: " + x.getOrganisations().
                        getName() + " основана в: " + dateHelper.dateConverter(x.getOrganisations().getFoundation(), DateType.TYPE1, DateType.TYPE2)

        ).forEach(System.out::println);
    }

    public void securitiesValidListPrint() {
        if (list == null) {
            getJSONlist();
        }

        long actualSecurites =0l;

        actualSecurites = list.stream().map(y -> {
            long l = Arrays.stream(y.getOrganisations().getSecurities())
                    .filter(x -> dateHelper.stillValid(x.getTerminate(), DateType.TYPE1))
                    .peek(x -> {
                        String s = "Бумага: " + x.getName() + " актуально до: " +
                                dateHelper.dateConverter(x.getTerminate(), DateType.TYPE1, DateType.TYPE2) + " владелец: " + y.getOrganisations().getName();
                        System.out.println(s);
                    })
                    .count();

            return l;
        }).mapToLong(l->l.longValue()).sum();

        System.out.println("Всего актуальных бумаг: " + actualSecurites);

    }
}
