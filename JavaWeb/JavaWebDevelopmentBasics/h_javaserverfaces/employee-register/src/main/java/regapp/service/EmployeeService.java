package regapp.service;

import regapp.domain.models.service.EmployeeServiceModel;
import regapp.domain.models.view.EmployeeListViewModel;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    boolean remove(String id);
}
