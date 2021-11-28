package com.jmatt.epd.epdprofiles.repository;

import com.jmatt.epd.epdprofiles.model.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeProfileRepository extends MongoRepository<EmployeeDocument, Long> {
}
