package mc.tech.com.service.domain;


import lombok.extern.slf4j.Slf4j;
import mc.tech.com.domain.CarPoolOpportunity;
import mc.tech.com.domain.User;
import mc.tech.com.repository.CarPoolOpportunityRepository;
import mc.tech.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class CarPoolOpportunityService {
    private final CarPoolOpportunityRepository carPoolOpportunityRepository;
    private final UserRepository userRepository;
    @Autowired
    public CarPoolOpportunityService(CarPoolOpportunityRepository carPoolOpportunityRepository, UserRepository userRepository) {
        this.carPoolOpportunityRepository = carPoolOpportunityRepository;
        this.userRepository = userRepository;
    }


    public CarPoolOpportunity createCarPoolOpportunity(CarPoolOpportunity carPoolOpportunity) {
//        List<CarPoolOpportunity> overlappingOpportunities = carPoolOpportunityRepository.findByDepartureTimeBetween(
//                carPoolOpportunity.getDepartureTime(), carPoolOpportunity.getExpectedArrivalTime());
//        if (!overlappingOpportunities.isEmpty()) {
//            throw new RuntimeException("There is already a car-pool opportunity registered for the given time frame.");
//        }
        CarPoolOpportunity carPoolOpportunity1=carPoolOpportunityRepository.save(carPoolOpportunity);;
          log.info("createCarPoolOpportunity::",carPoolOpportunity1.getId());
        return carPoolOpportunity1;
    }
    public CarPoolOpportunity findCarPoolOpportunityById(Long carPoolOpportunityId) {

        CarPoolOpportunity carPoolOpportunity1=this.findCarPoolOpportunityById(carPoolOpportunityId);
        return carPoolOpportunity1;
    }

    public List<CarPoolOpportunity> searchCarPoolOpportunities(String origin) {
        List<CarPoolOpportunity>  carPoolOpportunity=carPoolOpportunityRepository.findByOriginContaining(origin);
        log.info("searchCarPoolOpportunities::",carPoolOpportunity);
        return carPoolOpportunity;
    }

    public List<CarPoolOpportunity> getCarPoolOpportunitiesCreatedByUser(Long id) {

        List<CarPoolOpportunity>listgetCarPoolOpportunitiesCreatedByUser=carPoolOpportunityRepository.findByOwner_Id(id);
        log.info("getCarPoolOpportunitiesCreatedByUser::",listgetCarPoolOpportunitiesCreatedByUser);
        return listgetCarPoolOpportunitiesCreatedByUser;
    }

    public List<CarPoolOpportunity> getCarPoolOpportunitiesJoinedByUser(User user) {
        List<CarPoolOpportunity> getCarPoolOpportunitiesJoinedByUser=carPoolOpportunityRepository.findByPassengersContains(user);
        return getCarPoolOpportunitiesJoinedByUser;
    }

//    public CarPoolOpportunity joinCarPoolOpportunity(CarPoolOpportunity carPoolOpportunity, User passenger) {
//
//        if (carPoolOpportunity.getAvailableSeats() == 0         || carPoolOpportunity.getPassengers().contains(passenger.getEmail())) {
//            throw new RuntimeException("This car-pool opportunity is already full or you have already joined it.");
//        }
//        carPoolOpportunity.getPassengers().add(passenger);
//        carPoolOpportunity.setAvailableSeats(carPoolOpportunity.getAvailableSeats() - 1);
//        CarPoolOpportunity carPoolOpportunity1= carPoolOpportunityRepository.save(carPoolOpportunity);
//        return carPoolOpportunity1;
//    }

    public CarPoolOpportunity joinCarPoolOpportunity(Long carPoolOpportunityId,Long UserId) {
        CarPoolOpportunity carPoolOpportunity=this.carPoolOpportunityRepository.getById(carPoolOpportunityId);

        User user=this.userRepository.getById(UserId);


        if (carPoolOpportunity.getAvailableSeats() == 0         || carPoolOpportunity.getPassengers().contains(user)) {
            throw new RuntimeException("This car-pool opportunity is already full or you have already joined it.");
        }
        carPoolOpportunity.getPassengers().add(user);
        carPoolOpportunity.setAvailableSeats(carPoolOpportunity.getAvailableSeats() - 1);
        CarPoolOpportunity carPoolOpportunity1= carPoolOpportunityRepository.save(carPoolOpportunity);
        return carPoolOpportunity1;
    }

    public CarPoolOpportunity leaveCarPoolOpportunity(Long  carId, Long userId) {
        CarPoolOpportunity carPoolOpportunity=this.carPoolOpportunityRepository.getById(carId);
        User passenger=this.userRepository.getById(userId);
        if (!carPoolOpportunity.getPassengers().contains(passenger)) {
            throw new RuntimeException("You are not currently part of this car-pool opportunity.");
        }
        carPoolOpportunity.getPassengers().remove(passenger);
        carPoolOpportunity.setAvailableSeats(carPoolOpportunity.getAvailableSeats() + 1);
        CarPoolOpportunity carPoolOpportunity1=carPoolOpportunityRepository.save(carPoolOpportunity);
        return carPoolOpportunity1;
    }}