package Model;

import java.util.ArrayList;

public class Securities {
    String name;
    int id;
    String terminate;

    ArrayList<String> currency = new ArrayList<>();

    public Securities() {

    }

    public ArrayList<String> getCurrency() {
        return currency;
    }

    public void setCurrency(ArrayList<String> currency) {
        this.currency = currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTerminate(String terminate) {
        this.terminate = terminate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getTerminate() {
        return terminate;
    }
}
