package com.kafka.send;

import java.nio.ByteBuffer;

public class NetWorkSend extends ByteBufferSend {

	public NetWorkSend(String destination, ByteBuffer... buffers) {
		super(destination, sizeDelimit(buffers));
	}

	/**
	 * ByteBuffer数组的第一个位置为int类型，用于存放数据的size
	 * @param buffers
	 * @return
	 */
	private static ByteBuffer[] sizeDelimit(ByteBuffer[] buffers) {
		int size = 0;
		for(int i =0 ;i < buffers.length; i++) {
			size += buffers[i].remaining();
		}
		ByteBuffer[] delimited = new ByteBuffer[buffers.length + 1];
		delimited[0] = ByteBuffer.allocate(4);
		delimited[0].putInt(size);
		delimited[0].rewind();
		//数组的拷贝的话尽量使用这种方式
		System.arraycopy(buffers, 0, delimited, 1, buffers.length);
		return delimited;
	}
}
