package com.yangzai.collection.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Objects;


/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 17:06
 * @Version 1.0
 */
@Data
@Builder
@ToString
public class Person  implements Comparable<Person>{
    private Integer id;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, password);
    }

    @Override
    public int compareTo(Person o) {
        int i = this.id - o.id;
        return i;
    }
}
