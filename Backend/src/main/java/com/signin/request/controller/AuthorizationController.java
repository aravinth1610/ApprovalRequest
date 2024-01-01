package com.signin.request.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signin.request.constant.Properties;
import com.signin.request.entity.Status;
import com.signin.request.entity.User;
import com.signin.request.services.ApprovalServices;
import com.signin.request.services.DashboardServices;
import com.signin.request.services.DeniedServices;
import com.signin.request.services.PendingServices;

@RestController
@RequestMapping("/signinrequest/authorization")
public class AuthorizationController {

	@Autowired
	private PendingServices pendingServices;
	@Autowired
	private DashboardServices dashboardServices;
	@Autowired
	private ApprovalServices approvalServices;
	@Autowired
	private DeniedServices deniedServices;
//	@Autowired
//	private Properties pro;

	@GetMapping("/pending")
	private final ResponseEntity<?> getPendingDetails() {
		// System.out.println(pro.getName());
		// System.out.println(pro.getVersion());

		return new ResponseEntity<List<?>>(pendingServices.getPendingDetails(), HttpStatus.OK);
	}

	@PostMapping("/pending/{statusId}")
	private final ResponseEntity<?> pendingDetails(@PathVariable(value = "statusId") Long statusId) {
		User userDetails = pendingServices.pendingDetail(statusId);
		return ((userDetails != null) ? new ResponseEntity<User>(userDetails, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PutMapping("/pending/approval/{statusId}")
	private final ResponseEntity<?> updatePendingStatusApproval(@PathVariable(value = "statusId") Long statusId) {
		System.out.println(statusId);
		int isStatusUpdated = pendingServices.updateApprovalStatus(statusId);
		return ((isStatusUpdated == 1) ? new ResponseEntity<Integer>(HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PutMapping("/pending/denied/{statusId}")
	private final ResponseEntity<?> updatePendingStatusDenied(@PathVariable(value = "statusId") Long statusId,
			@RequestParam(value = "statusMessage") String statusMessage) {
		System.out.println(statusId);
		int isStatusUpdated = pendingServices.updateDeniedStatus(statusId, statusMessage);
		return ((isStatusUpdated == 1) ? new ResponseEntity<Integer>(HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/dashboard")
	private final ResponseEntity<?> getDashboardDetails() {
		return new ResponseEntity<List<?>>(dashboardServices.getAllUserDetails(), HttpStatus.OK);
	}

	@GetMapping("/approval")
	private final ResponseEntity<?> getApprovalDetails() {
		List<User> approvalDetails = approvalServices.getApprovalUserDetails();

		return ((approvalDetails != null) ? new ResponseEntity<List<User>>(approvalDetails, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/approval/{statusId}")
	private final ResponseEntity<?> approvalDetails(@PathVariable(value = "statusId") Long statusId) {
		User userDetails = approvalServices.approvalDetail(statusId);
		return ((userDetails != null) ? new ResponseEntity<User>(userDetails, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PutMapping("/approval/denied/{statusId}")
	private final ResponseEntity<?> updateApprovalStatusDenied(@PathVariable(value = "statusId") Long statusId,
			@RequestParam(value = "statusMessage") String statusMessage) {
		System.out.println(statusId);
		int isStatusUpdated = approvalServices.updateDeniedStatus(statusId, statusMessage);
		return ((isStatusUpdated == 1) ? new ResponseEntity<Integer>(HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/denied")
	private final ResponseEntity<?> getDeniedDetails() {
		List<User> deniedDetails = deniedServices.getDeniedUserDetails();

		return ((deniedDetails != null) ? new ResponseEntity<List<User>>(deniedDetails, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/denied/{statusId}")
	private final ResponseEntity<?> deniedDetails(@PathVariable(value = "statusId") Long statusId) {
		User userDetails = deniedServices.deniedDetail(statusId);
		return ((userDetails != null) ? new ResponseEntity<User>(userDetails, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

	@PutMapping("/denied/approval/{statusId}")
	private final ResponseEntity<?> updateDeniedStatusApproval(@PathVariable(value = "statusId") Long statusId,
			@RequestParam(value = "statusMessage") String statusMessage) {
		System.out.println(statusId);
		int isStatusUpdated = deniedServices.updateApprovalStatus(statusId, statusMessage);
		return ((isStatusUpdated == 1) ? new ResponseEntity<Integer>(HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST));
	}

}
