package enums;


public enum BoardType {
    BB("Bed&Breakfast"),
    HB("Half Board"),
    FB("Full Board"),
    ALL_INCLUSIVE("All inclusive");

    private final String displayName;

    BoardType(String s) {
        displayName = s;
    }

    public String getDisplayName() {
        return displayName;
    }
}
