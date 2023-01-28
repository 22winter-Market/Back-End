package winterproject.market.domain.enumeration;

public enum TradeMethod {
    ONLINE, OFFLINE;

    static public TradeMethod of(String method) {
        if (method.equals("ONLINE")) return ONLINE;
        return OFFLINE;
    }
}
