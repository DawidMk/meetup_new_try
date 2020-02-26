import lombok.Getter;

@Getter
public class Address {
    Integer langCode;
    String hsn;

    public Address(Integer langCode, String hsn) {
        this.langCode = langCode;
        this.hsn = hsn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return com.google.common.base.Objects.equal(langCode, address.langCode) &&
                com.google.common.base.Objects.equal(hsn, address.hsn);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(langCode, hsn);
    }
}