package winterproject.market.domain.enumeration;

public enum ItemStatus {
    SALE, SOLD_OUT;

    static public ItemStatus of(String method) {
        if (method.equals("ONLINE")) return SALE;
        return SOLD_OUT;
    }
}
