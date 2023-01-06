package sn.job.crub_employee_h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.job.crub_employee_h2.exception.RecordNotFoundException;
import sn.job.crub_employee_h2.model.EmployeeEntity;
import sn.job.crub_employee_h2.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees()
    {
        System.out.println("getAllEmployees");
        List<EmployeeEntity> result = (List<EmployeeEntity>) employeeRepository.findAll();

        if (result.size() > 0){
            return result;
        }else {
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        System.out.println("getEmployeesById");
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        }else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }

    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity)
    {
    System.out.println("createOrUpdateEmployee");
    //create new entity
        if (entity.getId() ==null) {
            entity = employeeRepository.save(entity);
            return entity;
        }else {
            // Update existing entity
            Optional<EmployeeEntity> employee =employeeRepository.findById(entity.getId());
            if (employee.isPresent())
            {
                EmployeeEntity newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstname(entity.getFirstname());
                newEntity.setLastname(entity.getLastname());

                newEntity = employeeRepository.save(newEntity);
                return newEntity;

            }else {
                entity = employeeRepository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        System.out.println("deleteEmployeeById");

        Optional<EmployeeEntity> employee = employeeRepository.findById(id);

        if(employee.isPresent())
        {
            employeeRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }

    }
}
