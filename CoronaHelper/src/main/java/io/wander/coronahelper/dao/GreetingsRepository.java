package io.wander.coronahelper.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.wander.coronahelper.data.Greeting;

//@Repository // does not required as Marking them as JpaRepository is enough
//@Bean
public interface GreetingsRepository extends JpaRepository<Greeting, Long> {
	// we can use Spring Data MongoDB, Spring Data GemFire, Spring Data Cassandra, etc. For this tutorial, we’ll stick with JPA.

}