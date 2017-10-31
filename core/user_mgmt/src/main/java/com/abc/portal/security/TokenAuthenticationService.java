package com.abc.portal.security;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 480000; // 8 minutes - (120000 = 2 minutes)
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, Authentication auth) {
		String JWT = Jwts.builder().setSubject(auth.getName())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			jsonObject.put("access_token", TOKEN_PREFIX + " " + JWT);

			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper

			String jsonInString = null;
			try {
				map.put("u_info", auth.getPrincipal());
				jsonInString = mapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			JSONObject jObject = new JSONObject(jsonInString); 
			JSONObject data = jObject.getJSONObject("u_info"); 

			jsonObject.put("UserInfo", data.getJSONObject("user"));

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		res.setContentType("application/json");
		
		PrintWriter out;
		
		try {
			out = res.getWriter();
			out.print(jsonObject);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Authentication getAuthentication(HttpServletRequest request) throws IOException {
		// String token = readRequestBody(request);

		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null,emptyList());
			} else {
				throw new RuntimeException("403 Exception");
			}
		}
		return null;
	}

	/*public static String readRequestBody(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		
		 * String line = null; try { BufferedReader reader = request.getReader(); while
		 * ((line = reader.readLine()) != null) jb.append(line); } catch (Exception e) {
		 * report an error }
		 

		String s = getBody(request);

		JSONObject jsonObject;
		String token;
		try {

			jsonObject = new JSONObject(s.toString()); // json
			// jsonObject = HTTP.toJSONObject(jb.toString());
			System.out.println(jsonObject);
			token = jsonObject.getString("access_token");
			return token;
		} catch (JSONException e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}

	}

	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}*/
}
