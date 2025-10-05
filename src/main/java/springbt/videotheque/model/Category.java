package springbt.videotheque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class Category {

    @NotBlank(message = "Name can't be empty")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
