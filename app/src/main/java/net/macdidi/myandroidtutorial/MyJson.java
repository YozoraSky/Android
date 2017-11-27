package net.macdidi.myandroidtutorial;

/**
 * Created by T on 2017/5/13.
 */

import com.google.gson.annotations.SerializedName;

public class MyJson {
    @SerializedName("orderno")
    private String myOrderno;
    @SerializedName("ordersn")
    private String myOrdersn;
    @SerializedName("caseNumber")
    private String myCaseNumber;
    @SerializedName("customer")
    private String myCustomer;
    @SerializedName("qty")
    private String myQty;
    @SerializedName("unit")
    private String myUnit;
    @SerializedName("product")
    private String myProduct;
    @SerializedName("productType")
    private String myProductType;
    @SerializedName("productSize")
    private String myProductSize;
    @SerializedName("tagno")
    private String myTagno;
    @SerializedName("testPressure")
    private String myTestPressure;
    @SerializedName("dept")
    private String myDept;
    @SerializedName("workItem")
    private String myWorkItem;
    @SerializedName("ordersnNumber")
    private String myNumOfOrdersn = "1";

    public String getMyOrderno() {
        return myOrderno;
    }

    public void setMyOrderno(String myOrderno) {
        this.myOrderno = myOrderno;
    }

    public String getMyOrdersn() {
        return myOrdersn;
    }

    public void setMyOrdersn(String myOrdersn) {
        this.myOrdersn = myOrdersn;
    }

    public String getMyCaseNumber() {
        return myCaseNumber;
    }

    public void setMyCaseNumber(String myCaseNumber) {
        this.myCaseNumber = myCaseNumber;
    }

    public String getMyCustomer() {
        return myCustomer;
    }

    public void setMyCustomer(String myCustomer) {
        this.myCustomer = myCustomer;
    }

    public String getMyQty() {
        return myQty;
    }

    public void setMyQty(String myQty) {
        this.myQty = myQty;
    }

    public String getMyUnit() {
        return myUnit;
    }

    public void setMyUnit(String myUnit) {
        this.myUnit = myUnit;
    }

    public String getMyProduct() {
        return myProduct;
    }

    public void setMyProduct(String myProduct) {
        this.myProduct = myProduct;
    }

    public String getMyProductType() {
        return myProductType;
    }

    public void setMyProductType(String myProductTyper) {
        this.myProductType = myProductTyper;
    }

    public String getMyProductSize() {
        return myProductSize;
    }

    public void setMyProductSize(String myProductSize) {
        this.myProductSize = myProductSize;
    }

    public String getMyTagno() {
        return myTagno;
    }

    public void setMyTagno(String myTagno) {
        this.myTagno = myTagno;
    }

    public String getMyTestPressure() {
        return myTestPressure;
    }

    public void setMyTestPressure(String myTestPressure) {
        this.myTestPressure = myTestPressure;
    }

    public String getMyDept() {
        return myDept;
    }

    public void setMyDept(String myDept) {
        this.myDept = myDept;
    }

    public String getMyWorkItem() {
        return myWorkItem;
    }

    public void setMyWorkItem(String myWorkItem) {
        this.myWorkItem = myWorkItem;
    }

    public String getMyNumOfOrdersn() {
            return myNumOfOrdersn;
    }

    public void setMyNumOfOrdersn(String myNumOfOrdersn) {
        this.myNumOfOrdersn = myNumOfOrdersn;
    }
}
