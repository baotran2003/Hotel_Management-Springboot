package mock.project.hotelmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mock.project.hotelmanagement.validation.Phone;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE company SET deleted = true WHERE id=?")
@Where(clause = " deleted=false")
public class Company extends BaseEntity {
    @NotNull
    @Column
    private String companyName;

    @Column
    private String address;

    @Column
    private String taxNumber;

    @Phone
    @Column
    private String phone;

    @Email
    @Column
    private String email;

    @Column(name = "deleted")
    private Boolean deleted = false;
}
