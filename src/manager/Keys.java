package manager;

public enum Keys {
    //basic
    BASICGCC("GCC"),
    BASICJAVAC("Java"),
    FILE("file searching"),
    PROJECT("output"),

    /*####################################################*/;

    Keys(String key) {
        this.keyString = key;
    }

    public String getKeyString() {
        return keyString;
    }

    private final String keyString;
}