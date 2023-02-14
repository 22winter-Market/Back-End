package winterproject.market.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import winterproject.market.domain.Item;
import winterproject.market.domain.UploadFile;

@Repository
@RequiredArgsConstructor
public class UploadFilesRepository {

    private final EntityManager em;
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/files/";

    public UploadFile uploadFiles(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String orgFileName = multipartFile.getOriginalFilename();
        String storeFilename = UUID.randomUUID() + "." + extractExt(orgFileName);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(orgFileName, storeFilename, null);
    }

    public List<UploadFile> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult =  new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            if (!multipartFiles.isEmpty()) {
                storeFileResult.add(uploadFiles(multipartFile));
            }
        }
        return storeFileResult;
    }

    private String getFullPath(String name) {
        return fileDir + name;
    }

    private String extractExt(String name) {
        int pos = name.lastIndexOf(".");
        return name.substring(++pos);
    }

    public void save(List<UploadFile> uploadFiles, Item item) {
        for (UploadFile uploadFile: uploadFiles) {
            uploadFile.setItem(item);
            item.getUploadFiles().add(uploadFile);
            em.persist(uploadFile);
        }
    }
}
