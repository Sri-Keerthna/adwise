package com.spiralforge.adwise.constants;

/**
 * 
 * @author Muthu
 *
 */
public class ApiConstant {

	private ApiConstant() {
	}

	public static final String LOGIN_ERROR = "Please enter valid username and password";
	public static final String LOGIN_SUCCESS = "You are successfully logged in";

	public static final String SUCCESS = "SUCCESSFUL";
	public static final String FAILED = "FAILED";

	public static final Integer SUCCESS_CODE = 200;
	public static final Integer FAILURE_CODE = 404;
	public static final Integer NO_CONTENT_CODE = 204;

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
	public static final String VALIDATION_FAILED = "VALIDATION FAILED";
	public static final String NO_ELEMENT_FOUND = "NO ELEMENT FOUND";
	public static final String SLOT_NOT_FOUND_EXCEPTION = "No slot available";
	public static final String USER_NOT_FOUND_EXCEPTION = "Invalid user";
	public static final String BOOKING_NOT_FOUND_EXCEPTION = "No bookings found";

}
