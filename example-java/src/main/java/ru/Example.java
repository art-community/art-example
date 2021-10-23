package ru;

import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.*;
import static io.netty.channel.epoll.Native.*;
import java.io.*;

public class Example {
    public static void main(String[] arguments) throws IOException {
        System.out.println(KERNEL_VERSION);
        System.out.println(Errors.ERRNO_EAGAIN_NEGATIVE);
        System.out.println(Limits.IOV_MAX);
        FileDescriptor.from(".").isOpen();
        System.out.println("open");
        FileDescriptor.from(".").close();
        System.out.println("close");
    }
}
