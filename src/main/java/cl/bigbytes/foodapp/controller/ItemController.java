package cl.bigbytes.foodapp.controller;

import cl.bigbytes.foodapp.domain.Item;
import cl.bigbytes.foodapp.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> get(@PathVariable Integer id) {
        try {
            var item = itemService.getItem(id);
            return ResponseEntity.ok(item);
        } catch ( Exception ex) {
            log.error("error while reading the user:", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Item item) {
        try {
            itemService.addItem(item);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("Error while adding the item:", ex);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Item item) {
        try {
            item.setId(id);
            itemService.updateItem(item);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("Error while updating the item:", ex);
            return ResponseEntity.status(500).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("Error while deleting the item:", ex);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        try {
            var items = itemService.getItems();
            return ResponseEntity.ok(items);
        } catch (Exception ex) {
            log.error("Error while retrieving items:", ex);
            return ResponseEntity.status(500).build();
        }
    }
}
