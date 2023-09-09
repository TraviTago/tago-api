package com.tago.domain.tag.repository;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByType(Favorite favorite);
}
