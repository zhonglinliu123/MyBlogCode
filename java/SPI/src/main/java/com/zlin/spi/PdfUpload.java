package com.zlin.spi;

public class PdfUpload implements IUpload{
    @Override
    public void upload() {
        System.out.println("上传PDF");
    }
}
