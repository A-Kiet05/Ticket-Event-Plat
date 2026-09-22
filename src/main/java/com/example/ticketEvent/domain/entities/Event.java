package com.example.ticketEvent.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table (name = "events")
@NoArgsConstructor 
@AllArgsConstructor 
@Setter 
@Getter 
@Builder 
public class Event {
    
    @Id 
    @Column (name ="event_id" , nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column (name = "name" , nullable = false)
    private String name;
    

    
    @Column(name = "start_date" , nullable = false)
    private LocalDateTime start;

    @Column (name = "end_date" , nullable = false)
    private LocalDateTime end;

    @Column (name = "venue" , nullable = false)
    private String venue;

    @Column (name = "sales_start" , nullable = false)
    private LocalDateTime salesStart;

    @Column (name = "sales_end" , nullable = false)
    private LocalDateTime salesEnd;

    @Column(name ="status" , nullable = false)
    @Enumerated (EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "organizer_id" , nullable = false)
    private User organizer;

    @ManyToMany (mappedBy = "attendedEvents")
    private List<User> attendees = new ArrayList<>();
    
    @ManyToMany(mappedBy ="staffedEvents")
    private List<User> staffMembers = new ArrayList<>();

    @OneToMany (mappedBy="event" , cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>();

    @CreatedDate 
    @Column(name = "created_at" ,updatable = false, nullable= false)
    private LocalDateTime createdAt;

    @LastModifiedDate 
    @Column (name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

    @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(start,
        event.start) && Objects.equals(end, event.end) && Objects.equals(venue, event.venue)
        && Objects.equals(salesStart, event.salesStart) && Objects.equals(salesEnd, event.salesEnd)
        && status == event.status && Objects.equals(createdAt, event.createdAt) && Objects.equals(
        updatedAt, event.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, start, end, venue, salesStart, salesEnd, status, createdAt,
        updatedAt);
  }


}
