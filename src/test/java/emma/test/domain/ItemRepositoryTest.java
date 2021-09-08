package emma.test.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void AfterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("rice", 2000, 1);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(savedItem.getName()).isEqualTo(item.getName());
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("whiterice", 2000, 1);
        Item item2 = new Item("yellowrice", 3000, 1);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void 업데이트() {
        //given
        Item item1 = new Item("whiterice", 2000, 1);
        Item savedItem = itemRepository.save(item1);

        //when
        Item updateItem = new Item("yellowrice", 3000, 1);
        itemRepository.update(savedItem.getId(), updateItem);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem.getName()).isEqualTo(updateItem.getName());
    }

}