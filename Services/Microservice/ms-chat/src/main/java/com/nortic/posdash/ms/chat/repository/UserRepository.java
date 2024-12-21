package com.nortic.posdash.ms.chat.repository;

import com.nortic.posdash.models.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @EntityGraph(attributePaths = "role")
    List<UserEntity> findAllByIdIn(Set<UUID> userIds);
    @EntityGraph(attributePaths = "role")
    Optional<UserEntity> findById(@NonNull UUID id);
}
