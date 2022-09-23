package com.example.back.repositories;

import java.util.ArrayList;
import com.example.back.models.TaskModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Long> {

}
