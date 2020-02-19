package com.finalproject.quizsystem.repository;

import com.finalproject.quizsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    @Query(value = "SELECT new com.finalproject.quizsystem.entity.User(u.id, u.email, AVG(o.score), u.role) FROM User u JOIN u.results o GROUP BY u.id")
    Page<User> findAllUsersWithScore(Pageable pageable);
}
