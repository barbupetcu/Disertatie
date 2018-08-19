package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.Dept;


public interface DeptRepository extends JpaRepository<Dept, Long> {}