package kr.hhplus.be.server.domain.model;

import lombok.Getter;

@Getter
public class Inventory {

    private final Long productId;
    private int totalStock; // 원래 가지고 있는 상품 전체 갯수
    private int reservedStock; // 예약 재고
    private int confirmedStock; // 확정 재고

    public Inventory(Long productId, int total, int reserved, int confirmed) {
        this.productId = productId;
        this.totalStock = total;
        this.reservedStock = reserved;
        this.confirmedStock = confirmed;
    }

    // 재고 테이블에서 해당 상품 재고 확인
    public boolean hasStock(int requestedQty) {
        return (totalStock - reservedStock - confirmedStock) >= requestedQty;
    }

//    public void hasStock(int quantity){
//        if (confirmedStock < quantity){
//            throw new IllegalStateException("재고 부족");
//        }
//    }

    public int availableStock() {
        return totalStock - reservedStock - confirmedStock;
    }


    public void reserve(int quantity) {
        if (availableStock() < quantity)
            throw new IllegalStateException("재고 부족");
        this.reservedStock += quantity;
    }

    public void confirm(int quantity) {
        if (reservedStock < quantity)
            throw new IllegalStateException("예약 재고 부족");
        this.reservedStock -= quantity;
        this.confirmedStock += quantity;
    }

    public void ship(int quantity) {
        if (confirmedStock < quantity)
            throw new IllegalStateException("확정 재고 부족");
        this.confirmedStock -= quantity;
    }

}
