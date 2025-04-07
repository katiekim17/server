package kr.hhplus.be.server.domain;

import kr.hhplus.be.server.TransactionType;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
}
