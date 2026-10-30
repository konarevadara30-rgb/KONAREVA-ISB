package ru.edu.pr04;
public class OrderItem {
    // Вариант 4: максимальное количество в одной позиции 25 (вместо 100)
    public static final int MAX_QUANTITY = 25;
    private final Product product; private int quantity;
    public OrderItem(Product product,int quantity) { this.product=product; setQuantity(quantity); }
    private void setQuantity(int quantity) {
        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Количество должно быть от 1 до " + MAX_QUANTITY);
        }
        this.quantity=quantity;
    }
    public void increase(int delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("Увеличение должно быть больше 0");
        }
        setQuantity(quantity + delta);
    }
    public Product getProduct() { return product; } public int getQuantity() { return quantity; }
    public double getLineTotal() {
        return product.getPrice() * quantity;
    }
}
