package ru.edu.pr03;
public class ApprovalRouter {
    // Вариант 4: порог финансового согласования снижен на 25 %: 500 000 - 25 % = 375 000
    public static final long FINANCE_LIMIT = 500_000L * 75 / 100;
    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (risk == null) {
            throw new IllegalArgumentException("Не указан уровень риска");
        }
        if (containsPersonalData) {
            return ApprovalRoute.SECURITY_REVIEW;
        }
        if (risk == RiskLevel.HIGH) {
            return ApprovalRoute.DIRECTOR_REVIEW;
        }
        if (amount >= FINANCE_LIMIT) {
            return ApprovalRoute.FINANCE_REVIEW;
        }
        if (urgent) {
            return ApprovalRoute.FAST_TRACK;
        }
        return ApprovalRoute.STANDARD;
    }
}
