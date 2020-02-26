package pl.dma.appka.picture;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.dma.appka.exceptions.FileNotFoundException;
import pl.dma.appka.exceptions.FileStorageException;

import java.io.IOException;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture storePicture(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageException("invalid file name: " + fileName);
            }

            Picture picture = new Picture(fileName, file.getBytes());

            return pictureRepository.save(picture);
        }catch (IOException e){
            throw new FileStorageException("could not store file " + fileName);
        }
    }

    public Picture getPicture(Integer id){
        return pictureRepository.findById(id).orElseThrow(() -> new FileNotFoundException("no file found with id " + id));
    }

}
