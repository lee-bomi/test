package emma.test.web;

import emma.test.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/basic/items")
@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items
}
