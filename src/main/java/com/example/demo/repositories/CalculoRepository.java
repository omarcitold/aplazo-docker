package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.LoggerModel;

@Repository
public interface CalculoRepository extends CrudRepository<LoggerModel, Long>{

}
