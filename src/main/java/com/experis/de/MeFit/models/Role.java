package com.experis.de.MeFit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Role")
@Schema(description = "Role Model")
public  @Getter
        @Setter
        @RequiredArgsConstructor
class Role {

    //autogenerated ID which never has to be defined in future functions.
    @ApiModelProperty(notes = "ID of the role", name = "id", value = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Here the static components of the model
    @Column(name="name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;

    private enum Roles {USER, CONTRIBUTER, ADMIN};

    //related data
    //role - userRight - relation
    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<UserRight> userRights = new HashSet<>();

    // restricts the output to a List of IDs of the userrights
    @JsonGetter("userRights")
    public List<String> userRights() {
        if(userRights != null) {
            return userRights.stream()
                    .map(userRight -> {
                        return "/api/v1/userRights/" + userRight.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

}
