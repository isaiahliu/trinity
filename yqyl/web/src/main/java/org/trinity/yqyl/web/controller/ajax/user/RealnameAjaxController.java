package org.trinity.yqyl.web.controller.ajax.user;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ContentDto;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.request.ContentRequest;
import org.trinity.yqyl.common.message.dto.request.UserRealnameRequest;
import org.trinity.yqyl.common.message.dto.response.ContentResponse;
import org.trinity.yqyl.common.message.dto.response.UserRealnameResponse;
import org.trinity.yqyl.common.message.lookup.RealnameStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user/realname")
public class RealnameAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<DefaultResponse> ajaxChangePassword(final MultipartHttpServletRequest request) throws IException {

        final DefaultResponse response = new DefaultResponse();
        if (request.getFileNames().hasNext()) {
            try {
                final UserRealnameResponse serviceSupplierClientResponse = restfulServiceUtil.callRestService(Url.REALNAME_ME, null, null,
                        null, UserRealnameResponse.class);

                final String uuid = serviceSupplierClientResponse.getData().get(0).getCredentialCopy();

                final ContentRequest contentRequest = new ContentRequest();

                final InputStream stream = request.getFile("CREDENTIAL_COPY").getInputStream();
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

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<UserRealnameResponse> ajaxGetUserRealnameForMe() throws IException {
        final UserRealnameResponse response = restfulServiceUtil.callRestService(Url.REALNAME_ME, null, null, null,
                UserRealnameResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<IResponse> ajaxUpdateUserRealname(@RequestBody final UserRealnameRequest request) throws IException {
        if (request.getData().isEmpty()) {
            return createResponseEntity(new DefaultResponse());
        }

        request.getData().forEach(item -> {
            item.setStatus(new LookupDto(RealnameStatus.INPROGRESS.getMessageCode(), ""));
            item.setCredentialCopy(null);
        });

        final DefaultResponse response = restfulServiceUtil.callRestService(Url.REALNAME_UPDATE, null, request, null,
                DefaultResponse.class);
        return createResponseEntity(response);
    }

}
