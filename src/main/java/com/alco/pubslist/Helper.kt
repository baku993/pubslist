package com.alco.pubslist

import com.alco.pubslist.security.RestResponses
import com.google.gson.JsonObject
import org.apache.commons.codec.digest.DigestUtils
import java.io.IOException
import java.io.Writer
import java.sql.Timestamp
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

object Helper {
	@JvmStatic
	fun cacheData(data: String, salt: String): String {
		return DigestUtils.sha512Hex(data + salt)
	}

	@JvmStatic
	@JvmOverloads
	@Throws(IOException::class)
	fun formResponse(response: ServletResponse,
			responseData: RestResponses,
			token: String? = null) {
		val mappedResponse = response as HttpServletResponse
		mappedResponse.status = responseData.statusCode
		mappedResponse.contentType = "application/json"
		mappedResponse.characterEncoding = "UTF-8"
		val json = JsonObject()
		json.addProperty("timestamp", Timestamp(System.currentTimeMillis()).toString())
		if (responseData.statusCode >= 400) {
			json.addProperty("status", "Error")
		}
		json.addProperty("message", responseData.message)
		if (token != null) {
			json.addProperty("token", token)
		}
		val writer: Writer = mappedResponse.writer
		writer.write(json.toString())
		writer.flush()
		writer.close()
	}
}
