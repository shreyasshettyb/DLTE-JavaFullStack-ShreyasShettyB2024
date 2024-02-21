package basics.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bonds {
    private Double maturityAmount;
    private Double interestRate;
    private boolean taxStatus;
    private String bondholder;
    private Integer period;
}

