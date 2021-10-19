package views.Tm;

public class CustomerTm {
    private String CustId;
    private String CustTitle;
    private String CustName;
    private String CustAddress;
    private String City;
    private String Province;

    public CustomerTm() {
    }

    public CustomerTm(String custId, String custTitle, String custName, String custAddress, String city, String province, String postalCode) {
        this.CustId = custId;
        this.CustTitle = custTitle;
        this.CustName = custName;
        this.CustAddress = custAddress;
        this.City = city;
        this.Province = province;
        this.PostalCode = postalCode;
    }

    private String PostalCode;

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public String getCustTitle() {
        return CustTitle;
    }

    public void setCustTitle(String custTitle) {
        CustTitle = custTitle;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "CustId='" + CustId + '\'' +
                ", CustTitle='" + CustTitle + '\'' +
                ", CustName='" + CustName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }
}