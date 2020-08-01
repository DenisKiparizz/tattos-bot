package com.tatto.bot.repository;

import com.tatto.bot.entity.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TattooRepository extends JpaRepository<Tattoo, Long> {
}
