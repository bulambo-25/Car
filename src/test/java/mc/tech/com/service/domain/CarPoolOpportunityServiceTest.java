package mc.tech.com.service.domain;

import lombok.extern.slf4j.Slf4j;
import mc.tech.com.domain.CarPoolOpportunity;
import mc.tech.com.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
class CarPoolOpportunityServiceTest {

    @Autowired
    CarPoolOpportunityService opportunityService;

    @Autowired
    private UserService service;

    User user_save = new User("DRAY", "BULAMBO", "0814783125", "draybulambo45@gmail.com","dray@123");
    User user_save1 = new User("Farai", "Malone", "08167469484", "farai@gmail.com","farai@123");
    CarPoolOpportunity Carpoll=new CarPoolOpportunity(
            "12:20","15:40","Cape town","Kinshasa",
            5,user_save,"don't be late",new HashSet<>());
    CarPoolOpportunity Carpoll1=new CarPoolOpportunity(
            "3:20","1:40","Paris","london",
            5,user_save,"don't be late",new HashSet<>());
    CarPoolOpportunity Carpoll2=new CarPoolOpportunity(
            "22:20","00:40","Berlin","lisbon",
            5,user_save,"don't be late",new HashSet<>());


    @Test
    void createCarPoolOpportunity() {
       User user= this.service.create(user_save);
        User user1= this.service.create(user_save);
        this.service.create(user_save1);
        log.info("",user);


        CarPoolOpportunity carPoolOpportunity=this.opportunityService.createCarPoolOpportunity(Carpoll)   ;
        CarPoolOpportunity carPoolOpportunity1=this.opportunityService.createCarPoolOpportunity(Carpoll1)   ;
        CarPoolOpportunity carPoolOpportunity2=this.opportunityService.createCarPoolOpportunity(Carpoll2)   ;
         assertNotNull(carPoolOpportunity);
        System.out.println(carPoolOpportunity);
        System.out.println(carPoolOpportunity1);
        System.out.println(carPoolOpportunity2);
        log.info("",carPoolOpportunity);
    }

    @Test
    void d_searchCarPoolOpportunities() {
       List<CarPoolOpportunity> searchCarPool=this.opportunityService.searchCarPoolOpportunities("a");
       log.info("searchCarPool::",searchCarPool);
        System.out.println("searchCarPool::"+searchCarPool);
        assertNotNull(searchCarPool);
    }

    @Test
    void e_getCarPoolOpportunitiesCreatedByUser() {

//        User email=this.service.getUserByEmail(user_save.getEmail());
        List<CarPoolOpportunity> getCarPoolOpportunitiesCreatedByUser =
                this.opportunityService.getCarPoolOpportunitiesCreatedByUser(1L);
        System.out.println(getCarPoolOpportunitiesCreatedByUser);
        assertNotNull(getCarPoolOpportunitiesCreatedByUser);
    }

    @Test
    void f_joinCarPoolOpportunity() {


        CarPoolOpportunity joinCarPoolOpportunity =this.opportunityService.joinCarPoolOpportunity(1L,1L);
        log.info("joinCarPoolOpportunity::",joinCarPoolOpportunity);
        System.out.println(joinCarPoolOpportunity);
        assertNotNull(joinCarPoolOpportunity);

    }
//
//    @Test
//    void getCarPoolOpportunitiesJoinedByUser() {
//
//        User email=this.service.getUserByEmail(user_save1.getEmail());
//        List<CarPoolOpportunity> getCarPoolOpportunitiesJoinedByUser=this.opportunityService.getCarPoolOpportunitiesJoinedByUser(email);
//        log.info("getCarPoolOpportunitiesJoinedByUser::",getCarPoolOpportunitiesJoinedByUser);
//        assertNotNull(getCarPoolOpportunitiesJoinedByUser);
//
//    }
//
//
//
    @Test
    void leaveCarPoolOpportunity() {

        this.opportunityService.leaveCarPoolOpportunity(1L,1L);
    }


}