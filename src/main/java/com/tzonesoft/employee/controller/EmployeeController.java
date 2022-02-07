package com.tzonesoft.employee.controller;

import com.tzonesoft.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(path = "employee")
public class EmployeeController {
    private final List<Employee> employees= new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAll(@RequestParam(required = false) String name){
        if(name==null){
            return employees;
        }
        List<Employee> filteredList= new ArrayList<>();
        for (Employee employee : employees) {
            if(Objects.equals(employee.getName(), name)){
                filteredList.add(employee);
            }
        }
        return filteredList;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee saveStudent(@RequestBody Employee employee) {
        employees.add(employee);
      return employee;
    }
     @GetMapping(path = "{id}")
    public Employee getOne (@PathVariable Integer id){
         for (Employee employee : employees) {
             if(employee.getId().equals(id)){
                 return employee;
             }
         }
        throw new RuntimeException("Employee Not Found");
    }

    @DeleteMapping (path = "{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable Integer id){
        Employee removedEmployee = getOne(id);
        employees.remove(removedEmployee);

    }
    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee putOne(@PathVariable Integer id, @RequestBody Employee replacedEmployee) {
       deleteOne(id);
       employees.add(replacedEmployee);
       return replacedEmployee;
    }


}
