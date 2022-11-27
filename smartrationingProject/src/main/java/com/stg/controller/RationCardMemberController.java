package com.stg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.RationCardMember;
import com.stg.exception.RationApplicationException;
import com.stg.service.RationCardMemberService;
//import com.stg.service.UserService;

//@RestController
@RequestMapping(value = "rationCardmember")
public class RationCardMemberController {

	@Autowired
	private RationCardMemberService rationCardMemberService;

	//@PostMapping(value = "/create")
	public ResponseEntity<RationCardMember> addRationCardMember(@RequestBody RationCardMember rationCardMember,
			long rationCardNo) throws RationApplicationException {
		return new ResponseEntity(rationCardMemberService.addRationCardMember(rationCardMember, rationCardNo),
				HttpStatus.CREATED);
	}

	//@GetMapping(value = "read/{memberId}")
	public ResponseEntity<RationCardMember> readRationCard(@PathVariable int memberId)
			throws RationApplicationException {
		return new ResponseEntity<RationCardMember>(rationCardMemberService.readRationCardMember(memberId),
				HttpStatus.OK);
	}

	//@PutMapping(value = "/update")
	public ResponseEntity<RationCardMember> updateRationCard(@RequestParam int memberId, @RequestParam int age)
			throws RationApplicationException {
		return new ResponseEntity<RationCardMember>(rationCardMemberService.updateRationCardMember(memberId, age),
				HttpStatus.ACCEPTED);
	}

	//@DeleteMapping(value = "delete/{memberId}")
	public ResponseEntity<String> deleteRationCard(@PathVariable int memberId) throws RationApplicationException {
		return new ResponseEntity<String>(rationCardMemberService.deleteRationCardMember(memberId), HttpStatus.OK);
	}

	//@PutMapping(value = "/updateRationId to rationMember")
	public ResponseEntity<String> UpdateRationCardMemberToRationCard(@RequestParam int rationId,
			@RequestParam int rationCardmemberId) throws RationApplicationException {
		return new ResponseEntity<String>(
				rationCardMemberService.UpdateRationCardMemberToRationCard(rationId, rationCardmemberId),
				HttpStatus.ACCEPTED);
	}

}
