package com.midgardabc.day14.test;

import com.midgardabc.day14.shop.Category;
import com.midgardabc.day14.shop.PurchaseType;
import com.midgardabc.day14.shop.data.Stock;
import com.midgardabc.day14.shop.logic.TableData;
import com.midgardabc.day14.shop.requests.Shop;
import com.midgardabc.day14.shop.domain.Bird;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class ShopTest {

    private Shop shop;
    private Stock stock;

    @Before
    public void init(){
        shop = new Shop();
        stock = shop.getStock();
    }

    private void initUser() {
        shop.setCurrentCustomer(stock.getTableInfo().getCurrentCustomer("seroga", "sergeyprokopenko"));
    }

    @Test
    public void testLogin() {
       initUser();
       assertNotNull(shop.getCurrentCustomer());
    }

    @Test
    public void testUpdateBalance() {
        initUser();
        double balance = shop.getBalance();
        shop.putPayment(PurchaseType.PAYMENT, 100, "Replash Balance");
        shop.updateBalance(100);
        if (shop.getBalance() == balance) fail();
    }

    @Test
    public void testBuyBird() {
        initUser();
        Bird bird = stock.getProducts().get("Turkey");
        int aviable = bird.getAviableBird();
        double balance = shop.getBalance();
        int countBirds = 1;
        if (aviable >= countBirds && countBirds*bird.getRealPrice() < balance) {
            shop.buyBird(shop.getCurrentCustomer(), bird, countBirds);
            int newAviable = stock.getProducts().get("Turkey").getAviableBird();
            if (newAviable == aviable) fail();
        } else if (countBirds* bird.getRealPrice() > balance) {
            fail("No enought money to buy this bird");
        } else if (aviable < countBirds) {
            fail("No " + countBirds + " " + bird.getName() + "'s");
        }
    }

    @Test
    public void testAddBird() {
        initUser();
        double countPrice = (8 - Bird.SALES)*3;
        double balance = shop.getBalance();
        int productsSize = stock.getProducts().size();
        if (countPrice <= balance) {
            shop.addProduct("Hummingbird", Category.LITTLE, 3, 8);
            shop.updateBalance(-countPrice);
            shop.putPayment(PurchaseType.WHOLESALE, -countPrice, "Hummingbird");
            assertTrue(stock.getProducts().size() == productsSize + 1);
        } else {
            fail("Not enought money to add this bird");
        }
    }

    @Test
    public void testDeleteBird() {
        initUser();
        int productsSize = stock.getProducts().size();
        if (stock.getProducts().containsKey("Hummingbird")) {
            shop.deleteBird("Hummingbird");
            assertTrue(stock.getProducts().size() == productsSize - 1);
        } else {
            fail("No this bird on the stock");
        }
    }

    @Test
    public void testSelfBird() {
        initUser();
        Bird bird = stock.getProducts().get("Cannary");
        int aviableBird = bird.getAviableBird();
        double countPrice = (bird.getRealPrice() - Bird.SALES) * 3;
        double balance = shop.getBalance();
        if (countPrice <= balance) {
            shop.selfBird("Cannary", 3);
            shop.putPayment(PurchaseType.WHOLESALE, -countPrice, bird.getName());
            shop.updateBalance(-countPrice);
            assertTrue(aviableBird+3 == stock.getProducts().get("Cannary").getAviableBird());
        } else {
            fail("Not enought money to self " + 3 + " cannaries");
        }
    }

    @Test
    public void testChangePrice() {
        shop.changePrice("Eagle", 10);
        if (stock.getProducts().get("Eagle").getStartingPrice() != 10) fail();
    }

    @Test
    public void testRegister() {
        String login = "andrewwaves";
        String password = "andrewwaves";
        String repeatPassword = "andrewwaves";
        String name = "Andrew";
        TableData query = stock.getTableInfo();
        int tableUsersLength = query.tableLength("users");
        if (!query.checkToElement("select login from users where login = '" + login + "'") && login.length() > 4 && name.length() > 0
                && password.length() >= 8 && password.equals(repeatPassword)) {
            shop.register(name, login, password);
            boolean registerSucceful = query.checkToElement("select login from users where login = '" + login + "'");
            assertTrue(registerSucceful && query.tableLength("users") == tableUsersLength+1);
        } else {
            fail("Check your data: login must be consists more than 4 symbols, you must write name " +
                    "or password must consists of more than 8 symbols and there must mismatch");
        }
    }
}
