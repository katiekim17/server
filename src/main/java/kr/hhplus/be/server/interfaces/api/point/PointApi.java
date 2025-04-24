package kr.hhplus.be.server.interfaces.api.point;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PointApi {

    @Operation(summary = "특정 유저의 포인트를 조회하는 기능")
    @GetMapping("/{id}")
    ResponseEntity<PointResponse> getUserPoint(@PathVariable Long userId);

    @Operation(summary = "특정 유저의 포인트 충전/이용 내역을 조회")
    @GetMapping("{id}/histories")
    ResponseEntity<List<PointResponse>> getPointHistory(@RequestParam Long userId);

    @Operation(summary = "특정 유저의 포인트를 충전")
    @PatchMapping("{id}/charge")
    ResponseEntity<PointResponse> setCharge(
               @PathVariable long userId,
               @RequestBody long amount
    );

    @Operation(summary = "특정 유저의 포인트를 사용")
    @PatchMapping("{id}/use")
    ResponseEntity<PointResponse> usePoint(
            @PathVariable long userId,
            @RequestBody long amount
    );

}