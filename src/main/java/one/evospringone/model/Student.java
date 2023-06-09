package one.evospringone.model;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.evospringone.dto.SchoolDto;

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;
    @ManyToOne
    //referencedColumnName = "id"
    //o demekdiki men postmanda request atanda school:{id:1} yazanda 1 ci id uygun melumatlar getirsin
    @JoinColumn(name = "university_id",referencedColumnName = "id")
    private School school;

}
