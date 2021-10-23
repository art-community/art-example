package ru;

import io.netty.channel.unix.*;
import io.netty.channel.unix.FileDescriptor;
import java.io.*;

public class Example {
    public static void main(String[] arguments) throws IOException {
        System.out.println(Limits.IOV_MAX);
        FILEDESCRIPTOR.from(".").isOpen();
        System.out.println("open");
        FileDescriptor.from(".").close();
        System.out.println("close");
    }
}
