package com.kafka.send;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;

public class ByteBufferSend implements Send {

	private final String destination;
	private final int size;
	private final ByteBuffer[]  buffers;
	private int remaining;
	
	public ByteBufferSend(String destination, ByteBuffer... buffers) {
		super();
		this.destination = destination;
		for(int i = 0; i < buffers.length; i++) {
			remaining += buffers[i].remaining();
		}
		this.size = remaining;
		this.buffers = buffers;
	}
	
	@Override
	public String destination() {
		return this.destination;
	}

	@Override
	public boolean completed() {
		return remaining <= 0;
	}

	@Override
	public long writeTo(GatheringByteChannel channel) throws IOException {
		long written = channel.write(buffers);
		if(written < 0) {
			
		}
		remaining -= written;
		return written;
	}

	@Override
	public long size() {
		return this.size;
	}

}
