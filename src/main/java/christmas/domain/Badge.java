package christmas.domain;

import christmas.constants.EventBadge;

public class Badge {
    public String checkForBadge(int benefitAmount) {
        if (benefitAmount >= EventBadge.SANTA.getRequiredAmount()) {
            return EventBadge.SANTA.getBadge();
        }
        if (benefitAmount >= EventBadge.TREE.getRequiredAmount()) {
            return EventBadge.TREE.getBadge();
        }
        if (benefitAmount >= EventBadge.STAR.getRequiredAmount()) {
            return EventBadge.STAR.getBadge();
        }
        return EventBadge.NOTHING.getBadge();
    }
}
