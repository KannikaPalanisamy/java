package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.core.codec.Decoder;
import org.springframework.stereotype.Service;

@Service
public class EncodeDecodeService {

	public String base64String() {
		String originalInput = "stg";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("Encoded value: "+encodedString);
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		System.out.println("Decoded value: "+decodedString);

		return decodedString;
	}

	public String base64File() throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get("D:\\kannika\\text.txt"));
		String encodedString = Base64.getMimeEncoder().encodeToString(bytes);
		System.out.println(encodedString);
		byte[] decodedBytes = Base64.getMimeDecoder().decode(encodedString);
		System.out.println(new String(decodedBytes));
		return encodedString;

	}
}
