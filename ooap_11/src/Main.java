import java.util.concurrent.*;

class ProductPageScraper implements Callable<String> {
    private final String url;

    public ProductPageScraper(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        // Симулюємо процес скрапінгу сторінки товару
        Thread.sleep(2000); // Симуляція затримки для скрапінгу
        return "Product page scraped successfully: " + url;
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Створення пула потоків з двома потоками

        // URL адреси сторінок товару
        String url1 = "https://example.com/product1";
        String url2 = "https://example.com/product2";

        // Створення об'єктів Callable для кожної сторінки товару
        Callable<String> scraper1 = new ProductPageScraper(url1);
        Callable<String> scraper2 = new ProductPageScraper(url2);

        // Запуск задач на виконання в пулі потоків
        Future<String> future1 = executor.submit(scraper1);
        Future<String> future2 = executor.submit(scraper2);

        // Отримання результатів виконання
        try {
            String result1 = future1.get(); // Блокуємо основний потік до завершення операції
            String result2 = future2.get(); // Блокуємо основний потік до завершення операції

            System.out.println(result1);
            System.out.println(result2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown(); // Завершуємо пул потоків після завершення виконання
    }
}
