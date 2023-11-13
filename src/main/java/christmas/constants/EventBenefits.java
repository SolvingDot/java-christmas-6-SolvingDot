package christmas.constants;

public enum EventBenefits {
    NOTHING("없음", 0),
    DDAY_DISCOUNT("크리스마스 디데이 할인", 1000),
    DDAY_DISCOUNT_INCREASE("할인 증가율", 100),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023),
    SPECIAL_DISCOUNT("특별 할인", 1000),
    FREE_CHAMPAGNE("증정 이벤트", Menu.CHAMPAGNE.getPrice());

    private final String detail;
    private final int benefit;

    EventBenefits(String detail, int benefit) {
        this.detail = detail;
        this.benefit = benefit;
    }

    public String getDetail() {
        return detail;
    }

    public int getBenefit() {
        return benefit;
    }
}
