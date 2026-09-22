package com.example.ticketEvent.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data 
@Entity 
@Table (name = "users")
@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter 
@Builder 
public class User {
    
    @Id 
    @Column (name = "user_id" , nullable = false , updatable = false)
    private UUID id;
  
    @Column (name = "name" , nullable = false)
    private String name;

    @Column (name = "email" , nullable = false)
    private String email;

    //TODO: Organize the event
    @OneToMany (mappedBy ="organizer" , cascade = CascadeType.ALL)
    private List<Event> organizedEvents = new ArrayList<>();
    //TODO : Attend events
    @ManyToMany 
    @JoinTable(
        name = "attended_events",
        joinColumns = @JoinColumn (name = "user_id"),
        inverseJoinColumns = @JoinColumn (name = "event_id")

    )
    private List<Event> attendedEvents = new ArrayList<>();
    //TODO : Staffing events
    @ManyToMany 
    @JoinTable (
        name = "staffed_events",
        joinColumns = @JoinColumn (name = "user_id"),
        inverseJoinColumns = @JoinColumn (name = "event_id")
    )
    private List<Event> staffedEvents = new ArrayList<>();


    @CreatedDate 
    @Column (name = "created_at" , nullable = false , updatable = false)
    private LocalDateTime createdAt;
    

    @LastModifiedDate 
    @Column (name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

    @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email,
        user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt,
        user.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, createdAt, updatedAt);
  }
}
