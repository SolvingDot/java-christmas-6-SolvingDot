package christmas.model.event;

public class Badge {
    public String give(int totalBenefit) {
        if (totalBenefit >= BadgeName.SANTA.condition) {
            return BadgeName.SANTA.name;
        }
        if (totalBenefit >= BadgeName.TREE.condition) {
            return BadgeName.TREE.name;
        }
        if (totalBenefit >= BadgeName.STAR.condition) {
            return BadgeName.STAR.name;
        }
        return BadgeName.NOTHING.name;
    }

    private enum BadgeName {
        NOTHING("없음", 0),
        STAR("별", 5_000),
        TREE("트리", 10_000),
        SANTA("산타", 20_000);

        private final String name;
        private final int condition;

        BadgeName(String name, int condition) {
            this.name = name;
            this.condition = condition;
        }
    }
}
