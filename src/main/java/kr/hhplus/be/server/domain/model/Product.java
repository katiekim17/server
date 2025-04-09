package kr.hhplus.be.server.domain.model;
import lombok.Getter;
import java.time.LocalDateTime;

// ERD에서 상품정보테이블, item_info
public class Product {
    @Getter private final Long productId;
    @Getter private final String name;
    @Getter private final int price;
    @Getter private final Long registerId;
    @Getter private final int category;
    @Getter private final LocalDateTime createdAt;
    @Getter private final LocalDateTime updatedAt;


    // 생성자 생성
    public Product(Long productId, String name, int price, Long registerId, int category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (price < 0) throw new IllegalArgumentException("가격은 0 이상이어야 합니다");
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.registerId = registerId;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 비즈니스 로직 작성
    // **"도메인의 핵심 정책, 규칙, 판단"**이 들어간 코드들 == 비즈니스 로직
    // TODO 아이디로 상품정보 조회 -> 상품정보 가져옴


    // TODO 이름으로 상품정보 조회 -> 상품정보 가져옴

    // 가격이 무료인지 판단
    public boolean isFree(){
        return this.price == 0;
    }

    // 특정 이름이 포함되는지 비교
    public boolean isSameName(String keyword) {
        return name.contains(keyword);
    }



}

