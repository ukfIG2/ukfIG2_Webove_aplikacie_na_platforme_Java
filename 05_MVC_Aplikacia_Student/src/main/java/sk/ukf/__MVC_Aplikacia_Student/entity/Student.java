package sk.ukf.__MVC_Aplikacia_Student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Meno nesmie byť prázdne.")
    @Size(min = 2, max = 64, message = "Meno musí mať aspoň 2 a najviac 64 znakov.")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Priezvisko nesmie byť prázdne.")
    @Size(min = 2, max = 64, message = "Priezvisko musí mať aspoň 2 a najviac 64 znakov.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Dátum narodenia nesmie byť prázdny.")
    @Past(message = "Dátum narodenia musí byť v minulosti.")
    @Column(name = "birth_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = "Pohlavie nesmie byť prázdne.")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Email nesmie byť prázdny.")
    @Email(message = "Neplatný formát emailovej adresy.")
    @Size(max = 255, message = "Email môže mať najviac 255 znakov.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Telefónne číslo nesmie byť prázdne.")
    @Pattern(regexp = "\\+421[0-9]{9}", message = "Telefónne číslo musí začínať predvoľbou +421 a obsahovať presne 9 číslic za predvoľbou.")
    @Column(name = "phone")
    private String phone;

    @Min(value = 0, message = "Kredit nemôže byť záporný.")
    @Column(name = "credit")
    private double credit;

    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

}