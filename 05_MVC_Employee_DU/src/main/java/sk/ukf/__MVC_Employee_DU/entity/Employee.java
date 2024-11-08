package sk.ukf.__MVC_Employee_DU.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Krstné meno, nemôže byť prázdne.")
    @Size(min = 2, max = 75, message = "Krstné meno musí mať aspoň 2 až 75 znakov.")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Priezvisko, nemôže byť prázdne.")
    @Size(min = 2, max = 50, message = "Priezvisko musí mať aspoň 2 až 75 znakov.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Dátum narodenia, musí byť v minulosti.")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Email nemôže byť prázdny.")
    //@Email(message = "Email should be valid")
    @Pattern(
            regexp = "^[\\w\\d._%+-]+@[\\w\\d.-]+\\.[A-Za-z]{2,6}$",
            message = "Email, môže mať iba písmena, čísla a \'_\'. Email, musí mať formu použivateľské meno'@'doména'.'krajina alebo ina koncovka."
    )
    @Size(max = 100, message = "Email musí mať menej ako 100 znakov.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+[0-9]{12}$",
            message = "Phone number must be in the format '+123456789012' with exactly 12 digits after the '+'"
    )
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Pracovna pozícia, nesmie byť prázdna.")
    @Size(min = 2, max = 50, message = "Pracovná pozícia, musí mať aspoň 2 až 75 znakov.")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Plat nesmie byť prázdny.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Plat muší byť viac ako 0,0.")
    @DecimalMax(value = "100000.0", message = "Plat viac ako 100 000? Koho uplácaš?")
    @Column(name = "salary")
    private Double salary;

    @NotBlank(message = "Typ úvazku, nesmie byť prázdny.")
    @Size(min = 2, max = 75, message = "Typ úvazku musi byť od 2 do 75 znakov.")
    @Column(name = "full_time")
    private String fullTime;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, LocalDate birthDate, String email, String phone, String jobTitle, Double salary, String fullTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.salary = Objects.requireNonNullElse(salary, 0.0);
        this.fullTime = fullTime;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }
}
