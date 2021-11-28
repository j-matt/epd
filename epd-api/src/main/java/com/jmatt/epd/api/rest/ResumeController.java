package com.jmatt.epd.api.rest;

import com.jmatt.epd.api.service.EmployeeDetails;
import com.jmatt.epd.api.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employee")
@Slf4j
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final EmployeeRequestMapper employeeRequestMapper;

    @GetMapping
    public ResponseEntity<String> getEvent(@RequestParam long id) {
        log.info("Hit this controller");
        return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body("ok");
    }

    @PostMapping
    public ResponseEntity<Long> createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest){
        log.info("Creating Employee with name {} and surname {}", employeeCreateRequest.getName(), employeeCreateRequest.getSurname());
        EmployeeDetails employeeDetails = employeeRequestMapper.requestToEmployeeDetails(employeeCreateRequest);
        long newEmployeeId = resumeService.createNewEmployee(employeeDetails);
        log.info("Created Employee with name {} and surname {} and assigned id {}", employeeCreateRequest.getName(), employeeCreateRequest.getSurname(), newEmployeeId);
        return ResponseEntity.ok(newEmployeeId);
    }
}
