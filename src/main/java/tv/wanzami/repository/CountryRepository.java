package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}