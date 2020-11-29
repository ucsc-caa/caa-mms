package org.ucsccaa.mms.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.Random;

@Entity
@Data
@AllArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String password;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    public UserDetails() {
        userName = String.valueOf((int)((Math.random() * 9+1) * (Math.pow(10, 4))));
        password = String.valueOf((int)((Math.random() * 9+1) * (Math.pow(10, 7))));
    }
}