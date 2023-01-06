package sn.job.crub_employee_h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sn.job.crub_employee_h2.exception.RecordNotFoundException;
import sn.job.crub_employee_h2.model.EmployeeEntity;
import sn.job.crub_employee_h2.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping
    public String getAllEmployees(Model model){

        System.out.println("getAllEmployees");

        List<EmployeeEntity> list = employeeService.getAllEmployees();

        model.addAttribute("employees", list);

        return "list_employees";
    }


    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeesById(Model model, @PathVariable("id")Optional<Long> id) throws  RecordNotFoundException{

        System.out.println("editEmployeeById" + id);

        if (id.isPresent())
        {
            EmployeeEntity entity = employeeService.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        }else{
            model.addAttribute("employee", new EmployeeEntity());
        }
        return "add_edit_employee";
    }


    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById( @PathVariable("id") Long id ) throws RecordNotFoundException{

        System.out.println("deleteEmployeeId");

        employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }


    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(EmployeeEntity employee){
        System.out.println("createOrUpdateEmployee");

        employeeService.createOrUpdateEmployee(employee);

        return "redirect:/";

    }

}
