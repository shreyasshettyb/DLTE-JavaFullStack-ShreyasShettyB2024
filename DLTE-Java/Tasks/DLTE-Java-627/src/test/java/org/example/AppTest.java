package org.example;

import static org.example.MyBank.loanList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    App app = new App();
    @BeforeClass
    public static void initialize() {
        loanList.add(new Loan(1211214L, 52000.0, 19, "open", "varun", 741852960L));
        loanList.add(new Loan(45555151L, 2000.0, 14, "closed", "prashanth", 9963582014L));
    }

    @Test
    public void testCheckLoans() {
        assertEquals("loanNumber=52000, loanAmount=1211214.0, loanDate=19, loanStatus='open', borrowerName='varun', borrowerContact=741852960",app.checkLoans());
    }

    @Test
    public void testClosedLoans(){
        assertEquals("loanNumber=45555151, loanAmount=2000.0.0, loanDate=14, loanStatus='closed', borrowerName='prashanth', borrowerContact=9963582014",app.checkClosedLoans());

    }
}
