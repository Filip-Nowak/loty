package src.main.java.com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private String nickname;
    private String pass;
    private String passPlain;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "user")
    private List<SavedFlights> savedFlights;


    @ManyToMany
    @JoinTable(
            name = "user_creditCard",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "creditCard_id")
    )
    private List<CreditCard> creditCards;

    public void addRole(Role role){
        if(roles==null)
            roles=new ArrayList<>();
        roles.add(role);
    }


}
