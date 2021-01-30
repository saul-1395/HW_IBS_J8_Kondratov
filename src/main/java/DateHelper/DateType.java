package DateHelper;

public class DateType {


    public enum dateType {
        TYPE1("dd.MM.yyyy"), TYPE2("dd/MM/yyyy"), TYPE3("dd.MM.yy"), TYPE4("dd/MM/yy");

        private final String dataType;

        dateType(String dataType) {
            this.dataType = dataType;
        }

        public String getDataType() {
            return dataType;
        }
    }

}
