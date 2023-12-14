package com.idCardEmployee.repository;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardAccessRepository extends JpaRepository<CardAccess, Integer> {
    List<CardAccess> findAllByLevelAccess(LevelAccess levelAccess);
}
