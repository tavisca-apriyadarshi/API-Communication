package tavisca.gce.training.DataFetcher.model;

import java.util.UUID;

public class TransactionIdGenerator {
    private static String uniqueID = UUID.randomUUID().toString();

    public static String getUniqueID() {
        return uniqueID;
    }
}
