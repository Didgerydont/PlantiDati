package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Integer> {
}
