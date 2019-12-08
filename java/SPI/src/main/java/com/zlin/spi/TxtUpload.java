package com.zlin.spi;

public class TxtUpload implements IUpload{
    @Override
    public void upload() {
        System.out.println("上传txt文件");
    }
}
