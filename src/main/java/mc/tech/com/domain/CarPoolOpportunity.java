package mc.tech.com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class CarPoolOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CarPoolOpportunity(String departureTime, String expectedArrivalTime, String origin, String destination, Integer availableSeats, User owner, String notes, Set<User> passengers) {
        this.departureTime = departureTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.owner = owner;
        this.notes = notes;
        this.passengers = passengers;
    }

    @NotNull(message = "Departure time is required")
    private String  departureTime;

    @NotNull(message = "Expected arrival time is required")
    private String expectedArrivalTime;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Available seats is required")
    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;



    private String notes;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<User> passengers = new HashSet<>();

    @Override
    public String toString() {
        return "CarPoolOpportunity{" +
                "id=" + id +
                ", departureTime='" + departureTime + '\'' +
                ", expectedArrivalTime='" + expectedArrivalTime + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                ", owner=" + owner +
                ", notes='" + notes + '\'' +
                ", passengers=" + passengers +
                '}';
    }
    // getters and setters
}
