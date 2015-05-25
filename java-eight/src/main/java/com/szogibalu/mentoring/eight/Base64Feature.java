package com.szogibalu.mentoring.eight;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Feature {

    public static void main(String[] args) {
	final String text = "This isn't so bad, huh? Makin' bucks, gettin' exercise, workin' outside.";

	final Encoder encoder = Base64.getEncoder();
	final Decoder decoder = Base64.getDecoder();

	final String encoded = encoder.encodeToString(text.getBytes(UTF_8));
	System.out.println(encoded);

	final String decoded = new String(decoder.decode(encoded), UTF_8);
	System.out.println(decoded);
    }

}
