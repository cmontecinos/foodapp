package cl.bigbytes.foodapp.repository;

import cl.bigbytes.foodapp.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Item getItem(Integer id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getDouble("price"));
            return item;
        });}

    public void addItem(Item item) {
        String sql = "INSERT INTO item (name, description, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.getPrice());
    }

    public void updateItem(Item item) {
        String sql = "UPDATE item SET name = ?, description = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.getPrice(), item.getId());
    }
    public void deleteItem(Integer id) {
        String sql = "DELETE FROM item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public List<Item> getItems() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getDouble("price"));
            return item;
        });
    }
}

