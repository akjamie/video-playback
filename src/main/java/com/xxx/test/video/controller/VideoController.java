package com.xxx.test.video.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v")
public class VideoController {

	private static final long DEFAULT_EXPIRE_TIME = 1800L;

	@RequestMapping(value = "/stream/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getPreview(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseEntity<byte[]> result = null;
		try {
			String pathString = "C:/Users/Jamie/Videos/BigBuck.m4v";
			Path path = Paths.get(pathString);
			byte[] image = Files.readAllBytes(path);

			response.setStatus(HttpStatus.OK.value());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentLength(image.length);
			result = new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
		} catch (java.nio.file.NoSuchFileException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return result;
	}

	/**
	 * Returns true if the given match header matches the given value.
	 * 
	 * @param matchHeader
	 *            The match header.
	 * @param toMatch
	 *            The value to be matched.
	 * @return True if the given match header matches the given value.
	 */
	private static boolean matches(String matchHeader, String toMatch) {
		String[] matchValues = matchHeader.split("\\s*,\\s*");
		Arrays.sort(matchValues);
		return Arrays.binarySearch(matchValues, toMatch) > -1 || Arrays.binarySearch(matchValues, "*") > -1;
	}
}
