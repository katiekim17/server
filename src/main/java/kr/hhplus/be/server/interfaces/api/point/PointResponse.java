package kr.hhplus.be.server.interfaces.api.point;


import kr.hhplus.be.server.domain.model.UserPoint;

// DTO 용도
public record PointResponse(Long userId, Long amount) {
    public static PointResponse from(UserPoint point) {
        return new PointResponse(point.getUserId(), point.getPoint());
    }
}