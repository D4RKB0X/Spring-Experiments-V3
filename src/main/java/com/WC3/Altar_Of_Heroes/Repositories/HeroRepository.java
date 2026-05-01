package com.WC3.Altar_Of_Heroes.Repositories;

import com.WC3.Altar_Of_Heroes.Entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository <Hero, Long> {}