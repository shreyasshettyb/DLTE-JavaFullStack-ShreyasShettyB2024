package employee.middleware.remote;

import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;

import java.util.ArrayList;

public interface Operations {
    void create(ArrayList<Personal> personal, ArrayList<Address> permanentAddress, ArrayList<Address> temporaryAddress );
    Object[] read();
    Object[] read(Long employeeID);
    Object[] filterAddress(String parameter, Object value);

}
