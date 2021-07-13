package donnee;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class StubImage {

    public static List<POJOImage> getLesImages() {
        List<POJOImage> lesImages = new ArrayList<>();
        List<File> lesFiles = new ArrayList<>();
        lesFiles.add(new File("resource/image/1.jpg"));
        lesFiles.add(new File("resource/image/2.jpg"));
        lesFiles.add(new File("resource/image/3.jpg"));
        lesFiles.add(new File("resource/image/4.jpg"));
        lesFiles.forEach(file -> lesImages.add(new POJOImage(file.getPath() ,new Image(file.toURI().toString()))));
        return lesImages;
    }
}
