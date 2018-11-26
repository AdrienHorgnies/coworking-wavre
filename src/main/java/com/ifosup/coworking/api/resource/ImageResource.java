package com.ifosup.coworking.api.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageResource {

    private final Logger log = LoggerFactory.getLogger(ImageResource.class);

    @GetMapping(value = "coworking-spaces/{image:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable String image, HttpServletResponse response) throws IOException {
        log.debug("REST request to get Image : {}", image);

        ClassPathResource imgFile = new ClassPathResource("public/images/coworking-spaces/" + image);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}
