package entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Customer {

    @Id
    private String custID;
    private String cusTitle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String custID, String cusTitle, String custName, String custAddress, String city, String province, String postalcode) {
        this.custID = custID;
        this.cusTitle = cusTitle;
        this.custName = custName;
        this.custAddress = custAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCusTitle() {
        return cusTitle;
    }

    public void setCusTitle(String cusTitle) {
        this.cusTitle = cusTitle;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalcode() {
        return postalCode;
    }

    public void setPostalcode(String postalCode) {
        this.postalCode = postalCode;
    }
}
