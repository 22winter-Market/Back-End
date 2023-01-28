package winterproject.market.domain.enumeration;

public enum TradeMethod {
    ONLINE, OFFLINE;

    static public TradeMethod of(String method) {
        if (method.equals("ONLINE")) return ONLINE;
        if (method.equals("OFFLINE")) return OFFLINE;
        return null;
    }
}
