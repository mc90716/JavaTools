package com.kafka.send;

import java.nio.ByteBuffer;

public class ResponseSend extends NetWorkSend {
	
	private final String responseHead;
	private final String responseBody;
	
	public ResponseSend(String destination, String responseHead, String responseBody) {
		super(destination, serialize(responseHead, responseBody));
		this.responseHead = responseHead;
		this.responseBody = responseBody;
	}
	
	public static ByteBuffer serialize(String header, String body) {
		ByteBuffer buffer = ByteBuffer.allocate(header.length() + body.length());
		buffer.put(header.getBytes(), 0, header.length());
		buffer.put(body.getBytes(), 0, body.length());
		return buffer;
	}

	public String getResponseHead() {
		return responseHead;
	}

	public String getResponseBody() {
		return responseBody;
	}
}

