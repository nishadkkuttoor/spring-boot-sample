package com.javatechie.spring.cashe.api.service;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.security.SecureRandom;
import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class SequanceGenerator {
	private volatile long sequence = 0L;
	private volatile long lastTimestamp = -1L;
	private static final int NODE_ID_BITS = 10;
	private static final int SEQUENCE_BITS = 12;
	private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
	private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);
	private static final long CUSTOM_EPOCH = 1420070400000L;

	private final int nodeId;
//	Distributed 64-bit unique ID generator inspired by Twitter Snowflake
	public SequanceGenerator() {
		this.nodeId = createNodeId();
	}
	public synchronized String nextId(Long userID) {
		long currentTimestamp = timestamp();

		if (currentTimestamp < lastTimestamp) {
			throw new IllegalStateException("Invalid System Clock!");
		}

		if (currentTimestamp == lastTimestamp) {
			sequence = (sequence + 1) & maxSequence;
			if (sequence == 0) {
				// Sequence Exhausted, wait till next millisecond.
				currentTimestamp = waitNextMillis(currentTimestamp);
			}
		} else {
			// reset sequence to start with zero for the next millisecond
			sequence = 0;
		}

		lastTimestamp = currentTimestamp;

		long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS);
		id |= (nodeId << SEQUENCE_BITS);
		id |= sequence;
		String newSequence = String.valueOf(id);
		if (userID != null)
			newSequence = userID + newSequence;
		return newSequence;
	}
	private long waitNextMillis(long currentTimestamp) {
		while (currentTimestamp == lastTimestamp) {
			currentTimestamp = timestamp();
		}
		return currentTimestamp;
	}
	private int createNodeId() {
		int nodeId;
		try {
			StringBuilder sb = new StringBuilder();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				byte[] mac = networkInterface.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X", mac[i]));
					}
				}
			}
			nodeId = sb.toString().hashCode();
		} catch (Exception ex) {
			nodeId = (new SecureRandom().nextInt());
		}
		nodeId = nodeId & maxNodeId;
		return nodeId;
	}
	// Get current timestamp in milliseconds, adjust for the custom epoch.
		private static long timestamp() {
			return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
		}
}
