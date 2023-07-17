package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.CalendarComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarCommentRepository extends JpaRepository<CalendarComment, Integer> {
}
