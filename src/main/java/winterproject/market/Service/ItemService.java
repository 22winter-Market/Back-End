package winterproject.market.Service;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winterproject.market.Repository.ItemRepository;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.Repository.UploadFilesRepository;
import winterproject.market.controller.ItemForm;
import winterproject.market.domain.Item;
import winterproject.market.domain.Member;
import winterproject.market.domain.UploadFile;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final UploadFilesRepository uploadFilesRepository;

    public Long itemUpload(ItemForm itemForm) {
        try {
            Member member = memberRepository.findOneById(itemForm.getMemberId());
            Item item = new Item(member, itemForm);

            itemRepository.save(item);
            log.info("item id : " + item.getId());
            List<UploadFile> uploadFiles = uploadFilesRepository.uploadFiles(
                itemForm.getImageFiles());
            log.info("upload!!");
            uploadFilesRepository.save(uploadFiles, item);
            log.info("upload save!!");
            return item.getId();
        }catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void itemDelete(Long id) {
        itemRepository.delete(id);
    }

    public void changeStatus(Long id, String status) {
        Item item = itemRepository.findById(id);
        item.soldOut();
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findItems(Member member) {
        return itemRepository.findAllByMember(member);
    }
}
