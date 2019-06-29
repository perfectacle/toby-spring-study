package example.domain;

public enum Level {
    GOLD(3, null)
    , SILVER(2, GOLD)
    , BASIC(1, SILVER)
    ;

    private final int level;
    private final Level next;

    Level(final int level, final Level next) {
        this.level = level;
        this.next = next;
    }

    public int intValue() {
        return level;
    }

    public Level nextLevel() {
        return next;
    }

    public static Level valueOf(final int value) {
        switch (value) {
            case 1: return BASIC;
            case 2: return SILVER;
            case 3: return GOLD;
            default: throw new AssertionError("Unknown value: " + value);
        }
    }
}
