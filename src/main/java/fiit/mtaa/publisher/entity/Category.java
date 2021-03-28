package fiit.mtaa.publisher.entity;

import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
