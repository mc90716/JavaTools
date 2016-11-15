package com.kafka.send;

import java.nio.ByteBuffer;

public class RequestSend extends NetWorkSend {
	
	private final String requestHead;
	private final String requestBody;
	
	public RequestSend(String destination, String requestHead, String requestBody) {
		super(destination, serialize(requestHead, requestBody));
		this.requestHead = requestHead;
		this.requestBody = requestBody;
	}
	
	public static ByteBuffer serialize(String header, String body) {
		ByteBuffer buffer = ByteBuffer.allocate(header.length() + body.length());
		buffer.put(header.getBytes(), 0, header.length());
		buffer.put(body.getBytes(), 0, body.length());
		return buffer;
	}

	public String getRequestHead() {
		return requestHead;
	}

	public String getRequestBody() {
		return requestBody;
	}
}
