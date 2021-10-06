package com.jaya.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long transactionID;
    private Long userdID;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal value;
    private BigDecimal exchangeRate;
    private LocalDateTime dateTime;
}
