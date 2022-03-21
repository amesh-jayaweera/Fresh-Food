package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerDTO {
    private String name;
    private String contactNo;
}
