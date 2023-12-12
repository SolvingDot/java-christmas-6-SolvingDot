package christmas.constants;

public enum EventBadge {
    NOTHING("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String badge;
    private final int requiredAmount;

    EventBadge(String badge, int requiredAmount) {
        this.badge = badge;
        this.requiredAmount = requiredAmount;
    }

    public String getBadge() {
        return badge;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }
}
