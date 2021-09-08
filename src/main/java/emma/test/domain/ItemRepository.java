package emma.test.domain;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   //final이 붙으면 추가 엔티티 할당 불가
    private static Long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Item updateParam) {
        Item item = findById(id);
        item.setName(updateParam.getName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
