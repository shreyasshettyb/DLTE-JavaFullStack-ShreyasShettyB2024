package basics.oops;

public class DebitCard extends Account {
    //Intialization
    Long cardNumber = 1234568754215L;
    Integer cardPin = 12345;

    //Validate pin
    public boolean validatePin(Integer pin){
        if (cardPin.equals(pin))
            return true;
        return false;
    }
    //make Withdraw
    public void makeWithdraw(Double withdrawAmount){
        if(super.getAccountBalance()-withdrawAmount>=0){
            super.setAccountBalance(super.getAccountBalance()-withdrawAmount);
            System.out.println("Successfully withdraw Money from "+getAccountNumber()+ "\nAvailable balance is "+getAccountBalance()+"Rs.");
        }else {
            System.out.println("Insufficient Balance !!!");
        }
    }


}
