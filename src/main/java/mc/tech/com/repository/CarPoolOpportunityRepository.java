package mc.tech.com.repository;



import mc.tech.com.domain.CarPoolOpportunity;
import mc.tech.com.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPoolOpportunityRepository extends JpaRepository<CarPoolOpportunity, Long> {
    List<CarPoolOpportunity> findByOriginContaining(String time);
    List<CarPoolOpportunity> findByOwner_Id(Long id);
    List<CarPoolOpportunity> findByPassengersContains(User passenger);
    CarPoolOpportunity getById(Long carPoolOpportunityId);
}