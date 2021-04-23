package io.wander.coronahelper.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.wander.coronahelper.data.Greeting;

//@Repository
//@Bean
public interface GreetingsRepository extends JpaRepository<Greeting, Long> {

}