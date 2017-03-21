package com.kafka.bytebuffer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Test {
	public static void main(String[] args) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
    	DataOutputStream outputStream = new DataOutputStream(new ByteBufferOutputStream(byteBuffer));
    	outputStream.writeBoolean(true);
    	outputStream.writeInt(100);
    	outputStream.writeLong(10L);
    	outputStream.close();
    	
    	byteBuffer.flip();
    	
    	DataInputStream  inputStream = new DataInputStream(new ByteBufferInputStream(byteBuffer));
    	System.out.println(inputStream.readBoolean());
    	System.out.println(inputStream.readInt());
    	System.out.println(inputStream.readLong());
    	inputStream.close();
	}
}
