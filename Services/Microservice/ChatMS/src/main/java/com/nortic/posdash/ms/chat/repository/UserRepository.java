package com.nortic.posdash.ms.chat.repository;

import com.nortic.posdash.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
