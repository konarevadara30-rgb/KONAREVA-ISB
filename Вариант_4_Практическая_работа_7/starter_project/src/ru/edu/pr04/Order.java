package ru.edu.pr04;
import java.util.*;
public class Order {
    private final Employee employee; private final List<OrderItem> items=new ArrayList<>(); private OrderStatus status=OrderStatus.DRAFT;
    public Order(Employee employee) { this.employee=Objects.requireNonNull(employee); }
    public void addProduct(Product product,int quantity) {
        if (status == OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Заказ уже подтверждён, изменять нельзя");
        }
        if (product == null) {
            throw new IllegalArgumentException("Товар не указан");
        }
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increase(quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }
    public void removeProduct(String productId) {
        if (status == OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Заказ уже подтверждён, изменять нельзя");
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(productId)) {
                items.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Товар " + productId + " не найден в заказе");
    }
    public double total() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getLineTotal();
        }
        return sum;
    }
    public void confirm() {
        if (status == OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Заказ уже подтверждён");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Нельзя подтвердить пустой заказ");
        }
        status = OrderStatus.CONFIRMED;
    }
    public List<OrderItem> getItems() { return List.copyOf(items); }
    public OrderStatus getStatus() { return status; }
}
