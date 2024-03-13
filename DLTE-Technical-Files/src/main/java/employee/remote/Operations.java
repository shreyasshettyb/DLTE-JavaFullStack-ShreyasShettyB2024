package employee.remote;

import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;

public interface Operations {
    void create(Personal personal, Address permanentAddress, Address temporaryAddress);
    Object[] read();
    Object[] read(Long employeeID);
    Object[] filterAddress(String parameter, Object value);

}
