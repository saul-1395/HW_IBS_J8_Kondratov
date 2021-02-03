package DateHelper;


    public enum DateType {
        TYPE1("dd.MM.yyyy"), TYPE2("dd/MM/yyyy"), TYPE3("dd.MM.yy"), TYPE4("dd/MM/yy");

        private String dateType;

        DateType(String dateType) {
            this.dateType = dateType;
        }


        public String getDateType() {
            return dateType;
        }
    }


