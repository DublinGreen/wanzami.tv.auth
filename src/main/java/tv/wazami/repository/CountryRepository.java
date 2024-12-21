package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Country;

/**
 * Country Repository Interface
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}