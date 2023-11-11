public enum ThemeEnum {

    ANIMALS("animals.txt"),
    COUNTRIES("countries.txt"),
    DRINKS("drinks.txt"),
    FOODS("foods.txt"),
    FRUITS("fruits.txt"),
    VEGETABLES("vegetables.txt");

    private final String fileName;

    ThemeEnum(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

