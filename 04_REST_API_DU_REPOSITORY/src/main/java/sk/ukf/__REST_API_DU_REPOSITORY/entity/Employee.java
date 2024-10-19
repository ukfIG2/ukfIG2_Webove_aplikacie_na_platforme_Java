package sk.ukf.__REST_API_DU_REPOSITORY.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
// define your entity here. It should have all the fields you want to store in the database.
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "Musíš zadať krstne meno.")
    @Size(min = 2, max = 30, message = "Krstné meno musí byť od 2 do 30 písmen.")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Musíš zadať priezvisko.")
    @Size(min = 2, max = 50, message = "Priezvisko musí byť od 2 do 50 písmen.")
    private String lastName;

    @Column(name = "birth_date")
    @NotNull(message = "Dátum narodenia, musí byť zadaný.")
    @Past(message = "Dátum narodenia, musí byť v minulosti")
    private LocalDate birthDate;

    @Column(name = "email")
    @NotBlank(message = "Email musí byť zadaný.")
    @Email(message = "Email musí byť, validný.")
    @Size(max = 100, message = "Email, by mal byť menej ako 100 znakov.")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]", message = "Email should be valid and in the format string@string.string")
    /*
    ^[\\w.%+-]+: The local part (before the @) allows for one or more alphanumeric characters, dots, percentages, pluses, or hyphens.
    @[\\w.-]+: The domain part (after the @ but before the dot) allows for one or more alphanumeric characters, dots, or hyphens.
    \\.[a-zA-Z]: This ensures at least one alphabetic character after the dot.
    */
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Telefónne číslo, musí byť zadané.")
    @Pattern(regexp = "^\\+\\d{12}$", message = "Telefónne číslo, by malo byť vo forme +čččččččččččč.")
    private String phone;

    @Column(name = "job_title")
    @NotBlank(message = "Musíš zadať pracovnú pozíciu.")
    @Size(min = 2, max = 100, message = "Pracovná pozícia, musí byť od 2 do 100 písmen.")
    private String jobTitle;

    @Column(name = "salary")
    @NotNull(message = "Plat, musí byť zadaný.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Plat, musí byť větší než 0.")
    /*
    inclusive = true: Allows the value to be exactly the limit (in your case, salary can be exactly 0.0).
    inclusive = false: The value must be strictly greater than the limit (salary must be more than 0.0).
    */
    @DecimalMax(value = "10000.0", message = "Plat, musí byť menší než 10000.")
    //in database salary is decimal
    private Double salary;

    @Column(name = "full_time")
    @NotNull(message = "Musíš zadať, či si na plný uväzok, alebo nie.")
    //in database, it is tinyint
    private Boolean fullTime;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, LocalDate birthDate, String email, String phone, String jobTitle, Double salary, Boolean fullTime) {
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

    public Boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }
}
