package JSONParser;


import DateHelper.DateHelper;
import DateHelper.DateType.dateType;
import InputInterface.Currency;
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

    public void organisationListAfterDate(String inputDate, dateType dateType) {
        if (list != null) {
            getJSONlist();
        }
        System.out.println("организации основанные после " + inputDate + " :");
        list.stream()
                .filter(x -> dateHelper.foundationAfterDate(inputDate, dateType, x.getOrganisations()))
                .map(x -> "компания " + x.getOrganisations().getName() + " основана в " + x.getOrganisations().getFoundation())
                .forEach(System.out::println);


    }

    public void getSecuritiesFromCurrency(Currency.currency currency) {
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
                        getName() + " основана в: " + dateHelper.dateConverter(x.getOrganisations().getFoundation(), dateType.TYPE1, dateType.TYPE2)

        ).forEach(System.out::println);
    }

    public void securitiesValidListPrint() {
        if (list == null) {
            getJSONlist();
        }

        final long[] counter = {0l};
        long count = 0l;
        list.stream().filter(y -> {
            counter[0] = counter[0] + Arrays.stream(y.getOrganisations().getSecurities())
                    .filter(x -> dateHelper.stillValid(x.getTerminate(), dateType.TYPE1))
                    .map(x -> {
                        String s = "Бумага: " + x.getName() + " актуально до: " +
                                dateHelper.dateConverter(x.getTerminate(), dateType.TYPE1, dateType.TYPE2) + " владелец: " + y.getOrganisations().getName();
                        System.out.println(s);
                        return s;
                    })
                    .count();

            return true;
        }).count();



        for (long l : counter) {
            count += l;
        }
        System.out.println("всего активных бумаг: " + count);
    }


}
