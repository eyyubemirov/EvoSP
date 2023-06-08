package one.evospringone.model;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    @ManyToOne
    @JoinColumn(name = "school_id",referencedColumnName = "id")//referencedColumnName = "id"
    // o demekdiki men postmanda request atanda school:{id:1} yazanda 1 ci id uygun melumatlar getirsin
    private School school;

}
