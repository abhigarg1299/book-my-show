package com.abhigarg.bookmyshow.pojo;


import lombok.Data;

@Data
public class RegionResponse {
    private int id;
    private int parentId;
    private String name;
    private String regionType;
    private Long albumId;

    public RegionResponse(int id, int parentId, String name, String regionType) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.regionType = regionType;
    }
}
