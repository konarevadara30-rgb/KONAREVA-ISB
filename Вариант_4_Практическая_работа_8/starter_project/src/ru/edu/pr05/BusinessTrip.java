package ru.edu.pr05;
import java.time.*;
public class BusinessTrip {
    // Вариант 4: причина отклонения должна содержать минимум 25 символов
    public static final int MIN_REASON_LENGTH = 25;
    private final String id; private final LocalDate start; private final LocalDate end; private TripStatus status=TripStatus.DRAFT; private String rejectionReason;
    public BusinessTrip(String id, LocalDate start, LocalDate end) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Не указан id командировки");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("Не указаны даты командировки");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Дата окончания раньше даты начала");
        }
        this.id=id; this.start=start; this.end=end;
    }
    public void submit() {
        if (status != TripStatus.DRAFT) {
            throw new IllegalStateException("Отправить можно только черновик, текущий статус " + status);
        }
        status = TripStatus.SUBMITTED;
    }
    public void approve() {
        if (status != TripStatus.SUBMITTED) {
            throw new IllegalStateException("Согласовать можно только отправленную заявку, текущий статус " + status);
        }
        status = TripStatus.APPROVED;
    }
    public void reject(String reason) {
        if (status != TripStatus.SUBMITTED) {
            throw new IllegalStateException("Отклонить можно только отправленную заявку, текущий статус " + status);
        }
        if (reason == null || reason.trim().length() < MIN_REASON_LENGTH) {
            throw new IllegalArgumentException("Причина отклонения должна быть не короче " + MIN_REASON_LENGTH + " символов");
        }
        rejectionReason = reason.trim();
        status = TripStatus.REJECTED;
    }
    public void cancel() {
        if (status != TripStatus.DRAFT && status != TripStatus.SUBMITTED && status != TripStatus.APPROVED) {
            throw new IllegalStateException("Отменить заявку в статусе " + status + " нельзя");
        }
        status = TripStatus.CANCELLED;
    }
    public void complete(LocalDate today) {
        if (status != TripStatus.APPROVED) {
            throw new IllegalStateException("Завершить можно только согласованную заявку, текущий статус " + status);
        }
        if (today == null) {
            throw new IllegalArgumentException("Не указана текущая дата");
        }
        if (!today.isAfter(end)) {
            throw new IllegalStateException("Завершить можно только после даты окончания командировки");
        }
        status = TripStatus.COMPLETED;
    }
    public TripStatus getStatus() { return status; } public String getRejectionReason() { return rejectionReason; }
}
