package com.example.zlatik.repository;

import com.example.zlatik.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinRepository extends JpaRepository<Bin,Long> {
}
