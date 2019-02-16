package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.view.EmployeeListViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeesListBean {

    private List<EmployeeListViewModel> employees;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeesListBean() {
    }

    @Inject
    public EmployeesListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees().stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public BigDecimal getAllSalaries() {
        Function<EmployeeListViewModel, BigDecimal> salaryMapper = EmployeeListViewModel::getSalary;
        if (this.employees.size() < 1){
            return BigDecimal.ZERO;
        } else {
            return this.employees.stream()
                    .map(salaryMapper)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    public BigDecimal getAverageSalary() {
        BigDecimal count = BigDecimal.valueOf(this.employees.size());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return this.getAllSalaries().divide(count, RoundingMode.HALF_UP);
        }
    }


    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }
}
