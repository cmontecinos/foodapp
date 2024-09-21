package cl.bigbytes.foodapp.service;

import cl.bigbytes.foodapp.domain.Item;
import cl.bigbytes.foodapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItem(Integer id) {
        return itemRepository.getItem(id);
    }
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteItem(id);
    }

    public List<Item> getItems() {
        return itemRepository.getItems();
    }

}
