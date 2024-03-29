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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GalleryController {
    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/metadata")
    public String showMetadata(Model model) throws ImageProcessingException, IOException {
        Resource resource = resourceLoader.getResource("classpath:static/img/cat.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(resource.getFile());

        List<Data> dataList = new ArrayList<>();
        model.addAttribute("dataList", dataList);

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                dataList.add(new Data(directory.getName(), tag.getTagName(), tag.getDescription()));
                System.out.format("[%s] - %s = %s%n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s%n", error);
                }
            }
        }
        return "metadata";
    }

    @GetMapping("/gallery")
    public String showGallery(){
        return "gallery";
    }

    @GetMapping("/video")
    public String showVideo(){
        return "video";
    }

    @GetMapping("/animations")
    public String showAnimation(){
        return "animations";
    }
}
