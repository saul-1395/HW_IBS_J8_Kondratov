package Model;


public class Organisations {


    private String ogrn;

    private String name;

    private String inn;

    private String addres;

    private String foundation;

    private String phonenumber;

    private Securities[] securities;



    public Organisations() {
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public String getAddres() {
        return addres;
    }

    public String getFoundation() {
        return foundation;
    }

    public String getPhonenumber() {return phonenumber;}

    public Securities[] getSecurities() {
        return securities;
    }
}