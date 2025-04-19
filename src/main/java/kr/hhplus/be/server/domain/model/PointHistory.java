package kr.hhplus.be.server.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.type.TransactionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "point_history")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키
    private Long userId;
    private Long amount;
//    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDateTime createdAt;

    protected PointHistory() {
        // JPA 기본 생성자
    }

    public PointHistory(Long userId, Long amount, TransactionType type, LocalDateTime createdAt) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }
}
