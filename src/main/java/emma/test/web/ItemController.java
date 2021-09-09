package emma.test.web;

import emma.test.domain.Item;
import emma.test.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/basic/items")
@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("toy", 1000, 1));
        itemRepository.save(new Item("snack", 2000, 1));
    }

    @GetMapping
    public String items(Model model) { //아직 받은 데이터가 없으니 view에 넘길 Model생성
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("add") //새로고침했을때 최근 작업이 반복지않도록, 리다이렉트
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
}
