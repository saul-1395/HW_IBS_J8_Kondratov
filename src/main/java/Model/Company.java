package Model;

public class Company {
    private Organisations organisations;

    public Company() {
    }

//    public Build(Model.Organisations organisations) {
//        this.organisations = organisations;
//    }

    public Organisations getOrganisations() {
        return organisations;
    }

    public void setOrganisations(Organisations organisations) {
        this.organisations = organisations;
    }

    @Override
    public String toString() {
        return "Build{" +
                "organisations=" + organisations.getName() +
                '}';
    }
}
