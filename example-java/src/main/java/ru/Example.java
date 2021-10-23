package ru;

import io.netty.channel.epoll.*;
import io.netty.channel.unix.FileDescriptor;
import java.io.*;

public class Example {
    public static void main(String[] arguments) throws IOException {
        FileDescriptor.from(".").isOpen();
        System.out.println("open");
        FileDescriptor.from(".").close();
        System.out.println("close");
    }
}
