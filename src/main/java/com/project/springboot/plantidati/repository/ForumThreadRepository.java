package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, Integer> {
}