package basics.services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String to ;
    private String remarks;
}