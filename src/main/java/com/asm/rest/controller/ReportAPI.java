package com.asm.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Report;
import com.asm.service.ReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/report")
public class ReportAPI {

	@Autowired
	ReportService rpService;
	
	@RequestMapping("/get")
	public ResponseEntity<List<Report>> getReport() {
		return ResponseEntity.ok(rpService.findReport());
	}
	


}
