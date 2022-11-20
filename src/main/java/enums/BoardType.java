package enums;


public enum BoardType {
    BB("BedAndBreakFast"),
    HB("Half Board"),
    FB ("Full Board"),
    ALL_INCLUSIVE("All inclusive");

    private final String displayName;

    BoardType(String s) {
        displayName = s;
    }
}
