package Entities;

import enums.PartnerCategory;
import enums.PartnerType;

import java.util.Objects;

public class Partner {
    private int id;
    private String name;
    private PartnerCategory category;
    private PartnerType type;
    private String contact;
    private String email;
    private String phone;
    private String address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerCategory getCategory() {
        return category;
    }

    public void setCategory(PartnerCategory category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PartnerType getType() {
        return type;
    }

    public void setType(PartnerType type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner partner)) return false;
        return id == partner.id && category == partner.category && type == partner.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, type);
    }
}
