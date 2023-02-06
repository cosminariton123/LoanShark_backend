package com.loansharkmss.LoanShark.v1.dtos;

public class ImageCard {

    private final Long id;

    private final String name;

    private final String type;

    private final byte[] data;

    public ImageCard(Long id, String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }
}
