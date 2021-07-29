package he.aida.provider.entity;

import java.io.Serializable;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:56 2021/3/11
 */
public class User implements Serializable {

    private String name;
    private Integer age;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
