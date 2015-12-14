package com.midgardabc.day14.shop.requests;

import com.midgardabc.day14.shop.Category;
import com.midgardabc.day14.shop.PurchaseType;
import com.midgardabc.day14.shop.data.Stock;
import com.midgardabc.day14.shop.data.database.DBRequest;
import com.midgardabc.day14.shop.data.database.Derby;
import com.midgardabc.day14.shop.domain.Bird;
import com.midgardabc.day14.shop.domain.Customer;
import com.midgardabc.day14.shop.domain.Transaction;
import com.midgardabc.day14.shop.logic.DataInfo;
import com.midgardabc.day14.shop.logic.TableData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 01.07.2015.
 */
public class Shop implements ShopOperations {

    private Stock stock;
    private Customer currentCustomer;
    private DBRequest requests;
    private TableData tableInfo;

    public Shop()
    {
        requests = new DBRequest(new Derby());
        tableInfo = new DataInfo(requests);
        stock = new Stock(requests, tableInfo);
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Stock getStock() {
        return stock;
    }

    public void buyBird(Customer customer, Bird bird, int count) {

        int aviable = stock.getProducts().get(bird.getName()).getAviableBird();
        if (count != 0 && aviable >= count) {
            Transaction t = new Transaction();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            t.setCustomer(customer);
            t.setDate(sdf.format(date));
            t.setBird(bird);
            t.setCount(count);

            stock.getTransactions().add(t);

            stock.getProducts().get(bird.getName()).updateCount(count);

            stock.getProducts().get(bird.getName()).updateProfit(count * bird.getRealPrice());

            if (stock.getProducts().get(bird.getName()).getProfit() > 500) {
                stock.getProducts().get(bird.getName()).setRealPrice(bird.getStartingPrice() - (bird.getStartingPrice() * 0.05));
            }
            if (stock.getProducts().get(bird.getName()).getProfit() > 1000) {
                stock.getProducts().get(bird.getName()).setRealPrice(bird.getStartingPrice() - (bird.getStartingPrice() * 0.1));
            }

            Bird b = stock.getProducts().get(bird.getName());
            requests.cud("update product_statistic set how_match = " + b.getAviableBird() + ", how_match_sold = " + b.getSoldBird()
                    + ",profit = " + b.getProfit() + ", price = " + b.getStartingPrice() + " where product_id = " + b.getId());

            addTransaction(customer.getId(), bird.getName(), count, new Date());
            updateBalance(-count * b.getRealPrice());
            putPayment(PurchaseType.RETAIL, -count * b.getRealPrice(), b.getName());

        }
    }

    public void selfBird(String str, int count) {
        stock.getProducts().get(str).updateSoldBird(count);
        Bird b = stock.getProducts().get(str);
        requests.cud("update product_statistic set how_match = " + b.getAviableBird() + " where product_id = " + b.getId());
    }

    public void putPayment(PurchaseType purchaseType, double sumPurchase, String typePayment) {
        requests.cud("insert into customer_payment(customer_id, type_payment, purchase_type, balance_payment)" +
                "values(" + currentCustomer.getId() + ",'" + typePayment + "','" + purchaseType.toString() +
                "'," + sumPurchase + ")");
    }

    public void changePrice(String s, double price)
    {
        stock.getProducts().get(s).updatePrice(price);
        Bird b = stock.getProducts().get(s);
        requests.cud("update product_statistic set price = " + b.getRealPrice() + " where product_id = " + b.getId());
    }

    public void deleteBird(String bird) {
        stock.getProducts().remove(bird);
        int id = stock.getTableInfo().getIdToString("products", bird, "product", "product_id");
        requests.cud("delete from products where product = '" + bird + "'");
        requests.cud("delete from product_statistic where product_id = " + id);
    }

    public void removeTransactions() {
        stock.getTransactions().clear();
        requests.cud("delete from transactions ");
        requests.cud("delete from transactions_products");
    }

    @Override
    public void addProduct(String productName, Category category, int howMach, double price) {
        requests.cud("insert into products(product, category) values('" + productName + "','" + category.toString() + "')");
        int id = tableInfo.lastId("products", "product_id");
        stock.getProducts().put(productName, new Bird(id, productName, category, howMach, price));
        System.out.println("Add new product into catalog: " + stock.getProducts().size());
        requests.cud("insert into product_statistic(product_id, how_match, price)" +
                " values(" + id +"," + howMach + "," + price + ")");
    }

    @Override
    public void addTransaction(long idCustomer, String productName, int countBirds, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String windowsFormat = sdf.format(date);

        requests.cud("insert into transactions(customer_id, date_transaction) values("+ idCustomer + ",'"+ windowsFormat + "')");

        int transactionId = tableInfo.lastId("transactions", "transaction_id");
        int productId = tableInfo.getIdToString("products", productName, "product", "product_id");
        String insert = "insert into transactions_products(transaction_id, product_id, product_count) values(" + transactionId + "," + productId + "," + countBirds + ")";
        requests.cud(insert);
    }

    @Override
    public double getBalance() {
        ResultSet res = requests.select("select balance from customers where customer_id = " + currentCustomer.getId() + " ");

        try {
            if (res.next()) {
                double money = res.getDouble("balance");
                return money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    @Override
    public void updateBalance(double sum) {
        requests.cud("update customers set balance = balance + " + sum + " where customer_id = " + currentCustomer.getId());
    }

    public void register(String nameUser, String nam, String pass) {
        requests.cud("insert into customers(name) values('" + nameUser + "')");
        int id = tableInfo.lastId("customers", "customer_id");
        Customer customer = new Customer(nameUser, id);
        stock.getCustomers().put(customer.getId(), customer);
        String insertUser = "insert into users(customer_id, login, password) values(" + id + ",'" + nam + "','" + pass + "')";
        requests.cud(insertUser);
    }

}
