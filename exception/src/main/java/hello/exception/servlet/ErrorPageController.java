package hello.exception.servlet;

import static jakarta.servlet.RequestDispatcher.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorPageController {

	@RequestMapping("/error-page/404")
	public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
		log.info("errorPage 404");
		return "error-page/404";
	}

	@RequestMapping("/error-page/500")
	public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
		log.info("errorPage 500");
		return "error-page/500";
	}

	@RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> errorPage500Api(
		HttpServletRequest request, HttpServletResponse response
	) {
		log.info("API ErrorPage500");

		Map<String, Object> result = new HashMap<>();
		Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
		result.put("status", request.getAttribute(ERROR_STATUS_CODE));
		result.put("message", ex.getMessage());

		Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE);
		return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
	}
}
