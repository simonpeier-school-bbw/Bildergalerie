package ch.simonpeier.Bildergalerie;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class GalleryController {
    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/metadata")
    public String showMetadata() throws ImageProcessingException, IOException {
        Resource resource = resourceLoader.getResource("classpath:static/img/cat.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(resource.getFile());

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s%n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s%n", error);
                }
            }
        }
        return "";
    }
}
