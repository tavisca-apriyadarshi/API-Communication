package tavisca.gce.training.IncomingRequestHandler.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransactionDetails {
    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "input_transaction_id_generator")
    @SequenceGenerator(name="input_transaction_id_generator", sequenceName = "input_transaction_id_seq", allocationSize=1)
    */
    @Column(name = "transactionId", updatable = false, nullable = false, length = 256)
    private String transactionId;
    @Column(updatable = false, nullable = false, length = 64)
    private String timestamp;
    @Column(updatable = false, nullable = false, length = 32)
    private boolean validity;
    @Column(updatable = false, nullable = false, length = 32)
    private String serviceTo;
    @Column(updatable = false, nullable = false, length = 256)
    private String inputData;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /*public UsersData getUsersData() {
        return usersData;
    }

    public void setUsersData(UsersData usersData) {
        this.usersData = usersData;
    }*/

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getServiceName() {
        return serviceTo;
    }

    public void setServiceName(String serviceName) {
        this.serviceTo = serviceName;
    }
}
