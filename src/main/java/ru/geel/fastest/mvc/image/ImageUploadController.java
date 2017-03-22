package ru.geel.fastest.mvc.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by ivangeel on 27.02.17.
 */


public class ImageUploadController {

    private MultipartFile multipartFile;
    private String username;

    public ImageUploadController(MultipartFile multipartFile, String username) {
        this.multipartFile = multipartFile;
        this.username = username;
    }

    public String setPhotoIntoDB()
    {
        CryptName cryptName = new CryptName();
        if (!multipartFile.isEmpty()) {
            try {
                byte[] imgBytes = multipartFile.getBytes();
                String dir = "/usr/local/tomcat/webapps/ROOT/images";
                String filename = cryptName.getCryptedName() + ".jpg";
                System.out.println(filename);

                File newImg = new File(dir, filename);

                if(newImg.createNewFile()) {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newImg));
                    stream.write(imgBytes);
                    stream.close();
                }
                return "/images/"+filename;

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("The avatar has been uploaded");
        }
        return null;
    }
}
