package com.zlin.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {
        testSpi();
    }

    private static void testSpi() {
        // 第一步:把我们的接口类型保存到ServiceLoader变量中
        // 第二步:创建一个我们 懒加载的迭代器
        // 1.保存了IUpload接口到service   2.保存线程上下文类加载器(AppClassLoader)  3.创建一个懒加载的迭代器LazyIterator
        ServiceLoader<IUpload> iUploads = ServiceLoader.load(IUpload.class);

        // 返回我们懒加载的迭代器对象
        Iterator<IUpload> iUploadIterator = iUploads.iterator();

        // iUploadIterator.hasNext(): 读取"META-INF/services/接口全类名"的内容
        // 第一次把"META-INF/services/接口全类名"文件中的第一个值保存到newxtName变量  (第一个实现类的全限定名)
        // 第二次把第二个值保存到nextName变量(第二个实现类的全限定名)   依次下去...
        while (iUploadIterator.hasNext()) {
            // iUploadIterator.next() 得到的是接口的实现类的实例
            // iUploadIterator.next() 会调用  c = Class.forName(cn, false, loader);
            // 会触发类的初始化 ==> 调用类的静态代码块    初始化只会触发一次
            iUploadIterator.next().upload();
        }
    }
}
