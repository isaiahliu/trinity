package org.trinity.yqyl.web.controller.ajax;

import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.AbstractResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.message.MessageUtils;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ContentDto;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.request.AuthenticateRequest;
import org.trinity.yqyl.common.message.dto.request.ChangePasswordRequest;
import org.trinity.yqyl.common.message.dto.request.ContentRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceReceiverClientRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierClientRequest;
import org.trinity.yqyl.common.message.dto.request.TokenRequest;
import org.trinity.yqyl.common.message.dto.request.UserRequest;
import org.trinity.yqyl.common.message.dto.response.ContentResponse;
import org.trinity.yqyl.common.message.dto.response.OrderResponse;
import org.trinity.yqyl.common.message.dto.response.SecurityResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.dto.response.TokenResponse;
import org.trinity.yqyl.common.message.dto.response.UserResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.web.util.SessionFilter;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user")
public class UserAjaxController extends AbstractRestController {

	private static final String LICENSE_COPY = "license";

	private static final String IDENTITY_COPY = "identity";

	@Autowired
	private ISecurityUtil<AccessRight> securityUtil;

	@Autowired
	private IRestfulServiceUtil restfulServiceUtil;

	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<DefaultResponse> ajaxChangePassword(@RequestBody final ChangePasswordRequest request) throws IException {
		final DefaultResponse response = restfulServiceUtil.callRestService(Url.USER_CHANGE_PASSWORD, null, request, null,
				DefaultResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/supplier/upload", method = RequestMethod.POST)
	public ResponseEntity<DefaultResponse> ajaxChangePassword(final MultipartHttpServletRequest request) throws IException {

		final DefaultResponse response = new DefaultResponse();
		if (request.getFileNames().hasNext()) {
			try {
				final ServiceSupplierClientResponse serviceSupplierClientResponse = restfulServiceUtil.callRestService(Url.SUPPLIER_ME,
						null, null, null, ServiceSupplierClientResponse.class);

				final String name = request.getFileNames().next();
				String uuid;
				switch (name) {
					case LICENSE_COPY:
						uuid = serviceSupplierClientResponse.getData().get(0).getLicenseCopy();
						break;
					case IDENTITY_COPY:
						uuid = serviceSupplierClientResponse.getData().get(0).getIdentityCopy();
						break;
					default:
						return createResponseEntity(response);
				}
				final ContentRequest contentRequest = new ContentRequest();

				final InputStream stream = request.getFile(name).getInputStream();
				final byte[] bytes = new byte[stream.available()];
				stream.read(bytes);

				final ContentDto dto = new ContentDto();
				dto.setUuid(uuid);
				dto.setContent(bytes);
				contentRequest.getData().add(dto);

				restfulServiceUtil.callRestService(Url.CONTENT_UPLOAD, null, contentRequest, null, ContentResponse.class);
			} catch (final Exception e) {
			}
		}

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/receiver/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<DefaultResponse> ajaxDeleteUserinfo(@PathVariable("id") final Long id) throws IException {
		final DefaultResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_CANCEL_PROPOSAL, String.valueOf(id), null, null,
				DefaultResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/order/processed", method = RequestMethod.GET)
	public ResponseEntity<OrderResponse> ajaxGetProcessedOrders(final ServiceOrderSearchingDto request) throws IException {
		request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

		final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSED, null, null, request, OrderResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/order/processing", method = RequestMethod.GET)
	public ResponseEntity<OrderResponse> ajaxGetProcessingOrders(final ServiceOrderSearchingDto request) throws IException {
		request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

		final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSING, null, null, request, OrderResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/order/unprocessed", method = RequestMethod.GET)
	public ResponseEntity<OrderResponse> ajaxGetUnprocessedOrders(final ServiceOrderSearchingDto request) throws IException {
		request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

		final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_UNPROCESSED, null, null, request, OrderResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public ResponseEntity<UserResponse> ajaxGetUserinfo() throws IException {
		final UserResponse response = restfulServiceUtil.callRestService(Url.USER_ME, null, null, null, UserResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/receiver/{id}", method = RequestMethod.GET)
	public ResponseEntity<ServiceReceiverClientResponse> ajaxGetUserinfo(@PathVariable("id") final Long id) throws IException {
		final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER, String.valueOf(id), null, null,
				ServiceReceiverClientResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.PUT)
	public ResponseEntity<AbstractResponse<?>> ajaxLogin(@RequestBody final SecurityDto dto, final HttpServletResponse httpResponse) {
		final AuthenticateRequest authenticateRequest = new AuthenticateRequest();
		authenticateRequest.setUser(dto);
		final TokenRequest tokenRequest = new TokenRequest();
		tokenRequest.setIdentity(UUID.randomUUID().toString());
		final TokenResponse tokenRespose = restfulServiceUtil.callRestService(Url.TOKEN_NEW, null, tokenRequest, null, TokenResponse.class);

		if (!tokenRespose.getErrors().isEmpty()) {
			return createResponseEntity(tokenRespose);
		}

		final String newToken = tokenRespose.getData().get(0).getToken();

		final SecurityResponse securityResponse = restfulServiceUtil.callRestService(newToken, Url.AUTHENTICATE, null, authenticateRequest,
				null, SecurityResponse.class);

		if (securityResponse.getErrors().isEmpty()) {
			final Cookie cookie = new Cookie(SessionFilter.COOKIE_NAME, newToken);
			cookie.setPath("/");
			httpResponse.addCookie(cookie);
			return createResponseEntity(tokenRespose);
		}

		return createResponseEntity(securityResponse);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<SecurityResponse> ajaxLogout(final HttpServletResponse httpResponse) throws IException {
		final SecurityResponse response = restfulServiceUtil.callRestService(Url.LOGOUT, null, null, null, SecurityResponse.class);

		if (response.getErrors().isEmpty()) {
			final Cookie cookie = new Cookie(SessionFilter.COOKIE_NAME, "");
			cookie.setPath("/");
			httpResponse.addCookie(cookie);
		}

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/supplier/proposal", method = RequestMethod.PUT)
	public ResponseEntity<DefaultResponse> ajaxProposalSupplier(@RequestBody final ServiceSupplierClientRequest request) throws IException {
		final DefaultResponse response = restfulServiceUtil.callRestService(Url.SUPPLIER_PROPOSAL, null, request, null,
				DefaultResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<DefaultResponse> ajaxRegister(@RequestBody final SecurityDto dto) throws IException {
		final AuthenticateRequest authenticateRequest = new AuthenticateRequest();
		authenticateRequest.setUser(dto);

		final DefaultResponse response = restfulServiceUtil.callRestService(Url.REGISTER, null, authenticateRequest, null,
				DefaultResponse.class);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/receiver", method = RequestMethod.PUT)
	public ResponseEntity<IResponse> ajaxUpdateUserinfo(@RequestBody final ServiceReceiverClientRequest request) throws IException {
		if (request.getData().isEmpty()) {
			return createResponseEntity(new DefaultResponse());
		}

		final ServiceReceiverClientDto dto = request.getData().get(0);

		if (dto.getId() != null && dto.getId() > 0) {
			final ServiceReceiverClientResponse me = restfulServiceUtil.callRestService(Url.RECEIVER, String.valueOf(dto.getId()), null,
					null, ServiceReceiverClientResponse.class);
			if (!me.getErrors().isEmpty()) {
				final DefaultResponse errorResponse = new DefaultResponse();
				errorResponse.getErrors().addAll(me.getErrors());
				return createResponseEntity(errorResponse);
			}

			if (!MessageUtils.in(me.getData().get(0).getStatus().getCode(), ServiceReceiverClientStatus.PROPOSAL)) {
				dto.setName(null);
				dto.setStatus(null);
				dto.setGender(null);
				dto.setDob(null);
				dto.setYijinCode(null);
				dto.setIdentityCard(null);
			}

			final DefaultResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_UPDATE, null, request, null,
					DefaultResponse.class);
			return createResponseEntity(response);
		} else {
			dto.setStatus(new LookupDto(ServiceReceiverClientStatus.PROPOSAL));
			final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_ADD, null, request, null,
					ServiceReceiverClientResponse.class);

			return createResponseEntity(response);
		}
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.PUT)
	public ResponseEntity<DefaultResponse> ajaxUpdateUserinfo(@RequestBody final UserRequest request) throws IException {
		final DefaultResponse response = restfulServiceUtil.callRestService(Url.USER_INFO, null, request, null, DefaultResponse.class);

		return createResponseEntity(response);
	}

}
