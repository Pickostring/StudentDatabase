package model;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, int> {
}