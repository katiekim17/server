package kr.hhplus.be.server.domain.model;

import kr.hhplus.be.server.domain.type.TransactionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PointHistory {
    private final Long userId;
    private final Long amount;
    private final TransactionType type;
    private final LocalDateTime createdAt;

    public PointHistory(Long userId, Long amount, TransactionType type, LocalDateTime createdAt) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }
}
