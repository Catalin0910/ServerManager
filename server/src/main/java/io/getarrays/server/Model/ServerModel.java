package io.getarrays.server.Model;

import io.getarrays.server.enumaration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP address can not be empty or null")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imgUrl;
    private Status status;
}
