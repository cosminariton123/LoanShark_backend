package com.loansharkmss.LoanShark.v1.dtos;

public class ImageCard {
    private final byte[] data;

    public ImageCard(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
