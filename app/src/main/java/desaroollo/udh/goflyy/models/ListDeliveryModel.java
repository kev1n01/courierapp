package desaroollo.udh.goflyy.models;

public class ListDeliveryModel {
    String Address, Client, Code, Personal, PhoneClient, Status;
    Integer Total;
    public ListDeliveryModel(){

    }

    public ListDeliveryModel(String address, String client, String code, String personal, String phoneClient, String status, Integer total) {
        this.Address = address;
        this.Client = client;
        this.Code = code;
        this.Personal = personal;
        this.PhoneClient = phoneClient;
        this.Status = status;
        this.Total = total;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        this.Client = client;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String personal) {
        this.Personal = personal;
    }

    public String getPhoneClient() {
        return PhoneClient;
    }

    public void setPhoneClient(String phoneClient) {
        this.PhoneClient = phoneClient;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }
}
