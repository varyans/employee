package com.tzonesoft.employee.controller;

import com.tzonesoft.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "employer")
public class EmployerController {
    private final List<Employer> employerList = new ArrayList<>();

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employer> getList() {
        return employerList;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employer saveStudent(@RequestBody Employer employers) {
        employerList.add(employers);
        return employers;
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employer getOne(@PathVariable Integer id) {
        for (Employer employers : employerList) {
            if (employers.getId().equals(id)) {
                return employers;
            }

        }
        throw new RuntimeException();
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable Integer id) {
        Employer removedEmployer = getOne(id);
        employerList.remove(removedEmployer);
    }
    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employer putOne(@PathVariable Integer id, @RequestBody Employer replacedEmployer, @RequestHeader(name = "X-AUTH-TOKEN", required = false) String token) {
        if(token==null|| !token.equals("şifre")){
            throw new RuntimeException("Not Authenticated");
        }
        deleteOne(id);
        employerList.add(replacedEmployer);
        return replacedEmployer;
    }

}




