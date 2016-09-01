package com.rucsok.pun.service.domain;

import java.util.Random;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class PunServiceRandom extends Random {

	public long nextLong(long n) {
		// error checking and 2^x checking removed for simplicity.
		long bits, val;
		do {
			bits = (nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits-val+(n-1) < 0L);
		return val;
	}
	
}
