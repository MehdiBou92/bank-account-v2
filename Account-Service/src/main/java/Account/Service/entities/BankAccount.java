package Account.Service.entities;

import Account.Service.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount {

    @Id
    private String id;
    private Double balance;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String currency;
    private Long customerId;
    @Transient
    private Customer customer;
}
