package com.zlin.spi;

public class ImgUpload implements IUpload{
    @Override
    public void upload() {
        System.out.println("上传图片");
    }
}
