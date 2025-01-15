package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Country;

/**
 * Country Repository Interface
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}