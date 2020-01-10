package com.alco.pubslist.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ErrorPageController : ErrorController {
	@RequestMapping("/error")
	fun index(): String {
		return "index.html"
	}

	override fun getErrorPath(): String {
		return "index.html"
	}
}
