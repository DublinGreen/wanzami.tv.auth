package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}