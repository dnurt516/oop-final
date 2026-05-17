package enums;

public enum ResolveStatus {
    CANCELED("Canceled"),
    PENDING("Pending"),
    IN_PROCESS("In process"),
    RESOLVED("Resolved"),
    CLOSED("Closed");

    private final String textValue;

    ResolveStatus(String v) {
        this.textValue = v;
    }

    public String getTextValue() {
        return this.textValue;
    }
}
