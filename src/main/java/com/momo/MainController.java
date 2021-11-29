/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momo;

import com.tt.pojo.Discount;
import com.tt.pojo.Prize;
import com.tt.pojo.Product;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author anhtu
 */
public class MainController implements Initializable {

    private double money = 0;
    private static int count = 0;
    private static String flag;
    private List<Prize> listPrize = new ArrayList<Prize>();
    private List<Discount> listDiscount = new ArrayList<Discount>();

    Date date = java.util.Date.from(java.time.LocalDate.now().atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());

    NumberFormat formatter = new DecimalFormat("");

    
    @FXML
    private Button btAddMoney;
    @FXML
    private Button btCoke;
    @FXML
    private Button btPepsi;
    @FXML
    private Button btSoda;
    @FXML
    private Button btEnd;
    @FXML
    private TextField txtFieldAddMoney;
    @FXML
    private Text txtMoney;
    @FXML
    private Text txtNoti;
    

    private Product coke = new Product(1, "coke", 10000);
    private Product pepsi = new Product(2, "pepsi", 10000);
    private Product soda = new Product(1, "soda", 20000);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void importMoney(ActionEvent evt) {
        if (validMoney()) {
            txtMoney.setText(String.valueOf(formatter.format(money)));
            txtFieldAddMoney.setText("");
            txtNoti.setText("Recharge successfully");
        } else {
            txtNoti.setText("Invalid money value");
            txtFieldAddMoney.setText("");
        }
    }

    public boolean validMoney() {
        double check = Double.parseDouble(txtFieldAddMoney.getText());
        if (check == 10000 || check == 20000 || check == 50000 || check == 100000 || check == 200000) {
            money += Double.parseDouble(txtFieldAddMoney.getText());
            return true;
        }
        return false;
    }

    public void buyCoke(ActionEvent evt) {
        buyProduct(this.coke);
    }

    public void buyPepsi(ActionEvent evt) {
        buyProduct(this.pepsi);
    }

    public void buySoda(ActionEvent evt) {
        buyProduct(this.soda);
    }

    public void buyProduct(Product product) {
        if (money >= product.getPrice()) {
            money = money - product.getPrice();
            txtNoti.setText("Buy " + product.getName() + " success");
            txtMoney.setText(String.valueOf(formatter.format(money)));
            if (flag == "") {
                flag = product.getName();
                count++;
            } else {
                if (flag == product.getName()) {
                    count++;
                } else {
                    count = 1;
                    flag = product.getName();
                }
            }
            if (count == 3) {
                setDiscount();
                createDiscount();
                checkDiscount(new Prize(date, product), listDiscount.get(listDiscount.size() - 1));
                count = 0;
            }
        } else {
            txtNoti.setText("Please send more money");
        }

    }

    public void checkDiscount(Prize prize, Discount discount) {
        if (discount(discount.getPercent())) {
            if (checkBudget(prize)) {
                listPrize.add(prize);
                txtNoti.setText("You win a" + prize.getProduct().getName() + " promotion");
            }
        }
    }

    public boolean checkBudget(Prize prize) {
        double budget = 0;
        for (int i = 0; i < listPrize.size() - 1; i++) {
            if (listPrize.get(i).getDate() == date) {
                budget += listPrize.get(i).getProduct().getPrice();
            }
        }
        if (budget + prize.getProduct().getPrice() == 50000) {
            listDiscount.get(listDiscount.size() - 1).setCheckBudget(true);
        }
        if (budget <= 50000) {
            return true;
        }
        return false;
    }

    public void createDiscount() {
        if (!listDiscount.get(listDiscount.size()-1).isCheckBudget()) {
            if (date != listDiscount.get(listDiscount.size()-1).getDate()) {
                double percent = listDiscount.get(listDiscount.size()-1).getPercent() + listDiscount.get(listDiscount.size()-1).getPercent() / 2;
                listDiscount.add(new Discount(date, percent));
            }
        }
    }

    public boolean discount(double percent) {
        Random rd = new Random();
        int number = rd.nextInt(100);
        if (number < percent) {
            return true;
        }
        return false;
    }

    public void setDiscount() {
        if (listDiscount.size() == 0) {
            listDiscount.add(new Discount(date, 80.0));
        }
    }
    
    public void End(ActionEvent evt){
        txtNoti.setText("Withdraw money " + String.valueOf(formatter.format(money)) + " VND");
        money = 0;
         txtMoney.setText(String.valueOf(formatter.format(money)));
    }
}
