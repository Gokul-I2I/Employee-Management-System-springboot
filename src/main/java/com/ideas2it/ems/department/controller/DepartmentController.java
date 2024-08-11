package com.ideas2it.ems.department.controller;

import com.ideas2it.ems.department.dto.DepartmentDto;
import com.ideas2it.ems.department.mapper.Mapper;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.addDepartment(Mapper.convertObject(departmentDto));
    }

    @PutMapping("/delete")
    public DepartmentDto deleteDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.deleteDepartment(Mapper.convertObject(departmentDto));
    }

    @PutMapping("/update")
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(Mapper.convertObject(departmentDto));
    }

    @GetMapping("/view_id/{id}")
    public ResponseEntity<DepartmentDto> retrieveDepartmentById(@PathVariable int id) {
        DepartmentDto departmentDto = departmentService.retrieveDepartmentById(id);
        if (departmentDto != null) {
            return ResponseEntity.ok(departmentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/view_all")
    public List<Department> retrieveDepartments() {
        return departmentService.retrieveDepartments();
    }
}
