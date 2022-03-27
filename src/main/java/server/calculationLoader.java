package server;

import java.io.InputStream;

public class calculationLoader extends ClassLoader {

//    public static void main(String args[]) throws Exception {
//        calculationLoader javaClassLoader = new calculationLoader();
//        javaClassLoader.load();
//
//    }
    public calculationLoader()throws Exception{
//        calculationLoader javaClassLoader = new calculationLoader(String path);
    }
    public Class<?> getClass(String path) throws Exception {

        // create FileInputStream object
//        "ClassLoaderInput.class"
        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(path);

        /*
         * Create byte array large enough to hold the content of the file. Use
         * fileInputStream.available() to determine size of the file in bytes.
         */
        byte rawBytes[] = new byte[fileInputStream.available()];

        /*
         * To read content of the file in byte array, use int read(byte[]
         * byteArray) method of java FileInputStream class.
         */
        fileInputStream.read(rawBytes);

        // Load the target class
        Class<?> regeneratedClass = this.defineClass(rawBytes, 0, rawBytes.length);

        return regeneratedClass;

        // Getting a method from the loaded class and invoke it
//        regeneratedClass.getMethod("printString", null).invoke(regeneratedClass.newInstance(), null);
    }

}

