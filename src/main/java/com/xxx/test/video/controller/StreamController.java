package com.xxx.test.video.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.test.video.utils.MultipartFileSender;

@RestController
@RequestMapping("/v1")
public class StreamController {

	@RequestMapping(value = "/stream/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getPreview(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pathString = "C:/Users/Jamie/Videos/BigBuck.m4v";
		Path path = Paths.get(pathString);

		MultipartFileSender.fromPath(path).with(request).with(response).serveResource();
	}
}
