package com.tatto.bot.repository;

import com.tatto.bot.entity.prod.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TatooRepository extends JpaRepository<Tattoo, Long> {
}
