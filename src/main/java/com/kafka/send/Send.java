package com.kafka.send;

import java.io.IOException;
import java.nio.channels.GatheringByteChannel;

public interface Send {
	
	public String destination();
	
	public boolean completed();
	
	public long writeTo(GatheringByteChannel channel) throws IOException;
	
	public long size();
}
