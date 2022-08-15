package com.example.bibliophilia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

// Repository: Abstraktion von Spring, um einfach auf DB zuzugreifen
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
